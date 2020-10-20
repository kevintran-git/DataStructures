package dictionaries;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public final class SortedArrayDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {

    private static class Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K searchKey, V dataValue) {
            key = searchKey;
            value = dataValue;
        }
    }

    @SuppressWarnings("unchecked")
    private Entry<K, V>[] dictionary = (Entry<K, V>[]) new Entry[2];
    private int topIndex = -1;

    /**
     * Doubles the array's capacity if it's full.
     */
    private void ensureCapacity() {
        if (topIndex >= dictionary.length - 1)
            dictionary = Arrays.copyOf(dictionary, 2 * dictionary.length); // If array is full, double its size
    }

    /**
     * Binary search for the desired key. O(log(n)) time.
     *
     * @param key Key to search for index.
     * @return The key's index, or the insertion point as a negative value, the point where the array would have been
     */
    private int binarySearch(K key) {
        int low = 0;
        int high = topIndex;
        while (low <= high) {
            int mid = (low + high) >>> 1; //unsigned bit shift right, exactly the same as (low + high) / 2, however it won't overflow into negative values with larger integers
            @SuppressWarnings("rawtypes")
            Comparable midVal = dictionary[mid].key;
            @SuppressWarnings("unchecked")
            int cmp = midVal.compareTo(key);
            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found. return where the key would have been, adding one, otherwise searching an empty array would return index 0, which cannot be negative.
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
        int index = binarySearch(key);
        if (index >= 0) { //this is a duplicate
            V temp = dictionary[index].value;
            dictionary[index].value = value;
            return temp;
        }
        ensureCapacity(); // this is a new value to insert (not a duplicate).
        index = -index - 1; //subtract the placeholder one we added earlier.
        System.arraycopy(dictionary, index, dictionary, index + 1, topIndex + 1 - index); //Shifts everything in the dictionary right of the insertion point by 1
        dictionary[index] = new Entry<>(key, value); //add the value at the correct index
        topIndex++; //increment the value
        return null;
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
        int index = binarySearch(key);
        if (index < 0) return null;
        V temp = dictionary[index].value;
        System.arraycopy(dictionary, index + 1, dictionary, index, topIndex - index); //Shifts everythng left one, overwrites the deleted value
        topIndex--;
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
        int index = binarySearch(key);
        return index < 0 ? null : dictionary[index].value;
    }

    /**
     * Sees whether a specific entry is in this dictionary.
     *
     * @param key An object search key of the desired entry.
     * @return True if key is associated with an entry in the dictionary.
     */
    @Override
    public boolean contains(K key) {
        return binarySearch(key) >= 0;
    }

    /**
     * Sees whether this dictionary is empty.
     *
     * @return True if the dictionary is empty.
     */
    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    /**
     * Gets the size of this dictionary.
     *
     * @return The number of entries (key-value pairs) currently
     * in the dictionary.
     */
    @Override
    public int getSize() {
        return topIndex + 1;
    }

    /**
     * Removes all entries from this dictionary.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        topIndex = -1;
        dictionary = (Entry<K, V>[]) new Entry[2];
    }


    /**
     * Creates an iterator that traverses all search keys in this dictionary.
     *
     * @return An iterator that provides sequential access to the search
     * keys in the dictionary.
     */
    @Override
    public Iterator<K> getKeyIterator() {
        return Arrays.stream(dictionary).filter(Objects::nonNull).map(k -> k.key).iterator();
    }

    /**
     * Creates an iterator that traverses all values in this dictionary.
     *
     * @return An iterator that provides sequential access to the values
     * in this dictionary.
     */
    @Override
    public Iterator<V> getValueIterator() {
        return Arrays.stream(dictionary).filter(Objects::nonNull).map(k -> k.value).iterator();
    }
}







