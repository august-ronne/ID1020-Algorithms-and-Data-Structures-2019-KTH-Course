/**
 * Compilation: javac <name>.java
 * Execution: java <name> < input.txt
 * Dependencies: StdIn.java, StdOut.java
 *
 * A generic stack, where data is stored in an array.
 * If the array is too large or too small it is resized.
 *
 * Unit test of implementation in main() method works as follows,
 * Input is read from StdIn and pushed to the stack as chars using a recursive function.
 * When everything until the newline char has been read the recursive function starts
 * popping the stored chars in a LIFO order.
 * StdIn input: hello
 * StdOut output: [o],[l],[l],[e],[h],
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReverserRecursive<Element> implements Iterable<Element> {

    private Element[] elems;       // array for storing our elements
    private int n;                 // current size of stack

    /**
     * Constructor initializes an empty stack.
     */
    public ReverserRecursive() {
        elems = (Element[]) new Object[2];
        n = 0;
    }

    /**
     * Check if stack has 0 elements.
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the amount of elements in the stack.
     * @return the amount of elements in the stack
     */
    public int size() {
        return n;
    }

    /**
     * Resize the array containing the elements.
     *
     * @param capacity the capacity of the new Element array
     */
    private void resize(int capacity) {
        if (capacity < n) {
            return;
        }
        Element[] temporary = (Element[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temporary[i] = elems[i];
        }
        elems = temporary;
    }

    /**
     * Adds an element to the top of the stack.
     * @param elem the element to add
     */
    public void push(Element elem) {
        if (n == elems.length) {
            resize(2 * elems.length);
        }
        elems[n++] = elem;
    }

    /**
     * Removes and returns the element most recently added to the stack.
     * @return the element most recently added
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public Element pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        Element elem = elems[--n];
        elems[n] = null;
        if ((n > 0) && (n == elems.length / 4)) {
            resize(elems.length / 2 );
        }
        return elem;
    }

    /**
     * Returns an iterator for this stack that iterates
     * through the elements of the stack in LIFO order.
     * @return an iterator that for this stack that iterates through the items in LIFO order
     */
    public Iterator<Element> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * A private Iterator class. Does not include the remove() method.
     */
    private class ReverseArrayIterator implements Iterator<Element> {
        private int i;

        public ReverseArrayIterator() {
            i = n - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Element next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elems[i--];
        }
    }

    /**
     * Unit tests the {@code Stack} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        ReverserRecursive<Character> stack = new ReverserRecursive<Character>();
        reverseRecursive(stack);
    }

    /**
     * A recursive function that reads the user input as chars and
     * out puts the reversed char sequence
     * @param stack the Stack ADT initialized by the main method
     * @return jumping back to the previous method body
     */
    private static Object reverseRecursive(ReverserRecursive stack) {
        char c = StdIn.readChar();
        if (c != '\n') {
            stack.push(c);
            reverseRecursive(stack);
            StdOut.print(stack.pop());
            return 0;
        } else {
            return 0;
        }
    }
}
