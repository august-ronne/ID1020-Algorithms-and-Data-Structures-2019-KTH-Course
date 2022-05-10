/**************************************************************************
 * Compilation: javac <name>.java
 * Execution: java <name> < input.txt (input as txt file optional)
 * Dependencies: StdIn.java, StdOut.java
 *
 * A generic, iterable FIFO queue ADT.
 * The queue is constructed using a doubly linked list, where
 * each Node is linked to its previous and next Node.
 *
 * Explanation of unit test in main() method:
 * The input is read as chars until a newline char is registered,
 * and stored in the queue. The contents of the queue is printed
 * using the toString() method, which can iterate the queue
 * using a for : each loop thanks to the ListIterator.
 *
 ***************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Element> implements Iterable<Element> {
    private Node start;        // empty starting node of the doubly linked list
    private Node stop;         // empty stopping node of the doubly linked list
    private int size;                   // current size of the doubly linked list

    /**
     * Doubly linked Node class.
     * The node contains data in form of a stored Element object
     */
    private class Node {
        private Element elem;
        private Node next;
        private Node previous;
    }

    /**
     * Initializes a new doubly linked list.
     * Contains a starting and stopping node.
     */
    public DoublyLinkedList() {
        start = new Node();
        stop = new Node();
        start.next = stop;
        stop.previous = start;
    }

    /**
     * Checks if the doubly linked list is empty
     * @return true if this list is empty, false if not
     */
    public boolean isEmpty() { return size == 0; }

    /**
     * Checks the current size of the doubly linked list
     * @return size the amount of Elements currently stored in the doubly linked list
     */
    public int size() { return size; }

    /**
     * Adds a new Element object to the end of the queue
     * @param elem Element object to add to the queue
     */
    public void enqueue(Element elem) {
        Node insertion = stop.previous;
        Node newNode = new Node();
        newNode.elem = elem;
        newNode.next = stop;
        newNode.previous = insertion;
        stop.previous = newNode;
        insertion.next = newNode;
        size++;
    }

    /**
     * Returns the least recently added element of the queue,
     * and the removes access to it by making the least recently added
     * element the new starting node.
     * @return elem the oldest element currently stored in the queue
     */
    public Element dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Element elem = start.next.elem;
        start = start.next;
        size--;
        return elem;
    }

    /**
     * Iterates through the doubly linked list in order
     * to return a String representation of the contents
     * @return s the elements of the queue in brackets, separated by commas
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Element elem : this) {
            sb.append("[");
            sb.append(elem);
            sb.append("], ");
        }
        return sb.toString();
    }

    /**
     * Method which returns a new ListIterator which
     * iterates over the doubly linked list in FIFO order
     * @return a new ListIterator object
     */
    public Iterator<Element> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Element> {
        private Node current = start.next;

        public boolean hasNext() { return current != stop; }
        public void remove() { throw new UnsupportedOperationException(); }

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
     * Unit tests the {@code DoublyLinkedList} data type.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        DoublyLinkedList<Character> queue = new DoublyLinkedList<Character>();
        while (true) {
            Character elem = StdIn.readChar();
            if (elem.equals('\n')) {
                break;
            }
            queue.enqueue(elem);
        }
        StdOut.println("Printing the contents of the queue using the toString() method," +
                " which in turn can iterate through the queue with a 'for each loop thanks" +
                " to the ListIterator");
        StdOut.println(queue);

        StdOut.println("\nRemoving the least recently added element using the dequeue() method"
        + queue.dequeue());
        StdOut.println("\nPrinting the contents of the queue after using dequeue() method");
        StdOut.println(queue);
        StdOut.println("\nCurrent size of queue: " + queue.size() + " elements.");

    }

}