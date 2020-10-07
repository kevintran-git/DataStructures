package queues;

public class AirportSimulation {
    private static class Airplane {
        static int prevID = 0;

        private final String airline = new String[]{"Delta", "Jetblue", "American", "Southwest", "United", "Frontier", "Spirit"}[(int) (Math.random() * 7)];
        private final int id = ++prevID;

        public boolean isLanding() {
            return isLanding;
        }

        public int getOperationTime() {
            return operationTime;
        }

        private final boolean isLanding; //gives priority
        private final int operationTime; //amount of time it takes to take off or land

        public int getStartTime() {
            return startTime;
        }

        private final int startTime;

        /**
         *
         * @param propLanding likelyhood the plane will land instead of taking off
         * @param operationTime amount of time plane takes to land or take off
         * @param startTime time the plane enters the queue
         */
        public Airplane(double propLanding, int operationTime, int startTime) {
            isLanding = Math.random() < propLanding;
            this.operationTime = operationTime;
            this.startTime = startTime;
        }

        public String toString() {
            return airline + " " + id;
        }
    }

    private DequeInterface<Airplane> planes = new LinkedDeque<>();
    private int numberOfPlanes;
    private int numberArrived;
    private int numberDeparted;
    private int totalTimeWaited;

    /**
     * Simulates a waiting line with one serving agent.
     *
     * @param duration           The number of simulated minutes.
     * @param arrivalProbability A real number between 0 and 1, and the
     *                           probability that a customer arrives at
     *                           a given time
     * @param maxOperationTime The longest landing or taking off time for a
     *                           plane
     *
     * @param landingProbability the percentage of planes that will land
     *
     */
    public void simulate(int duration, double arrivalProbability,
                         int maxOperationTime, double landingProbability) {
        int operationTimeLeft = 0;

        for (int clock = 0; clock < duration; clock++) {
            if (Math.random() < arrivalProbability) {
                numberOfPlanes++;
                int transactionTime = (int) (Math.random() * maxOperationTime + 1);
                Airplane newPlane = new Airplane(landingProbability, transactionTime, clock);

                if (newPlane.isLanding()) //Adds landing planes to the front of the queue
                    planes.addToFront(newPlane);
                else
                    planes.addToBack(newPlane);

                System.out.println(newPlane.toString() + (newPlane.isLanding() ? " queues for landing" : " queues for taking off") + " at " + clock + ", which will take " + transactionTime);
            } // end if

            if (operationTimeLeft > 0)
                operationTimeLeft--;

            else if (!planes.isEmpty()) {
                Airplane activePlane = planes.removeFront();
                operationTimeLeft = activePlane.getOperationTime() - 1;
                int timeWaited = clock - activePlane.getStartTime();
                totalTimeWaited = totalTimeWaited + timeWaited;

                if (activePlane.isLanding())
                    numberArrived++;
                else
                    numberDeparted++;

                System.out.println(activePlane.toString() + (activePlane.isLanding() ? " lands" : " takes off") + " at " + clock + " time waited is " + timeWaited);
            } // end if
        } // end for
    } // end simulate

    /**
     * Displays summary results of the simulation.
     */
    public void displayResults() {
        System.out.println();
        System.out.println("Number arrived = " + numberArrived);
        System.out.println("Number departed = " + numberDeparted);
        System.out.println("Total time waited = " + totalTimeWaited);
        int leftInLine = numberOfPlanes - (numberArrived + numberDeparted);
        System.out.println("Number left in line = " + leftInLine);
    } // end displayResults

    /**
     * Initializes the simulation.
     */
    public final void reset() {
        planes.clear();
        numberOfPlanes = 0;
        numberArrived = 0;
        numberDeparted = 0;
        totalTimeWaited = 0;
    } // end reset
}
