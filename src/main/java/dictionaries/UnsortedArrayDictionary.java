package dictionaries;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public final class UnsortedArrayDictionary<K, V> implements DictionaryInterface<K, V> {

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
     * Linear search for the desired key. O(n) time.
     *
     * @param key Key to search for index.
     * @return The key's index, or -1 if no matching key is found.
     */
    private int findIndex(K key) {
        for (int i = 0; i <= topIndex; i++) if (dictionary[i].key.equals(key)) return i;
        return -1;
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
        int index = findIndex(key);
        if (index == -1) {
            ensureCapacity();
            dictionary[topIndex + 1] = new Entry<>(key, value);
            topIndex++;
            return null;
        } else {
            V temp = dictionary[index].value;
            dictionary[index].value = value;
            return temp;
        }
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
        int index = findIndex(key);
        if (index == -1) return null;
        V temp = dictionary[index].value;
        dictionary[index] = dictionary[topIndex];
        dictionary[topIndex] = null;
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
        int index = findIndex(key);
        return index == -1 ? null : dictionary[index].value;
    }

    /**
     * Sees whether a specific entry is in this dictionary.
     *
     * @param key An object search key of the desired entry.
     * @return True if key is associated with an entry in the dictionary.
     */
    @Override
    public boolean contains(K key) {
        return findIndex(key) != -1;
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
/*        return new Iterator<K>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index <= topIndex;
            }

            @Override
            public K next() {
                K result = null;
                if (hasNext()) {
                    Entry<K, V> currentEntry = dictionary[index];
                    result = currentEntry.key;
                    index++;
                } else throw new NoSuchElementException();
                return result;
            }
        };*/
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
/*        return new Iterator<V>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index <= topIndex;
            }

            @Override
            public V next() {
                V result = null;
                if (hasNext()) {
                    Entry<K, V> currentEntry = dictionary[index];
                    result = currentEntry.value;
                    index++;
                } else throw new NoSuchElementException();
                return result;
            }
        };*/
    }
}







