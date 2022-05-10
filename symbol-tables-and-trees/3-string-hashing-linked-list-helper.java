/**
 * The {@code LinkedListST} class constructs a
 * symbol table of key-value pairs by building an
 * unordered linked list consisting of the helper Node class,
 * which can store a key, value, and a link to the next Node.
 * Supports the following common methods:
 * <em>size</em>
 * <em>isEmpty</em>
 * <em>get</em>
 * <em>put</em>
 * <em>contains</em>
 * It also defines a <em>keys</em> method which returns an iterable
 * {@code LinkedListQueue} object containing all the keys currently
 * stored in the {@code LinkedListST} object.
 * @param <Key>
 * @param <Value>
 */
public class LinkedListST<Key, Value> {
    private int size;
    private Node head;

    /**
     * Nested Node class. A Node object
     * can store a key-value pair and is
     * linked to the next Node
     */
    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value val, Node next)  {
            this.key  = key;
            this.value  = val;
            this.next = next;
        }
    }

    /**
     * Get the size of the linked list
     * @return size of the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Check if the list is empty
     * @return {@code true} if empty, {@code false} if not
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Checks if the linked list contains the argument key
     * @param key the key to be searched for
     * @return {@code true} if key is in the table, {@code false} if not
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("call to contains() with null key");
        return get(key) != null;
    }

    /**
     * Return the value associated with the argument key
     * @param key the key to be searched for
     * @return the value associated with the key, or {@code null} if the key is not in the table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("call to get() with null key");
        for (Node current = head; current != null; current = current.next) {
            if (key.equals(current.key)) {
                return current.value;
            }
        }
        return null;
    }

    /**
     * Finds the Node storing the key-value pair given
     * by the argument key and updates the value. If the key
     * is not present in the table the method creates a new Node
     * storing the argument key-value pair
     * @param key the key to be searched for
     * @param value the value associated with the argument key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("call to put() with null key");
        for (Node current = head; current != null; current = current.next) {
            if (key.equals(current.key)) {
                current.value = value;
                return;
            }
        }
        head = new Node(key, value, head);
        size++;
    }

    /**
     * Returns an iterable consisting of a
     * {@code LinkedListQueue} object containing
     * all the keys of the linked list symbol table
     * @return a {@code LinkedListQueue} object containing all keys in the symbol table
     */
    public Iterable<Key> keys()  {
        LinkedListQueue<Key> list = new LinkedListQueue<Key>();
        for (Node current = head; current != null; current = current.next)
            list.add(current.key);
        return list;
    }
}
