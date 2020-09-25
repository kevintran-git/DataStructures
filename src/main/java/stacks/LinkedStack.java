package stacks;

public class LinkedStack<T> implements StackInterface<T>{

    private Node<T> head = null;

    /**
     Node class. Contains two private data fields, the item contained and the successor node in line.
     @author Kevin Tran
     */
    private static class Node<T> {
        private T item;
        private Node<T> succ;
    }

    /**
     * Adds a new entry to the top of this stack.
     *
     * @param newEntry An object to be added to the stack.
     */
    @Override
    public void push(T newEntry) {
        Node<T> oldFirst = head; //Saves previous head to a temporary variable.
        head = new Node<>();
        head.item = newEntry;
        head.succ = oldFirst; //Adds the entry as a new head and links it up with the chain through the old head.
    }

    /**
     * Removes and returns this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws java.util.EmptyStackException if the stack is empty before the operation.
     */
    @Override
    public T pop() {
        if(isEmpty()) throw new java.util.EmptyStackException();
        Node<T> oldFirst = head;
        head = oldFirst.succ;
        return oldFirst.item;
    }

    /**
     * Retrieves this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws java.util.EmptyStackException if the stack is empty.
     */
    @Override
    public T peek() {
        if(isEmpty()) throw new java.util.EmptyStackException();
        return head.item;
    }

    /**
     * Detects whether this stack is empty.
     *
     * @return True if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Removes all entries from this stack.
     */
    @Override
    public void clear() {
        head = null;
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
}
