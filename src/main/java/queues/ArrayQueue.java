package queues;


public final class ArrayQueue<T> implements QueueInterface<T> {
    private T[] queue; // Circular array of queue entries and one unused element
    private int frontIndex;
    private int backIndex;
    private boolean integrityOK;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    public ArrayQueue(int initialCapacity) {
        integrityOK = false;
        checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        backIndex = initialCapacity;
        integrityOK = true;
    }

    /**
     * Adds a new entry to the back of this queue.
     *
     * @param newEntry An object to be added.
     */
    @Override
    public void enqueue(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
    }

    /**
     * Removes and returns the entry at the front of this queue.
     *
     * @return The object at the front of the queue.
     * @throws EmptyQueueException if the queue is empty before the operation.
     */
    @Override
    public T dequeue() {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyQueueException();
        else {
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            return front;
        } // end if
    }

    /**
     * Retrieves the entry at the front of this queue.
     *
     * @return The object at the front of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    @Override
    public T getFront() {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return queue[frontIndex];
    }

    /**
     * Detects whether this queue is empty.
     *
     * @return True if the queue is empty, or false otherwise.
     */
    @Override
    public boolean isEmpty() {
        checkIntegrity();
        return frontIndex == ((backIndex + 1) % queue.length);
    }

    /**
     * Removes all entries from this queue.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        queue = (T[]) new Object[DEFAULT_CAPACITY + 1];
        frontIndex = 0;
        backIndex = DEFAULT_CAPACITY;
    }

    private void ensureCapacity() {
        if (frontIndex == ((backIndex + 2) % queue.length)) // If array is full,
        {                                                   // double size of array
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize);
            integrityOK = false;

            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            queue = tempQueue;
            for (int index = 0; index < oldSize - 1; index++) {
                queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            }

            frontIndex = 0;
            backIndex = oldSize - 2;
            integrityOK = true;
        }
    }

    private boolean checkIntegrity(){
        return integrityOK;
    }

    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY) throw new IllegalStateException("no");
    }
}

