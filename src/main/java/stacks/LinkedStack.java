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

    @Override
    public void push(T newEntry) {
        Node<T> oldFirst = head; //Saves previous head to a temporary variable.
        head = new Node<>();
        head.item = newEntry;
        head.succ = oldFirst; //Adds the entry as a new head and links it up with the chain through the old head.
    }

    @Override
    public T pop() {
        if(isEmpty()) return null;
        Node<T> oldFirst = head;
        head = oldFirst.succ;
        return oldFirst.item;
    }

    @Override
    public T peek() {
        return head.item;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

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
