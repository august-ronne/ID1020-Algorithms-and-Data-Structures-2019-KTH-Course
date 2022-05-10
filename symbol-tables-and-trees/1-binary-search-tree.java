import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    /**
     * Initializes the BST Symbol Table
     */
    public BinarySearchTree() {}

    /**
     * Return the amount of pairs in the BST
     * @return amount of pairs currently stored in the BST
     */
    public int size() {
        return size(root);
    }

    /**
     * Returns the number of key-value pairs
     * stored in the tree with the argument Node as root
     * @param x root of tree which size is to be returned
     * @return size of the tree rooted at {@code Node} x
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        else return x.size;
    }

    /**
     * Checks if the BST is empty
     * @return {@code true} if empty, {@code false} if not
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return the value connected with the argument key
     * @param key the key to be searched for
     * @return the value connected to the key, or return {@code null}
     *         if no such key is in the BST
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * Return the value connected with the argument key.
     * Search starts at argument {@code Node} x.
     * @param x root at which the method starts searching for the key
     * @param key the key to be searched for
     * @return the value associated with the key, return {@code null} if no such
     *          key is stored in the BST
     */
    public Value get(Node x, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("call to get() with null key.");
        }
        if (x == null) {
            return null;
        }
        int comparison = key.compareTo(x.key);
        if (comparison < 0) {
            return get(x.left, key);
        } else if (comparison > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("call to contains() with null key.");
        }
        return get(key) != null;
    }

    /**
     * Adds the key-value pair to the BST by calling
     * its sister method with the private {@code Node} root
     * as the starting point of the search
     * @param key key of pair to be added
     * @param value value of pair to be added
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("call to put() with null key.");
        }
        root = put(root, key, value);
    }

    /**
     * Recursive method that searches for the right place
     * to add the new key-value pair. If the key is already present
     * in the BST the value is updated.
     * @param x current {@code Node}, initially the BST root
     * @param key the key of the pair to be added
     * @param value the value of the pair to be added
     * @return the current node to enable recursive search
     */
    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int comparison = key.compareTo(x.key);
        if (comparison < 0) {
            x.left = put(x.left, key, value);
        } else if (comparison > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Retrieves the smallest key in the BST
     * @return the smallest key in the BST
     * @throws NoSuchElementException if the BST is empty
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("call to min() with empty symbol table.");
        }
        return min(root).key;
    }

    /**
     * Recursive method that moves down the tree starting
     * at the root until it reaches the smallest key in the BST
     * @param x the {@code Node} root the search starts at
     * @return the {@code Node} storing the smallest key of the BST
     */
    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    /**
     * Retrieves the largest key in the BST
     * @return the largest key in the BST
     * @throws NoSuchElementException if the BST is empty
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("call to min() with empty symbol table.");
        }
        return max(root).key;
    }

    /**
     * Recursive method that moves down the tree starting
     * at the root until it reaches the largest key in the BST
     * @param x the {@code Node} root the search starts at
     * @return the {@code Node} storing the largest key of the BST
     */
    private Node max(Node x) {
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    /**
     * Produces an iterable {@code LinkedList} containing
     * all the keys of the BST
     * @return an iterable {@code LinkedList} of all the BST keys
     */
    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new LinkedList<Key>();
        }
        return keys(min(), max());
    }
    /**
     * Produces an iterable {@code LinkedList} object containing
     * all keys currently stored in the BST
     * @param lo lower bound of iteration
     * @param hi upper bound of iteration
     * @return a {@code LinkedList} object containing all the keys in the BST
     * @throws IllegalArgumentException if {@code lo} or {@code hi} is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("call to keys() with first arg null.");
        }
        if (hi == null) {
            throw new IllegalArgumentException("call to keys() with second arg null.");
        }
        LinkedListQueue<Key> list = new LinkedListQueue<Key>();
        keys(root, list, lo, hi);
        return list;
    }

    private void keys(Node x, LinkedListQueue<Key> list, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int comparisonLo = lo.compareTo(x.key);
        int comparisonHi = hi.compareTo(x.key);
        if (comparisonLo < 0) {
            keys(x.left, list, lo, hi);
        }
        if (comparisonLo <= 0 && comparisonHi >= 0) {
            list.add(x.key);
        }
        if (comparisonHi > 0) {
            keys(x.right, list, lo, hi);
        }
    }
}
