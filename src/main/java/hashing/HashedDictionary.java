package hashing;

import dictionaries.DictionaryInterface;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;

public final class HashedDictionary<K, V> implements DictionaryInterface<K, V> {

    @SuppressWarnings("unchecked")
    private Entry<K, V>[] table = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
    private static final int DEFAULT_CAPACITY = 11;
    private int entries = 0;
    private static final double MAX_LOAD_FACTOR = 0.5;

    /**
     * probe method detects whether key collides with hashTable[index]
     * and resolves it by following a quadratic probe sequence.
     *
     * @param key Key to probe for
     * @return the index of an element along the probe sequence that is
     * either available or contains the entry whose search key is key.
     * This index is always legal, since the probe sequence stays within
     * the hash table.
     */
    private int probe(K key) {
        int index = key.hashCode() % table.length < 0 ? key.hashCode() % table.length + table.length : key.hashCode() % table.length;
        int removedStateIndex = -1; // Index of first location in removed state
        int increment = 1;          // Increment will increase, this is quadratic probing

        while (table[index] != null) if (!table[index].available) {
            if (key.equals(table[index].key)) return index; // Key found
            index = (index + increment) % table.length; // Quadratic probing
            increment = increment + 2;                      // Increase the increment, constant second difference is quadratic
        } else { // Skip entries that were removed
            if (removedStateIndex == -1) removedStateIndex = index; // Save index of first location in removed state
            index = (index + increment) % table.length;    // Quadratic probing
            increment = increment + 2;                         // Increase the increment, constant second difference is quadratic
        }
        return removedStateIndex == -1 ? index : removedStateIndex; //Return the location of search key or desired location if not found
    }

    /**
     * uses the quadratic sequence to locate a search key's index
     * @param key Key to search for
     * @return The index that contains the search key, or -1 if search key was not found
     */
    private int locate(K key) {
        int index = key.hashCode() % table.length < 0 ? key.hashCode() % table.length + table.length : key.hashCode() % table.length;
        int increment = 1; // Quadratic probing

        while (table[index] != null) {
            if (!table[index].available && key.equals(table[index].key)) return index; // Key found
            index = (index + increment) % table.length; // Quadratic probing
            increment = increment + 2;// Increase the increment, constant second difference is quadratic
        }
        return -1; //Key not found.
    }

    /**
     * Resizes and rehashes the internal array to the a prime number >= twice the old array length. if the load factor is exceeded. Does nothing otherwise.
     */
    @SuppressWarnings("unchecked")
    private void attemptResize() {
        if (entries <= MAX_LOAD_FACTOR * table.length) return;
        Entry<K, V>[] old = table;
        table = (Entry<K, V>[]) new Entry[Integer.parseInt(new BigInteger(String.valueOf(2 * old.length)).nextProbablePrime().toString())];
        entries = 0;
        for (Entry<K, V> kvEntry : old) if ((kvEntry != null) && !kvEntry.available) add(kvEntry.key, kvEntry.value);
    }

    /**
     * Adds a new entry to this dictionary. If the given search key already
     * exists in the dictionary, replaces the corresponding value.
     *
     * @param key   An object search key of the new entry.
     * @param value An object associated with the search key.
     * @return Either null if the new entry was added to the dictionary
     * or the value that was associated with key if that value
     * was replaced.
     */
    @Override
    public V add(K key, V value) {
        if ((key == null) || (value == null)) throw new IllegalArgumentException("Cannot add null to a dictionary.");
        int index = probe(key);
        if (table[index] == null || table[index].available) { //Entry not found
            table[index] = new Entry<>(key, value); //Add new entry at desired index via probe
            entries++;
            attemptResize(); //resize if load factor is too high
            return null;
        }
        V temp = table[index].value; //Entry found, replace the value
        table[index].value = value;
        return temp;
    }

    /**
     * Removes a specific entry from this dictionary.
     *
     * @param key An object search key of the entry to be removed.
     * @return Either the value that was associated with the search key
     * or null if no such object exists.
     */
    @Override
    public V remove(K key) {
        int index = locate(key);
        if (index == -1) return null; //do nothing if search key doesn't exist
        V temp = table[index].value;
        table[index].available = true; //set available flag to true, class will treat entry as inaccessible except for probing purposes
        entries--;
        return temp;
    }

    /**
     * Retrieves from this dictionary the value associated with a given
     * search key.
     *
     * @param key An object search key of the entry to be retrieved.
     * @return Either the value that is associated with the search key
     * or null if no such object exists.
     */
    @Override
    public V getValue(K key) {
        int index = locate(key);
        return index == -1 ? null : table[index].value;
    }

    /**
     * Sees whether a specific entry is in this dictionary.
     *
     * @param key An object search key of the desired entry.
     * @return True if key is associated with an entry in the dictionary.
     */
    @Override
    public boolean contains(K key) {
        return getValue(key) != null;
    }

    /**
     * Sees whether this dictionary is empty.
     *
     * @return True if the dictionary is empty.
     */
    @Override
    public boolean isEmpty() {
        return entries < 1;
    }

    /**
     * Gets the size of this dictionary.
     *
     * @return The number of entries (key-value pairs) currently
     * in the dictionary.
     */
    @Override
    public int getSize() {
        return entries;
    }

    /**
     * Removes all entries from this dictionary.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        entries = 0;
        table = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY]; //replace with empty array
    }

    /**
     * Creates an iterator that traverses all search keys in this dictionary.
     *
     * @return An iterator that provides sequential access to the search
     * keys in the dictionary.
     */
    @Override
    public Iterator<K> getKeyIterator() {
        return Arrays.stream(table).filter(x -> x != null && !x.available).map(k -> k.key).iterator(); //iterate through all elements that aren't null or removed
    }

    /**
     * Creates an iterator that traverses all values in this dictionary.
     *
     * @return An iterator that provides sequential access to the values
     * in this dictionary.
     */
    @Override
    public Iterator<V> getValueIterator() {
        return Arrays.stream(table).filter(x -> x != null && !x.available).map(k -> k.value).iterator(); //iterate through all elements that aren't null or removed
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; i < table.length; i++)
            if (table[i] != null && !table[i].available) {
                sb.append(table[i].key.toString()).append("[").append(i).append("]=").append(table[i].value.toString());
                if (i < table.length - 3) sb.append(", ");
            }
        return sb.append('}').toString();
    }

    public void displayHashTable() {
        System.out.println(toString());
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private boolean available = false; //A boolean representing a value that has been added in the past but removed.

        private Entry(K searchKey, V dataValue) {
            key = searchKey;
            value = dataValue;
        }
    }
}







