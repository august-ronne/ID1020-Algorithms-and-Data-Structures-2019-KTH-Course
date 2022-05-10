/**
 * Compilation: javac LinkedStack.java
 * Execution: java LinkedStack < input.txt
 *      reading a .txt file is optional, the user can also enter input manually
 * Dependencies: StdIn.java, StdOut.java
 *
 * A generic stack built using a linked list. The nature
 * of a stack, meaning it's LIFO order, is used to determine
 * whether the String entered by the user has balanced parentheses.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class LinkedStack<Element> {
    private int size;       // current size of the Stack
    private Node startingNode;     // the most recently added Node of the Stack

    /**
     * The Stack is constructed by Node objects.
     * The Node stores an Element and is linked to the next
     * node in the Stack.
     */
    private class Node {
        private Element elem;
        private Node next;
    }

    /**
     * Initializes a new Stack
     */
    public LinkedStack() {
        startingNode = null;
        size = 0;
    }

    /**
     * Checks if the Stack is empty
     * @return true if the stack is empty, false if not
     */
    public boolean isEmpty() {
        return startingNode == null;
    }

    /**
     * Returns the amount of Elements currently stored in the Stack.
     * @return the amount of Elements in the stack
     */
    public int size() {
        return size;
    }

    /**
     * Adds an Element to the top of the Stack.
     * @param elem the Element to be added
     */
    public void push(Element elem) {
        Node oldStartingNode = startingNode;
        startingNode = new Node();
        startingNode.elem = elem;
        startingNode.next = oldStartingNode;
        size++;
    }

    /**
     * Removes the most recently added Node of the Stack,
     * and returns the Element stored in the Node.
     * @return elem the most recently added Element on the Stack
     */
    public Element pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Element elem = startingNode.elem;
        startingNode = startingNode.next;
        size--;
        return elem;
    }

    /**
     * Tests if the String input by the user have balanced parentheses.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        LinkedStack<Character> stack = new LinkedStack<Character>();
        boolean balanced = true;
        while (true) {
            Character c = StdIn.readChar();
            if (c.equals('\n')) {
                break;
            }

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')') {
                if (stack.pop() != '(' || stack.isEmpty()) {
                    balanced = false;
                }
            }
            else if (c == ']') {
                if (stack.pop() != '[' || stack.isEmpty()) {
                    balanced = false;
                }
            }
            else if (c == '}') {
                if (stack.pop() != '{' || stack.isEmpty()) {
                    balanced = false;
                }
            }
        }
        balanced = stack.isEmpty();
        if (balanced) {
            StdOut.println("The parentheses are balanced.");
        } else {
            StdOut.println("The parentheses are not balanced.");
        }
    }
}
