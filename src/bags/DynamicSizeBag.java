package bags;

import java.util.Arrays;
import java.util.Iterator;

public class DynamicSizeBag<T> implements BagInterface<T> {

    private T[] bag;
    private int entries;
    private static final int MAX_CAPACITY = 32767;
    private boolean integrityOK = false;

    public DynamicSizeBag() {
        bag = (T[]) new Object[2];
        entries = 0;
        integrityOK = true;
    }

    @Override
    public boolean add(T newEntry) {
        if (newEntry == null) throw new IllegalArgumentException("item must be non-null");
        checkIntegrity();
        if (isArrayFull()) {
            doubleCapacity();
        }
        bag[entries] = newEntry;
        entries++;
        return true;
    }

    private void doubleCapacity() {
        int newLength = entries * 2;
        if (newLength > MAX_CAPACITY * 2) throw new IllegalStateException("BAG TOO BIG");
        @SuppressWarnings("unchecked")
        T[] newBag = (T[]) new Object[newLength];
        bag = Arrays.copyOf(bag, newLength);
    }

    private void checkIntegrity() {
        if (!integrityOK) throw new SecurityException("bags.ArrayBag object is corrupt.");
    }

    private boolean isArrayFull() {
        return entries == bag.length;
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(bag, entries);
    }

    @Override
    public int getCurrentSize() {
        return entries;
    }

    @Override
    public boolean isEmpty() {
        return entries == 0;
    }

    @Override
    public T remove() {
        checkIntegrity();
        return removeEntry(entries - 1);
    }

    @Override
    public boolean remove(T anEntry) {
        checkIntegrity();
        return anEntry.equals(removeEntry(getIndexOf(anEntry)));
    }

    private T removeEntry(int index) {
        T result = null;
        if (!isEmpty() && index >= 0) {
            result = bag[index];
            bag[index] = bag[entries - 1];
            bag[entries - 1] = null;
            entries--;
        }
        return result;
    }

    private int getIndexOf(T entry) {
        for (int i = 0; i < bag.length; i++) if (bag[i] != null && bag[i].equals(entry)) return i;
        return -1;
    }

    @Override
    public void clear() {
        while (!isEmpty()) remove();
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        checkIntegrity();
        int count = 0;
        for (T e : bag) if (anEntry.equals(e)) count++;
        return count;
    }

    @Override
    public boolean contains(T anEntry) {
        checkIntegrity();
        return getIndexOf(anEntry) > -1;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return Arrays.asList(bag).iterator();
    }
}
