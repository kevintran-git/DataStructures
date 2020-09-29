package queues;

public class LinkedQueue<T> implements QueueInterface<T> {

    /**
     * Node class. Contains two private data fields, the item contained and the successor node in line.
     *
     * @author Kevin Tran
     */
    private static class Node<T> {
        private T item;
        private Node<T> succ;
    }

    private Node<T> firstNode = null; // References node at front of queue
    private Node<T> lastNode = null;  // References node at back of queue

    /**
     * Adds a new entry to the back of this queue.
     *
     * @param newEntry An object to be added.
     */
    @Override
    public void enqueue(T newEntry) {
        Node<T> newNode = new Node<>();
        newNode.item = newEntry;
        if (isEmpty())
            firstNode = newNode;
        else
            lastNode.succ = newNode;
        lastNode = newNode;
    }

    /**
     * Removes and returns the entry at the front of this queue.
     *
     * @return The object at the front of the queue.
     * @throws EmptyQueueException if the queue is empty before the operation.
     */
    @Override
    public T dequeue() {
        T front = getFront();  // Might throw EmptyQueueException
        // Assertion: firstNode != null
        firstNode = firstNode.succ;
        if (firstNode == null)
            lastNode = null;
        return front;
    }

    /**
     * Retrieves the entry at the front of this queue.
     *
     * @return The object at the front of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    @Override
    public T getFront() {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return firstNode.item;
    }

    /**
     * Detects whether this queue is empty.
     *
     * @return True if the queue is empty, or false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null);
    }

    /**
     * Removes all entries from this queue.
     */
    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
    }
}
