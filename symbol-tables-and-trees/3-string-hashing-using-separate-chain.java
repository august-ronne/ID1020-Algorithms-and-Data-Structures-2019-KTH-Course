import java.util.LinkedList;

/**
 * A generic separate chaining hash ST class consisting of an array
 * of iterable linked lists. The class uses the Java language built in
 * hashCode() method to assign indices to the head nodes of its
 * linked lists.
 * @param <Key> Class of keys
 * @param <Value> Class of values
 */
public class ChainingHash<Key, Value> {
    private static final int STARTING_ARRAY_SIZE = 4;

    private int storedPairs;
    private int arraySize;
    private LinkedListST<Key, Value>[] st;

    public ChainingHash() {
        this(STARTING_ARRAY_SIZE);
    }

    /**
     * Initializes a linked list array with a hardcoded capacity
     * Iterates through the array and creates a new linked list
     * object in each array cell
     * @param arraySize
     */
    public ChainingHash(int arraySize) {
        this.arraySize = arraySize;
        st = (LinkedListST<Key, Value>[]) new LinkedListST[arraySize];
        for (int i = 0; i < arraySize; i++)
            st[i] = new LinkedListST<Key, Value>();
    }

    /**
     * Get the average length of the linked lists
     * of the symbol table
     * @return the average length of the chains
     */
    public double averageChainLength() {
        int totalLengthOfChains = 0;
        for (int i = 0; i < arraySize; i++) {
            totalLengthOfChains += st[i].size();
        }
        return totalLengthOfChains / (arraySize * 1.0);
    }

    /**
     * Get the longest linked list of the symbol table
     * @return the longest chain
     */
    public int[] findLongestChain() {
        int longestChain = 0;
        int index = 0;
        int[] data = new int[2];
        for (int i = 0; i < arraySize; i++) {
            if (st[i].size() > longestChain) {
                longestChain = st[i].size();
                data[0] = st[i].size();
                data[1] = i;
            }
        }
        return data;
    }

    /**
     * Get the number of empty array cells in
     * the symbol table
     * @return the number of empty array cells of the symbol table
     */
    public int numberOfEmptyCells() {
        int numberOfEmptyCells = 0;
        for (int i = 0; i < arraySize; i++) {
            if (st[i].isEmpty()) {
                numberOfEmptyCells++;
            }
        }
        return numberOfEmptyCells;
    }

    /**
     * Resizes the linked list array with a new capacity
     * given by the argument integer
     * @param k the capacity of the new array
     */
    private void resize(int k) {
        ChainingHash<Key, Value> newTable = new ChainingHash<Key, Value>(k);
        for (int i = 0; i < arraySize; i++) {
            for (Key key : st[i].keys()) {
                newTable.put(key, st[i].get(key));
            }
        }
        this.arraySize  = newTable.arraySize;
        this.storedPairs  = newTable.storedPairs;
        this.st = newTable.st;
    }

    /**
     * Generates a non-negative hash value for the
     * argument key. The key is modular to the size
     * of the array
     * @param key
     * @return
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % arraySize;
    }

    /**
     * Get array size
     * @return array size
     */
    public int getArraySize() {
        return arraySize;
    }

    /**
     * Get the number of stored key-value pairs
     * @return number of stored key-value pairs
     */
    public int getStoredKeyValuePairs() {
        return storedPairs;
    }

    /**
     * Check if the symbol table is empty
     * @return {@code true} if empty, {@code false} if not
     */
    public boolean isEmpty() {
        return getStoredKeyValuePairs() == 0;
    }

    /**
     * Check if the symbol table contains the argument key
     * @param key key to be searched for
     * @return {@code true} if key is in table, {@code false if not}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("call to contains() with null key");
        return get(key) != null;
    }

    /**
     * Get the value associated with the argument key
     * @param key key to be searched for
     * @return value associated with argument key
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("call to get() with null key");
        int index = hash(key);
        return st[index].get(key);
    }

    /**
     * Insert the argument key-value pair as a node
     * into the symbol table
     * @param key key to be added
     * @param value value associated with key
     */
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("call to put() with null key");
        if (storedPairs >= 10 * arraySize) resize(arraySize * 2);
        int index = hash(key);
        if (!st[index].contains(key)) {
            storedPairs++;
        }
        st[index].put(key, value);
    }

    /**
     * Returns an iterable list of all keys in the symbol table
     * @return an iterable list of the symbol table keys
     */
    public Iterable<Key> keys() {
        LinkedListQueue<Key> list = new LinkedListQueue<Key>();
        for (int i = 0; i < arraySize; i++) {
            for (Key key : st[i].keys()) {
                list.add(key);
            }
        }
        return list;
    }
}