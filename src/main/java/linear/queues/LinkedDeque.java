package linear.queues;

public class LinkedDeque<T> implements DequeInterface<T> {

    private static class Node<T> { //Doubly linked nodes.
        private Node<T> pred, succ;
        private T item;
    }

    private Node<T> head = null, tail = null;

    /**
     * Adds a new entry to the front of this deque.
     *
     * @param newEntry An object to be added.
     */
    @Override
    public void addToFront(T newEntry) {
        Node<T> newNode = new Node<>();
        newNode.item = newEntry;
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.succ = head; //Add to the head
            head.pred = newNode;
            head = newNode;
        }
    }

    /**
     * Adds a new entry to the back of this deque.
     *
     * @param newEntry An object to be added.
     */
    @Override
    public void addToBack(T newEntry) {
        Node<T> newNode = new Node<>();
        newNode.item = newEntry;
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.pred = tail; //Add to the tail
            tail.succ = newNode;
            tail = newNode;
        }
    }

    /**
     * Removes and returns the front entry of this deque.
     *
     * @return The object at the front  of the deque.
     * @throws EmptyQueueException if the deque is empty before the
     *                             operation.
     */
    @Override
    public T removeFront() {
        T front = getFront();
        head = head.succ;
        if (head == null)
            tail = null;
        else
            head.pred = null;
        return front;
    }

    /**
     * Removes and returns the back entry of this deque.
     *
     * @return The object at the back of the deque.
     * @throws EmptyQueueException if the deque is empty before the
     *                             operation.
     */
    @Override
    public T removeBack() {
        T back = getBack();
        tail = tail.pred;
        if (tail == null)
            head = null;
        else
            tail.succ = null;
        return back;
    }

    /**
     * Retrieves the front entry of this deque.
     *
     * @return The object at the front of the deque.
     * @throws EmptyQueueException if the deque is empty.
     */
    @Override
    public T getFront() {
        if (isEmpty()) throw new EmptyQueueException();
        return head.item;
    }

    /**
     * Retrieves the back entry of this deque.
     *
     * @return The object at the back of the deque.
     * @throws EmptyQueueException if the deque is empty.
     */
    @Override
    public T getBack() {
        if (isEmpty()) throw new EmptyQueueException();
        return tail.item;
    }

    /**
     * Detects whether this deque is empty.
     *
     * @return True if the deque is empty, or false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Removes all entries from this deque.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    private void display(Node<T> next) {
        if (next != null) {
            display(next.pred);
            System.out.println(next.item);
        }
    }

    public void display(){
        display(tail);
    }
}
