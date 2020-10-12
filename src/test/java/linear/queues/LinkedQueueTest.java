package linear.queues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedQueueTest {

    @Test
    void enqueue() {
        assertAll(() ->{
            QueueInterface <String> myQueue = new LinkedQueue<>();
            myQueue.enqueue("Jill");
            myQueue.enqueue("Jack");
            myQueue.enqueue("Josh");
            myQueue.enqueue("Jane");
        });
    }

    @Test
    void getFront() {
        QueueInterface <String> myQueue = new LinkedQueue<>();
        assertThrows(EmptyQueueException.class, myQueue::getFront);

        myQueue.enqueue("Jill");
        myQueue.enqueue("Jack");
        myQueue.enqueue("Josh");
        myQueue.enqueue("Jane");

        assertEquals("Jill", myQueue.getFront());
        assertEquals("Jill", myQueue.getFront());
    }

    @Test
    void dequeue() {
        QueueInterface <String> myQueue = new LinkedQueue<>();
        assertThrows(EmptyQueueException.class, myQueue::getFront);

        myQueue.enqueue("Jill");
        myQueue.enqueue("Jack");
        myQueue.enqueue("Josh");
        myQueue.enqueue("Jane");

        assertEquals("Jill", myQueue.dequeue());
        assertEquals("Jack", myQueue.dequeue());
    }

    @Test
    void isEmpty() {
        QueueInterface <String> myQueue = new LinkedQueue<>();
        assertTrue(myQueue.isEmpty());
        myQueue.enqueue("Jill");
        myQueue.enqueue("Jack");
        myQueue.enqueue("Josh");
        myQueue.enqueue("Jane");
        assertFalse(myQueue.isEmpty());
    }

    @Test
    void clear() {
        QueueInterface <String> myQueue = new LinkedQueue<>();
        myQueue.enqueue("Jill");
        myQueue.enqueue("Jack");
        assertAll(myQueue::clear);
        assertTrue(myQueue::isEmpty);
    }
}