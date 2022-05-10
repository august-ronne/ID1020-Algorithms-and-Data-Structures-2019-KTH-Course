/*****************************************************************************************************
 * Compilation: javac <name>.java
 * Execution: java <name> < input.txt
 *      A txt-file as input is optional, the program can be tested
 *      using runtime input from the user.
 * Dependencies: StdIn.java, StdOut.java
 *
 * A generic queue implemented using a linked list. Instead
 * of adding elements to the end of the queue, this program allows
 * insertion of a new element anywhere in the queue, specified by an index.
 * The most recently added element in the queue has index = 1.
 *
 * Explanation of unit test in main() method:
 * Input from the user is registered as chars and added to the queue until
 * a newline character is registered.
 * After the chars has been stored the size of the queue is printed using the size() method.
 * After this the program removes the element with index 1, then index 1 again, followed by index
 * 9, and index 5.
 * EXAMPLE INPUT:
 * bartolomeus
 * s removed (index 1)
 * u removed (index 1)
 * b removed (index 9)
 * o removed (index 5)
 * FINAL OUTPUT:
 * artlome
 **********************************************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Element> implements Iterable<Element> {
    private int size;
    private Node firstNode;
    private Node lastNode;

    /**
     * Node class which stores information in form
     * of an Element object. Linked to the next node
     * in the list.
     */
    private class Node {
        private Element elem;
        private Node next;
    }

    /**
     * Initializes an empty linked list.
     */
    public LinkedQueue() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    /**
     * Checks if the queue is empty.
     * @return true if the list is empty, false if not
     */
    public boolean isEmpty() {
        return firstNode == null;
    }

    /**
     * Returns the amount of Elements currently stored in the queue.
     * @return  the amount of Elements in the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Adds a new Element to the end of the queue.
     * @param elem the Element to be added
     */
    public void enqueue(Element elem) {
        Node previousLastNode = lastNode;
        lastNode = new Node();
        lastNode.elem = elem;
        lastNode.next = null;
        if (isEmpty()) {
            firstNode = lastNode;
        } else {
            previousLastNode.next = lastNode;
        }
        size++;
    }

    /**
     * Removes and returns the Element from the given position
     * in the queue. The most recently added Element has index = 1.
     * @param index the index of the Element to be fetched
     * @return elem the element at index
     */
    public Element dequeueAtIndex(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node current = firstNode;
        int counter = size;
        Element elem;
        if (index != size) {
            while (counter > index + 1) {
                counter--;
                current = current.next;
            }
            elem = current.next.elem;
            current.next = current.next.next;
            if (index == 1) {
                lastNode = current;
            }
        } else {
            elem = current.elem;
            firstNode = current.next;
            current = null;
        }
        size--;
        return elem;

    }

    /**
     * Returns a String representation of the contents of the queue.
     * @return the Elements stored in the queue, in LIFO order,
     * encased in brackets and separated by commas
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Element e : this) {
            sb.append("[");
            sb.append(e);
            sb.append("], ");
        }
        return sb.toString();
    }

    /**
     * Returns an iterator that iterates through the queue in LIFO order.
     * @return an iterator that iterates through the queue in LIFO order
     */
    public Iterator<Element> iterator() {
        return new ListIterator();
    }

    /**
     * A ListIterator. Does not include the remove() method.
     */
    private class ListIterator implements Iterator<Element> {
        private Node current = firstNode;

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
     * Unit tests the {@code LinkedQueue} data type.
     * The structure of the test is explained in the README
     * at the top of the file.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        LinkedQueue<Character> queue = new LinkedQueue<Character>();

        while (true) {
            Character elem = StdIn.readChar();
            if (elem.equals('\n')) {
                break;
            }
            queue.enqueue(elem);
        }
        StdOut.println("The size of the queue is: " + queue.size());
        StdOut.println(queue);
        StdOut.println(queue.dequeueAtIndex(1));
        StdOut.println(queue.dequeueAtIndex(1));
        StdOut.println(queue.dequeueAtIndex(9));
        StdOut.println(queue.dequeueAtIndex(5));
        StdOut.println(queue);
        StdOut.println("\nThe size of the queue is: " + queue.size());
    }

}
