public class LinearHash<Key, Value> {
    private static final int STARTING_ARRAYSIZE = 4;
    private int storedPairs;
    private int arraySize;
    private Key[] keys;
    private Value[] values;

    /**
     * Constructor calling second constructor
     * with hardcoded array capacity as argument
     */
    public LinearHash() {
        this(STARTING_ARRAYSIZE);
    }

    /**
     * Creates an empty linear hash symbol table
     * @param arraySize the starting capacity of the array
     */
    public LinearHash(int arraySize) {
        this.arraySize = arraySize;
        this.storedPairs = 0;
        this.keys = (Key[]) new Object[arraySize];
        this.values = (Value[]) new Object[arraySize];
    }

    /**
     * Get the size of the symbol table
     * @return the number of key-value pairs in the table
     */
    public int size() {
        return storedPairs;
    }

    /**
     * Check if the symbol table is empty
     * @return {@code true} if empty, {@code false} if not
     */
    public boolean isEmpty() {
        return storedPairs == 0;
    }

    /**
     * Check if the argument key is currently stored in
     * the symbol table
     * @param key the key to be searched for
     * @return {@code true} if key is in the table, {@code false} if not
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("call to contains() with null key");
        }
        return get(key) != null;
    }

    /**
     * Produce a hash value for the argument key
     * using Java's built in hashCode method
     * @param key the key for which we want to generate a hash value
     * @return the hash value of the argument key
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % arraySize;
    }

    /**
     * Returns the value associated with the argument key
     * @param key key to be searched for
     * @return the value associated with the key, {@code null} if no such key
     *         is found in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("call to get() with null key");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % arraySize) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    /**
     * Store the argument key-value pair in the symbol table.
     * If the key is already present the value is updated
     * using the argument value
     * @param key the key to be searched for
     * @param value the value associated with the argument key
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("call to put() with null key");
        }
        if (storedPairs >= (arraySize / 2)) {
            resize(2 * arraySize);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % arraySize) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        storedPairs++;
    }

    /**
     * Creates a new symbol table with size defined by
     * argument k, and then copies the content of the current
     * symbol table to the new, larger table
     * @param k the size of the symbol table
     */
    private void resize(int k) {
        LinearHash<Key, Value> newTable = new LinearHash<Key, Value>(k);
        for (int i = 0; i < arraySize; i++) {
            if (keys[i] != null) {
                newTable.put(keys[i], values[i]);
            }
        }
        keys = newTable.keys;
        values = newTable.values;
        arraySize = newTable.arraySize;
    }

    /**
     * Returns an iterable list containing all keys
     * currently stored in the symbol table
     * @return an iterable {@code LinkedListQueue} containing the keys
     *         of the symbol table
     */
    public Iterable<Key> keys() {
        LinkedListQueue<Key> list = new LinkedListQueue<Key>();
        for (int i = 0; i < arraySize; i++) {
            if (keys[i] != null) {
                list.add(keys[i]);
            }
        }
        return list;
    }
}
