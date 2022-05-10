/************************************************************************
 * README
 *
 * Dependencies: StdIn.java, StdOut.java
 *
 * A Symbol Table of key-value pairs constructed using ordered arrays.
 *
 * EXAMPLE EXECUTION:
 * Supply the following .txt file to the program:
 * https://algs4.cs.princeton.edu/31elementary/tinyST.txt
 *
 * The output will then read as follows:
 * A 8
 * C 4
 * E 12
 * H 5
 * L 11
 * M 9
 * P 10
 * R 3
 * S 0
 * X 7
 ***************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Implementation of a key-value pair symbol table. The keys
 * and values are generic but it is required that the {@code key}
 * implements the {@code Comparable} interface. The BinarySearchST class
 * supports the following self-explanatory methods:
 * <em>put</em>
 * <em>get</em>
 * <em>contains</em>
 * It also supports these methods:
 * <em>min</em> retrieve the smallest key
 * <em>max</em> retrieve the largest key
 * <em>keys</em> iterate over all the keys current stored in the ST
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] values;
    private int size = 0;

    /**
     * Constructor for empty symbol table,
     * in turn calls constructor with capacity argument
     */
    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    /** Constructor for new symbol table with
     * the program's defined initial capacity
     * @param capacity the capacity of a new symbol table object
     */
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Comparable[capacity];
    }

    /**
     * Resize the arrays storing our keys and values
     * @param capacity the capacity of the new arrays to be created
     */
    private void resize(int capacity) {
        Key[] tempkeys = (Key[]) new Comparable[capacity];
        Value[] tempvalues = (Value[]) new Comparable[capacity];
        for (int i = 0; i < size; i++) {
            tempkeys[i] = keys[i];
            tempvalues[i] = values[i];
        }
        keys = tempkeys;
        values = tempvalues;
    }

    /**
     * Return the number of key-value pairs currently stored in the symbol table
     * @return the number of key-value pairs currently stored in the symbol table
     */
    public int size() {
        return size;
    }

    /**
     * Check is the symbol table is empty
     * @return {@code true} is empty
     *         {@code false} not empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Check if the symbol table contains the argument key
     * @param key the key to be searched for
     * @return {@code true} if ST contains {@code key}
     *         {@code false} if not
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("arg for contains() is null.");
        }
        return get(key) != null;
    }

    /**
     * Retrieves the value matched with the argument key
     * @param key the key to be searched for
     * @return the value matched with the {@code key} and
     *         {@code null} if the key is not in the ST
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("arg for get() is null.");
        }
        if (isEmpty()) {
            return null;
        }
        int index = rank(key);
        if (index < size && keys[index].compareTo(key) == 0) {
            return values[index];
        }
        return null;
    }

    /**
     * Adds the argument key-value pair to the symbol table.
     * If the key is already present in the table the old value
     * is replaced with the new argument value
     * @param key the key to be added
     * @param value the value associated with the argument key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("first arg of put() is null.");
        }
        int index = rank(key);
        if (index < size && keys[index].compareTo(key) == 0) {
            values[index] = value;
            return;
        }
        if (size == keys.length) {
            resize(2 * keys.length);
        }
        for (int j = size; j > index; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[index] = key;
        values[index] = value;
        size++;
    }

    /**
     * Uses Binary Search to find the index of the specified key
     * @param key the key to be searched for
     * @return the index of the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("arg for rank() is null.");
        }
        int lowerBoundary = 0, higherBoundary = size - 1;
        while (lowerBoundary <= higherBoundary) {
            int middle = lowerBoundary + ((higherBoundary - lowerBoundary) / 2);
            int comparison = key.compareTo(keys[middle]);
            if (comparison < 0) {
                higherBoundary = middle - 1;
            } else if (comparison > 0) {
                lowerBoundary = middle + 1;
            } else {
                return middle;
            }
        }
        return lowerBoundary;
    }

    /**
     * Get the smallest key of the ST
     * @return smallest {@code key} in the ST
     * @throws NoSuchElementException if the ST is empty
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("call to min() with empty ST");
        }
        return keys[0];
    }

    /**
     * Get the largest key of the ST
     * @return largest {@code key} in the ST
     * @throws NoSuchElementException if the ST is empty
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("call to max() with empty ST");
        }
        return keys[size - 1];
    }

    /**
     * Retrieves all the keys in the ST by
     * creating an {@code Iterable} object consisting of
     * all our keys
     * @return an Iterable object containing all the keys of the symbol table
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Creates an {@code Iterable} object containing all
     * the keys in the given range.
     * @param lo lower bound of iteration
     * @param hi upper bound of iteration
     * @return an Iterable object containing all keys in the range given
     *         by {@code lo} and {@code hi}
     * @throws IllegalArgumentException if {@code lo} or {@code hi} is null
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first arg of keys() is null.");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second arg of keys() is null.");
        }
        LinkedListQueue<Key> list = new LinkedListQueue<Key>();
        if (lo.compareTo(hi) > 0) {
            return list;
        }
        for (int i = rank(lo); i < rank(hi); i++) {
            list.add(keys[i]);
        }
        if (contains(hi)) {
            list.add(keys[rank(hi)]);
        }
        return list;
    }

    /**
     * Uses shellsort to sort the values in ascending order,
     * and match the keys accordingly. greater() and exch()
     * are helper methods
     */
    private void shellSortST() {
        int gap = 1;
        while (gap < size / 3) gap = 3 * gap + 1;

        while (gap >= 1) {
            for (int i = gap; i < size; i++) {
                for (int j = i; j >= gap && greater(values[j], values[j - gap]); j -= gap) {
                    swap(values, j, j - gap);
                    swap(keys, j,  j - gap);
                }
            }
            gap /= 3;
        }
    }

    private boolean greater(Comparable first, Comparable second) {
        return first.compareTo(second) > 0;
    }

    private void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void nToMIntervalFrequency(int n, int m) {
        shellSortST();
        System.out.println("You have asked the program to show you the" +
                " " + n + " to " + m + " most common words.\n");
        for (int i = n - 1; i <= m - 1; i++) {
            System.out.println((i + 1) + ". '" + keys[i] + "', " + values[i] + " occurrences.");
        }
    }
}
