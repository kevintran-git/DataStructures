package linear.bags;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 A class of linear.bags whose entries are stored in a chain of linked nodes.
 The bag is never full.
 @author Kevin Tran
 */
public class LinkedBag<T> implements BagInterface<T> {
    //This class has no defined constructor. Just use the default one, we don't need to initialize anything.

    /**
     Node class. Contains two private data fields, the item contained and the successor node in line.
     @author Kevin Tran
     */
    private static class Node<T> {
        private T item;
        private Node<T> succ;
    }

    private Node<T> head = null;
    private int entries = 0;

    /** Adds a new entry to this bag.
     @param newEntry  The object to be added as a new entry.
     @return  True. */
    @Override
    public boolean add(T newEntry) {
        Node<T> oldFirst = head; //Saves previous head to a temporary variable.
        head = new Node<>();
        head.item = newEntry;
        head.succ = oldFirst; //Adds the entry as a new head and links it up with the chain through the old head.
        entries++;
        return true;
    }

    /** Gets the number of entries currently in this bag.
     @return  The integer number of entries currently in the bag. */
    @Override
    public int getCurrentSize() {
        return entries;
    }

    /** Sees whether this bag is empty.
     @return  True if the bag is empty, or false if not. */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /** Removes one unspecified entry from this bag, if possible.
     @return  Either the removed entry, if the removal
     was successful, or null. */
    @Override
    public T remove() {
        if (head == null) return null; //Don't do anything if the list is empty.
        Node<T> temp = head; //Stores the current head
        head = temp.succ; //Assigns the next head to the current one.
        entries--;
        return temp.item;
    }

    /** Removes one occurrence of a given entry from this bag.
     @param anEntry  The entry to be removed.
     @return  True if the removal was successful, or false otherwise. */
    @Override
    public boolean remove(T anEntry) {
        Node<T> temp = head, prev = null;
        if (temp != null && temp.item.equals(anEntry)) { //if the first node is an entry
            head = temp.succ;
            entries--;
            return true;
        }
        while (temp != null && !temp.item.equals(anEntry)) {
            prev = temp;
            temp = temp.succ;
        }
        if (temp == null) return false; //if entry was not found
        if (prev != null) prev.succ = temp.succ; //unlink the deleted node from the bag
        entries--;
        return true;
    }

    /** Removes all entries from this bag. */
    @Override
    public void clear() {
        while (!isEmpty()) remove();
        //alternatively you can use head = null; that would also unlink all nodes. However this ensures the number of entries counter is set to zero as well.
    }

    /** Counts the number of times a given entry appears in this bag.
     @param anEntry  The entry to be counted.
     @return  The number of times anEntry appears in the bag. */
    @Override
    public int getFrequencyOf(T anEntry) {
        Node<T> temp = head;
        int count = 0;
        while (temp != null) {
            if (temp.item.equals(anEntry)) count++;
            temp = temp.succ;
        }
        return count;
    }

    /** Tests whether this bag contains a given entry.
     @param anEntry  The entry to locate.
     @return  True if the bag contains anEntry, or false otherwise. */
    @Override
    public boolean contains(T anEntry) {
        Node<T> temp = head;
        while (temp != null) { //Traverses the link, if it finds the entry it will return true.
            if (temp.item.equals(anEntry)) return true;
            temp = temp.succ;
        }
        return false;
    }

    /** Retrieves all entries that are in this bag.
     @return  A newly allocated array of all the entries in this bag. */
    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[getCurrentSize()];
        int i = 0;
        Node<T> n = head;
        while (n != null) {
            array[i] = n.item; //Traverses the chain, adding each node's item
            n = n.succ; //and then grabbing the next node in line until you run into a node with no next.
            i++;
        }
        return array;
    }

    @Override
    public String toString() { //Here for ease of debugging,
        StringBuilder asString = new StringBuilder();
        Node<T> node = head;
        while (node != null) {
            asString.append(node.item.toString()).append(node.succ != null ? " -> " : "");
            node = node.succ;
        }
        return asString.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T item = current.item;
                current = current.succ;
                return item;
            }
        };
    }
}
