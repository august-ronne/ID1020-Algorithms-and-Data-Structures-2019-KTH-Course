/******************************************************************************
 * Compilation: javac <name>.java
 * Execution: java <name>
 *
 *      Unit test in main() method explained:
 * There is no option for user input in the testing of the data type.
 * A hardcoded integer array is created each time the program is executed,
 * and the contents of this array is then added in ascending order to the
 * singly linked list.
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@code LinkedListAscending} class is a singly linked list where
 * elements are added in ascending order and removed in LIFO order.
 * Each time an element is added to the list the whole list is
 * printed to the standard output.
 *
 * @author August Ronne, aronne@kth.se
 * @param <Element>
 */

public class Assignment7<Element extends Comparable<Element>> implements Iterable<Element> {

    private int size;       // size of the list
    private Node head ;     // start of the list

    /**
     * Linked list consists of objects of the Node class.
     * The Node stores data in form of an element, and
     * has a reference to the next Node in the linked list.
     */
    private class Node {
        private Element elem;
        private Node next;
    }
    /**
     * Initializes an empty linked list
     */
    public Assignment7() {
        head = new Node();
        size = 0;
    }

    /**
     * Checks whether the linked list is empty.
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the linked list.
     * @return the size of the linked list.
     */
    public int size() {
        return size;
    }

    /**
     * Stores an element in a Node object and add
     * the Node object to the linked list.
     * @param elem the element to be added
     */
    public void addInAscendingOrder(Element elem) {
        if (isEmpty()) {
            head.elem = elem;
            size++;
        } else if (lessOrEqual(elem, head.elem)) {
            Node newHead = new Node();
            newHead.elem = elem;
            newHead.next = head;
            head = newHead;
            size++;
        } else {
            Node current = head;
            while (!lessOrEqual(elem, current.elem)) {
                if (current.next != null) {
                    current = current.next;
                } else {
                    Node newNode = new Node();
                    newNode.elem = elem;
                    current.next = newNode;
                    size++;
                    System.out.println(this);
                    return;
                }
            }
            Node newNode = new Node();
            newNode.elem = elem;
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
        System.out.println(this);
    }

    public Element pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        Element elem = head.elem;
        head = head.next;
        size--;
        return elem;
    }

    public boolean lessOrEqual(Element e, Element f) {
        return e.compareTo(f) <= 0;
    }

    /**
     * Produces and return a String representation
     * of the elements currently stored in the linked list.
     * @return the elements of the list in ascending order
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Element e : this) {
            sb.append("[").append(e).append("], ");
        }
        return sb.toString();
    }

    /**
     * Returns an iterator that iterates through the linked list
     * in ascending order.
     * @return an iterator that iterates through the list in ascending order
     */
    public Iterator<Element> iterator() {
        return new ListIterator();
    }

    /**
     * List Iterator. Does not implement the remove method.
     */
    private class ListIterator implements Iterator<Element> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
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

    /**
     * Unit tests the {@code LinkedListAscending} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Assignment7<Integer> linkedList = new Assignment7<Integer>();

        System.out.println("\n Creating test array [3, 4, 5, 6, 1, 0, 3].\n" +
                " The integers will now be added to the linked list and" +
                " inserted in ascending order.\n Each time an integer is added" +
                " to the linked list, the list is printed in its entirety.\n");
        int[] testArray = {3, 4, 5, 6, 1, 0, 3};
        for (int i : testArray) {
            linkedList.addInAscendingOrder(i);
        }

    }

}
