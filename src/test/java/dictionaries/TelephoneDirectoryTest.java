package dictionaries;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TelephoneDirectoryTest {
    TelephoneDirectory directory;

    @BeforeEach
    void setUp() { //gets excecuted before every run.
        directory = new TelephoneDirectory();
        directory.add(new TelephoneDirectory.Name("Foo", "Bar"), "651-432-3215");
    }

    @AfterEach
    void tearDown() { //reset for the next run
        directory = null;
    }

    @Test
    void readFile() {
        assertAll(() -> {
            Scanner data = new Scanner(new File("data.txt")); //reads file
            directory.readFile(data);
        });
        assertEquals("401-555-1234", directory.getPhoneNumber(new TelephoneDirectory.Name("Joey", "Jones"))); //Make sure the file was read, testing for one entry in the file
    }

    @Test
    void getPhoneNumber() {
        assertEquals("651-432-3215", directory.getPhoneNumber(new TelephoneDirectory.Name("Foo", "Bar"))); //Make sure it gets the correct number
        assertNull(directory.getPhoneNumber(new TelephoneDirectory.Name("Qux", "Baz"))); //return null when number wasnt found
    }

    @Test
    void containsPerson() {
        assertTrue(directory.containsPerson(new TelephoneDirectory.Name("Foo", "Bar")));
        assertFalse(directory.containsPerson(new TelephoneDirectory.Name("Qux", "Baz")));
    }

    @Test
    void add() {
        assertFalse(directory.add(new TelephoneDirectory.Name("Foo", "Bar"), "666-666-6666")); //This shouldn't do anything
        assertTrue(directory.add(new TelephoneDirectory.Name("Qux", "Baz"), "777-777-7777")); //This shound add a new phone number
        assertEquals("777-777-7777", directory.getPhoneNumber(new TelephoneDirectory.Name("Qux", "Baz"))); //make sure the new one is added
        assertEquals("651-432-3215", directory.getPhoneNumber(new TelephoneDirectory.Name("Foo", "Bar"))); //make sure nothing was changed
    }

    @Test
    void change() {
        assertEquals("651-432-3215", directory.change(new TelephoneDirectory.Name("Foo", "Bar"), "666-666-6666")); //This should change foobar's number
        assertNull(directory.change(new TelephoneDirectory.Name("Qux", "Baz"), "777-777-7777")); //This should do nothing
        assertEquals("666-666-6666", directory.getPhoneNumber(new TelephoneDirectory.Name("Foo", "Bar"))); //Make sure this was changed
        assertNull(directory.getPhoneNumber(new TelephoneDirectory.Name("Qux", "Baz"))); //Make sure this did nothing
    }
}