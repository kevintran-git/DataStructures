package queues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTests {
    @Test
    void simulate() {
        WaitLine myLine = new WaitLine();
        myLine.simulate(6969, 1/69.0, 69);
        myLine.displayResults();
    }

    @Test
    void assertionTests() {
        QueueInterface<String> myQueue = new LinkedQueue<>();
        myQueue.enqueue("Jada");
        myQueue.enqueue("Jess");
        myQueue.enqueue("Jazmin");
        myQueue.enqueue("Jorge");
        myQueue.enqueue("Jamal");

        String front = myQueue.getFront(); // Returns "Jada"
        assertEquals("Jada", front);

        front = myQueue.dequeue(); // Removes and returns "Jada"
        assertEquals("Jada", front);

        myQueue.enqueue("Jerry"); // Adds "Jerry"

        front = myQueue.getFront(); // Returns "Jess"
        assertEquals("Jess", front);

        front = myQueue.dequeue(); // Removes and returns "Jess"
        assertEquals("Jess", front);

        myQueue.clear();
        myQueue.enqueue("Jennifer");
        assertFalse(myQueue.isEmpty());
        front = myQueue.getFront();
        assertEquals("Jennifer", front);

        myQueue.dequeue();
        assertTrue(myQueue.isEmpty());

        assertThrows(EmptyQueueException.class, myQueue::dequeue);
        assertThrows(EmptyQueueException.class, myQueue::getFront);


        QueueInterface<String> myQueueueueueueue = new ArrayQueue<>();
        /*
        Testing circular array logic. Array queues will "shift" in position, and this one will shift out of the default capacity
        if it's not taken care of. However, with modular arithmetic, this will be able to loop back to the front of the array if it exceeds the array's length.
         */
        for (int i = 0; i < 100; i++) {
            try {
                myQueueueueueueue.dequeue();
                myQueueueueueueue.dequeue();
                myQueueueueueueue.dequeue();
            } catch (EmptyQueueException ignored) {
            }
            myQueueueueueueue.enqueue("foo");
            myQueueueueueueue.enqueue("bar");
            myQueueueueueueue.enqueue("qux");
        }
        assertEquals("foo", myQueueueueueueue.dequeue());
        assertEquals("bar", myQueueueueueueue.dequeue());
        assertEquals("qux", myQueueueueueueue.dequeue());
    }
}