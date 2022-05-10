/************************************************************************
 * Compilation: javac <name>.java
 * Execution: java <name>
 * Dependencies: StdIn.java, StdOut.java
 *
 * An implementation of a circular doubly linked list. Each node
 * in the list is linked to its previous and next neighbouring node,
 * including the end nodes.
 *
 * Explanation of unit testing in main() function:
 * First input as read as chars until a new-line character is registered.
 * Every registered char is added to both the end and the start of the list.
 * After the loop is finished the contents of the list are printed. The final
 * and starting element are then removed and the contents are printed again.
 * 1) Example input: rudolf
 * 2) After chars has been added using addAtEnd() and addAtStart() methods:
 *      [f],[l],[o],[d],[u],[r],[r],[u],[d],[o],[l],[f],
 * 3) After chars has been removed using fetchAtEnd() and fetchAtStart() methods:
 *      [l],[o],[d],[u],[r],[r],[u],[d],[o],[l],
 *
 *  Author: August Ronne, aronne@kth.se
 ***************************************************************************/


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularDoublyLinkedList<Element> implements Iterable<Element> {
    private int size;           // size of the list
    private Node startBlock;    // starting Node of the list
    private Node stopBlock;     // final Node of the list

    /**
     * Initializes a circular doubly linked list with a starting
     * and stopping node. The nodes are linked to each other with both
     * their previous and next links.
     */
    public CircularDoublyLinkedList() {
        startBlock = new Node();
        stopBlock = new Node();
        startBlock.previous = stopBlock;
        startBlock.next = stopBlock;
        stopBlock.previous = startBlock;
        stopBlock.next = startBlock;
    }

    /**
     * The list is constructed by connected node objects.
     * Can store information in form of an Element object.
     */
    private class Node {
        private Element elem;
        private Node next;
        private Node previous;
    }

    /**
     * Returns true if the list is empty
     * @return true if the list is empty, false if not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the amount of Elements stored in the list
     * @return the amount of Elements stored in the list
     */
    public int size() {
        return size;
    }

    /**
     * Add an Element object to the end of the list.
     * @param elem the Element to be added
     */
    public void addAtEnd(Element elem) {
        Node newNode = new Node();
        newNode.elem = elem;
        newNode.next = stopBlock;
        newNode.previous = stopBlock.previous;
        newNode.previous.next = newNode;
        stopBlock.previous = newNode;
        size++;
    }

    /**
     * Add an Element to the start of the list.
     * @param elem the Element to be added
     */
    public void addAtStart(Element elem) {
        Node newNode = new Node();
        newNode.elem = elem;
        newNode.next = startBlock.next;
        newNode.previous = startBlock;
        newNode.next.previous = newNode;
        startBlock.next = newNode;
        size++;
    }

    /**
     * Fetches and removes the Element stored at the end of the list
     * @return elem the Element to be fetched and removed
     */
    public Element fetchAtEnd() {
        Node nodeFetchedFrom = stopBlock.previous;
        Element elem = stopBlock.previous.elem;
        stopBlock.previous.previous.next = stopBlock;
        stopBlock.previous = stopBlock.previous.previous;
        nodeFetchedFrom = null;
        size--;
        return elem;
    }

    /**
     * Fetches and removes the Element stored at the start of the list
     * @return elem the Element to be fetched and removed
     */
    public Element fetchAtStart() {
        Node nodeFetchedFrom = startBlock.next;
        Element elem = startBlock.next.elem;
        startBlock.next.next.previous = startBlock;
        startBlock.next = startBlock.next.next;
        nodeFetchedFrom = null;
        size--;
        return elem;
    }

    /**
     * Returns a new iterator that iterates over the list
     * from the first Node to the last Node
     * @return an iterator iterating over the list from the first Node to the last Node
     */
    public Iterator<Element> iterator() {
        return new ListIterator();
    }

    /**
     * A ListIterator. Does not implement the remove() method.
     */
    private class ListIterator implements Iterator<Element> {
        private Node current = startBlock.next;

        public boolean hasNext() { return current != stopBlock; }
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
     * Returns a String representation of the list
     * @return a String representation of the Elements stored in the list,
     * starting from the first node, moving toward the last node
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
     * Unit tests the {@code CircularDoublyLinkedList} data type.
     * The test is explained in the README at the top of the file.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        CircularDoublyLinkedList<Character> queue = new CircularDoublyLinkedList<Character>();

        while (true) {
            Character elem = StdIn.readChar();
            if (elem.equals('\n')) {
                break;
            }
            queue.addAtStart(elem);
            queue.addAtEnd(elem);
        }
        StdOut.println(queue);
        StdOut.println(queue.fetchAtEnd());
        StdOut.println(queue.fetchAtStart());
        StdOut.println(queue);
        StdOut.println(queue.size());
    }
}