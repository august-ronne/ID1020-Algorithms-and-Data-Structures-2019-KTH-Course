import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@code LinkedListQueue} is a simplified class
 * for constructing a generic, iterable linked list. Built
 * for very specific tests of other data structures it
 * only supports the <em>add</em> method.
 * @param <Element>
 */
public class LinkedListQueue<Element> implements Iterable<Element> {
    private int size;
    private Node head;
    private Node tail;

    /**
     * Nested Node class. A Node object can
     * store a generic element and is linked
     * to the next Node.
     */
    private class Node {
        private Element elem;
        private Node next;
    }

    /**
     * Initializes a new linked list
     * where both head and tail are set
     * to null.
     */
    public LinkedListQueue() {
        head = null;
        tail  = null;
        size = 0;
    }

    /**
     * Check if the linked list is empty
     * @return {@code true} if empty, {@code false} if not
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Get the size of the linked list
     * @return the size of the linked list
     */
    public int getsize() {
        return size;
    }

    /**
     * Constructs a Node storing the argument Element
     * and adds the Node to the end of the linked list
     * @param elem
     */
    public void add(Element elem) {
        Node oldTail = tail;
        tail = new Node();
        tail.elem = elem;
        tail.next = null;
        if (isEmpty()) {
            head = tail;
        } else {
            oldTail.next = tail;
        }
        size++;
    }

    /**
     * Return an iterator that iterates over the linked list
     * starting from the head
     * @return
     */
    public Iterator<Element> iterator()  {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Element> {
        private Node current = head;

        public boolean hasNext()  {
            return current != null;
        }
        public void remove()      {
            throw new UnsupportedOperationException();
        }
        public Element next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Element elem = current.elem;
            current = current.next;
            return elem;
        }
    }
}