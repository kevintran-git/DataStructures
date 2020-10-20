package dictionaries;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SortedArrayDictionaryTest {

    @Test
    void isSorted(){
        DictionaryInterface<String, Integer> people = getTestDictionary();
        Iterator<String> iterator = people.getKeyIterator(); //Gets the iterator, this should iterate through the keys in alphabetical order.
        assertEquals("Abel", iterator.next()); //Abel is first alphabetically, but second to be added to to the array.
        assertEquals("Bette", iterator.next());
        assertEquals("Carole", iterator.next());
        assertEquals("Derek", iterator.next());
        assertEquals("Dirk", iterator.next());
        assertEquals("Miguel", iterator.next());
        assertEquals("Nancy", iterator.next());
        assertEquals("Reiss", iterator.next());
        assertEquals("Sam", iterator.next());
        assertEquals("Tabatha", iterator.next());
        assertEquals("Tom", iterator.next()); //The list is sorted alphabetically.
        assertThrows(NoSuchElementException.class, iterator::next); //Make sure we reached the end of the list. Stepping too far should throw a NoSuchElementException.
    }


    @Test
    void add() {
        DictionaryInterface<String, Integer> people = getTestDictionary();
        assertNotNull(people);
        assertEquals(5552345, people.add("Reiss", 5555432)); //Testing the replace function
        assertEquals(5559012, people.add("Miguel", 5552109)); //replace returns the original value
        assertEquals(5552109, people.getValue("Miguel"));
        assertEquals(5552109, people.getValue("Miguel"));
    }

    @Test
    void remove() {
        DictionaryInterface<String, Integer> people = getTestDictionary();
        assertEquals(5551234, people.remove("Dirk")); //Removing an entry returns its value
        assertNull(people.remove("Dirk")); //Make sure the value was actually removed
    }

    @Test
    void getValue() {
        DictionaryInterface<String, Integer> people = getTestDictionary();
        assertEquals(5555678, people.getValue("Abel"));
        assertEquals(5557890, people.getValue("Sam"));
        assertEquals(5555555, people.getValue("Tom"));
        assertEquals(5552345, people.getValue("Reiss"));
        assertEquals(5559012, people.getValue("Miguel")); //These are all the corresponding numbers
        assertNull(people.getValue("Bo")); //We never added bo, should return nothing
    }

    @Test
    void contains() {
        DictionaryInterface<String, Integer> people = getTestDictionary();
        assertTrue(people.contains("Sam"));
        assertTrue(people.contains("Abel"));
        assertTrue(people.contains("Miguel"));
        assertTrue(people.contains("Tom"));
        assertFalse(people.contains("Bo")); //We didn't add bo!
    }

    @Test
    void getSize() {
        DictionaryInterface<String, Integer> people = getTestDictionary();
        assertEquals(11, people.getSize());
        people.remove("Reiss");
        assertEquals(10, people.getSize()); //checking if the size went down
        people.remove("Carole");
        assertEquals(9, people.getSize()); //checking if the size went down
        people.remove("Bo");
        assertEquals(9, people.getSize()); //the size shouldn't change if we're not removing anything
    }

    @Test
    void clear() {
        DictionaryInterface<String, Integer> people = getTestDictionary();
        assertNotEquals(0, people.getSize()); //populated dictionary should not be empty
        assertFalse(people.isEmpty());
        people.clear();
        assertEquals(0, people.getSize()); //populated dictionary should be empty
        assertTrue(people.isEmpty()); //another way of getting emptiness

    }

    /**
     *
     * @return Premade dictionary full of people, this ensures a consistent dictionary across tests
     */
    DictionaryInterface<String, Integer> getTestDictionary(){
        DictionaryInterface<String, Integer> people = new SortedArrayDictionary<>();
        people.add("Dirk", 5551234);
        people.add("Abel", 5555678);
        people.add("Miguel", 5559012);
        people.add("Tabatha", 5553456);
        people.add("Tom", 5555555);
        people.add("Sam", 5557890);
        people.add("Reiss", 5552345);
        people.add("Bette", 5557891);
        people.add("Carole", 5557892);
        people.add("Derek", 5557895);
        people.add("Nancy", 5557894);
        return people;
    }
}