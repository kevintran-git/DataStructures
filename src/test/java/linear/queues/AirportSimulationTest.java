package linear.queues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportSimulationTest {

    @Test
    void simulate() {
        AirportSimulation airport = new AirportSimulation();
        assertAll(() -> airport.simulate(10000, 1/5.0, 10, 0.8),
                airport::displayResults);
    }
}