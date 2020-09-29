package queues;

public class QueueDemo {
    public static void main(String... args){

        QueueInterface<String> myQueue = new ArrayQueue<>();
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


        QueueInterface<String> myQueueueueueueue = new ArrayQueue<>();

        for(int i = 0; i < 100; i++){
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
        for(int i = 0; i < 3; i++)System.out.println(myQueueueueueueue.dequeue());


    }
}
