package hashing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HashingDemoTest {

    @Test
    void testCollisions() {
        assertEquals(1, HashingDemo.testCollisions(HashingDemo.SquaresHashing::mostSig)); //first two digits are all the same. everything should collide.
        assertEquals(1, HashingDemo.testCollisions(HashingDemo.SquaresHashing::mid));
        assertEquals(1, HashingDemo.testCollisions(HashingDemo.SquaresHashing::leastSig));
    }

    @Test
    void stringsHash() {
        assertEquals(155497103, HashingDemo.stringsHash("Hello"));
        assertEquals(169653583, HashingDemo.stringsHash("World"));
    }

    @Test
    void squareHashing(){
        System.out.println((long)123456 * 123456);
        assertEquals(13, HashingDemo.SquaresHashing.mid(123456));
        assertEquals(36, HashingDemo.SquaresHashing.leastSig(123456));
        assertEquals(15, HashingDemo.SquaresHashing.mostSig(123456));
    }
}