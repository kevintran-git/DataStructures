package linear.queues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaitLineTest {
    @Test
    void simulate() {
        assertAll(() -> {
            WaitLine myLine = new WaitLine();
            myLine.simulate(6969, 1/69.0, 69);
            myLine.displayResults();
        });
    }
}