package queues;

public class QueueDemo {
    public static void main(String... args) {
        WaitLine myLine = new WaitLine();
        myLine.simulate(6969, 1/69.0, 69);
        myLine.displayResults();
        System.out.println("-------------------------------------------------------------------");
        queueTests();
    }

    public static void queueTests(){
        QueueInterface<String> myQueue = new LinkedQueue<>();
        myQueue.enqueue("Jada");
        myQueue.enqueue("Jess");
        myQueue.enqueue("Jazmin");
        myQueue.enqueue("Jorge");
        myQueue.enqueue("Jamal");

        String front = myQueue.getFront(); // Returns "Jada"
        System.out.println(front + " is at the front of the queue.");

        front = myQueue.dequeue(); // Removes and returns "Jada"
        System.out.println(front + " is removed from the queue.");

        myQueue.enqueue("Jerry"); // Adds "Jerry"

        front = myQueue.getFront(); // Returns "Jess"
        System.out.println(front + " is at the front of the queue.");

        front = myQueue.dequeue(); // Removes and returns "Jess"
        System.out.println(front + " is removed from the queue.");

        myQueue.clear();
        myQueue.enqueue("JJJJJJJJJJJJ");
        System.out.println(myQueue.isEmpty() + ": Is the queue empty? Should return false.");
        front = myQueue.getFront(); // Returns "JJJJJJJJJJJJ"
        System.out.println(front + " is at the front of the queue.");

        myQueue.dequeue();
        System.out.println(myQueue.isEmpty() + ": Is the queue empty? Should return true.");

        try {
            myQueue.dequeue();
        } catch (EmptyQueueException e) {
            System.out.println("Task failed successfully.");
        }
        try {
            myQueue.getFront();
        } catch (EmptyQueueException e) {
            System.out.println("Task failed successfully.");
        }


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
            } catch (EmptyQueueException e) {
                //do nothing we don't care lol
            }
            myQueueueueueueue.enqueue("foo");
            myQueueueueueueue.enqueue("bar");
            myQueueueueueueue.enqueue("qux");
        }
        for (int i = 0; i < 3; i++) System.out.println(myQueueueueueueue.dequeue()); //should print foo\n bar\n qux\n
    }
}
