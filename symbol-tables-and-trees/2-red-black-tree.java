import java.util.NoSuchElementException;

public class RedBlackTree<Key extends Comparable<Key>, Value> {

    private static final boolean red   = true;
    private static final boolean black = false;
    private Node root;

    /**
     * Nested Node class. The Node stored a {@code comparable} key and its
     * associated value. Each Node is linked two other child Nodes, which
     * can be {@code null}. The boolean parentLinkColor sets the link connecting
     * the Node to its parent to either red or black. Each Node also
     * stores the size of the subtree it is the root of.
     */
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private boolean parentLinkColor;
        private int size;

        public Node(Key key, Value value, boolean parentLinkColor, int size) {
            this.key = key;
            this.value = value;
            this.parentLinkColor = parentLinkColor;
            this.size = size;
        }
    }

    public RedBlackTree() {
    }

    /**
     * Checks if the parent link of the argument Node is red
     * @param x node which parents link we are interested in
     * @return {@code true} is parent link is red, {@code false} if not
     */
    private boolean redParentLink(Node x) {
        if (x == null) {
            return false;
        }
        return x.parentLinkColor == red;
    }

    /**
     * Get the size of the subtree the argument node
     * is the root of
     * @param x root of the subtree
     * @return the size of the subtree
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    public int size() {
        return size(root);
    }

    /**
     * Checks if the Red Black BST is empty
     * @return {@code true} if tree is empty, {@code false} if not
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Get the value associated with the argument key
     * @param key the key to be searched for
     * @return the value associated with the argument key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        while (x != null) {
            int comp = key.compareTo(x.key);
            if      (comp < 0) {
                x = x.left;
            } else if (comp > 0) {
                x = x.right;
            } else {
                return x.value;
            }
        }
        return null;
    }

    /**
     * Checks if the tree contains the argument key
     * @param key the key to be searched for
     * @return {@code true} is key is in tree, {@code false} if not
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * Adds the argument key and its associated value
     * to the tree
     * @param key the argument key
     * @param val the argument value associated with the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("call to put() with null key");
        }
        root = put(root, key, val);
        root.parentLinkColor = black;
    }

    private Node put(Node currentNode, Key key, Value val) {
        if (currentNode == null) {
            return new Node(key, val, red, 1);
        }

        int comp = key.compareTo(currentNode.key);
        if      (comp < 0) {
            currentNode.left  = put(currentNode.left,  key, val);
        } else if (comp > 0) {
            currentNode.right = put(currentNode.right, key, val);
        } else {
            currentNode.value   = val;
        }

        if (redParentLink(currentNode.right) && !redParentLink(currentNode.left)) {
            currentNode = rotateLeft(currentNode);
        }
        if (redParentLink(currentNode.left)  &&  redParentLink(currentNode.left.left)) {
            currentNode = rotateRight(currentNode);
        }
        if (redParentLink(currentNode.left)  &&  redParentLink(currentNode.right)) {
            flipColors(currentNode);
        }
        setSubTreeSize(currentNode);

        return currentNode;
    }

    /**
     * Sets the subtree size of a tree
     * rooted at node x
     * @param x root of subtree
     */
    private void setSubTreeSize(Node x) {
        x.size = size(x.left) + size(x.right) + 1;
    }

    /**
     * Rotates a subtree right around the pivot node
     * given as an argument
     * @param pivot node to rotate around
     * @return the new root of the subtree
     */
    private Node rotateRight(Node pivot) {
        Node x = pivot.left;
        pivot.left = x.right;
        x.right = pivot;
        x.parentLinkColor = x.right.parentLinkColor;
        x.right.parentLinkColor = red;
        x.size = pivot.size;

        setSubTreeSize(pivot);
        return x;
    }

    /**
     * Rotate a subtree left around the pivot node
     * given as an argument
     * @param pivot node to rotate around
     * @return the new root of the subtree
     */
    private Node rotateLeft(Node pivot) {
        Node x = pivot.right;
        pivot.right = x.left;
        x.left = pivot;
        x.parentLinkColor = x.left.parentLinkColor;
        x.left.parentLinkColor = red;
        x.size = pivot.size;

        setSubTreeSize(pivot);
        return x;
    }

    /**
     * Flip the parent link colors of the 3-node subtree
     * rooted by the argument node
     * @param pivot node to flip link colors around
     */
    private void flipColors(Node pivot) {
        pivot.parentLinkColor = !pivot.parentLinkColor;
        pivot.left.parentLinkColor= !pivot.left.parentLinkColor;
        pivot.right.parentLinkColor = !pivot.right.parentLinkColor;
    }

    /**
     * Get the smallest key of the tree
     * @return the smallest key of the tree
     */
    public Key smallestNode() {
        if (isEmpty()) {
            throw new NoSuchElementException("call to smallestNode() with null key");
        }
        return smallestNode(root).key;
    }

    private Node smallestNode(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return smallestNode(x.left);
        }
    }

    /**
     * Get the largest key of the tree
     * @return the largest key of the tree
     */
    public Key largestNode() {
        if (isEmpty()) {
            throw new NoSuchElementException("call to largestNode() with null key");
        }
        return largestNode(root).key;
    }

    private Node largestNode(Node x) {
        if (x.right == null) {
            return x;
        } else {
            return largestNode(x.right);
        }
    }

    /**
     * Produce and return an iterable linked list consisting of all the
     * keys in the tree. The keys are added in ascending order.
     * @return an ascending linked list containing the tree keys
     */
    public Iterable<Key> keys() {
        if (isEmpty()) return new LinkedListQueue<Key>();
        return keys(smallestNode(), largestNode());
    }

    public Iterable<Key> keys(Key lowerBound, Key upperBound) {
        if (lowerBound == null) {
            throw new IllegalArgumentException("call to keys() with null arg");
        }
        if (upperBound == null) {
            throw new IllegalArgumentException("call to keys() with null arg");
        }
        LinkedListQueue<Key> list = new LinkedListQueue<Key>();
        keys(root, list, lowerBound, upperBound);
        return list;
    }

    private void keys(Node x, LinkedListQueue<Key> list, Key lowerBound, Key upperBound) {
        if (x == null) return;
        int compLowerBound = lowerBound.compareTo(x.key);
        int compUpperBound = upperBound.compareTo(x.key);
        if (compLowerBound < 0) {
            keys(x.left, list, lowerBound, upperBound);
        }
        if (compLowerBound <= 0 && compUpperBound >= 0) {
            list.add(x.key);
        }
        if (compUpperBound> 0) {
            keys(x.right, list, lowerBound, upperBound);
        }
    }
}