package dictionaries;

import hashing.HashedDictionary;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class DictionaryDemo {
    public static void main(String... args) {
        dictDemo();
        //System.out.println(squaresHashString(34323142));
        // hashingTest();

    }

    private static void hashingTest() {
        double density = 0.8;
        int n = 5000, end = 6;

        Function<Integer, Double> p = x -> Math.pow(density, x) * Math.pow(Math.E, -density) / factorial(x); //function that outputs the probability of an address being chosen x times

        System.out.println("GIVEN r/n = " + density); //density is amount of keys divided by amount of addresses
        for (int i = 0; i < end; i++)
            System.out.println("p(" + i + ") = " + p.apply(i)); //applies the function from 0 to 5
        System.out.println("SUM = " + IntStream.range(0, end).mapToDouble(p::apply).sum()); //summation of the function from 0 to 5

        System.out.println("\n\n\nGIVEN n = " + n); //amount of addresses in the array

        UnaryOperator<Integer> k = i -> (int) (p.apply(i) * n); //function that outputs the amount of keys hashing to the same address x times
        for (int i = 0; i < end; i++) System.out.println("Keys mapping to " + i + " = " + k.apply(i)); //same
        System.out.println("SUM = " + IntStream.range(0, end).map(k::apply).sum()); //summing
        System.out.println("Addresses with No Keys: " + k.apply(0)); //amount of addresses that have 0 keys hashing to them
        System.out.println("Addresses with One Key: " + k.apply(1)); //amount of addresses that have 1 key hashing to them
        System.out.println("Addresses with Colliding Keys: " + (n - k.apply(0) - k.apply(1))); //anything not 1 to 1 is colliding

        var overflow = IntStream.range(2, end).mapToDouble(x -> n * (x - 1) * p.apply(x)).sum(); //summation of addresses with more than 1 key multiplied by amount of keys in each adress
        System.out.println("Overflow Keys: " + (int) (overflow)); //rounded down
        System.out.println("Percent of overflow keys: " + 100 * overflow / n);
    }

    private static int factorial(int n) {
        return (n == 0 || n == 1) ? 1 : n * factorial(n - 1);
    }

    private static void dictDemo() {
        DictionaryInterface<String, Integer> people = new HashedDictionary<>();
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

        printDictionary(people);

        System.out.println("Testing getValue()");
        System.out.println(people.getValue("Abel")); //Should return the same values we added them at
        System.out.println(people.getValue("Sam"));
        System.out.println(people.getValue("Tom"));
        System.out.println(people.getValue("Reiss"));
        System.out.println(people.getValue("Miguel"));
        System.out.println(people.getValue("gsdsfdsfds")); //returns null as we did not add this

        System.out.println("Testing contains()");
        System.out.println(people.contains("Sam")); //true
        System.out.println(people.contains("Abel"));
        System.out.println(people.contains("Miguel"));
        System.out.println(people.contains("Tom"));
        System.out.println(people.contains("Bo")); //false, we did not add bo

        System.out.println("Testing remove()");
        System.out.println(people.remove("Dirk")); //should return dirk's number, 5551234
        System.out.println(people.remove("Dirk")); //should return null, repeating this to make sure dirk was removed

        System.out.println("Testing replace");
        System.out.println(people.add("Reiss", 5555432)); //returns reiss' old number
        System.out.println(people.add("Miguel", 5552109)); //same with miguel
        printDictionary(people); //make sure reiss and miguel's numbers have changed to their new ones

        System.out.println("Removing Reiss");
        System.out.println("Size before remove: " + people.getSize());
        System.out.println(people.remove("Reiss")); //return reiss's number
        System.out.println("Size after remove: " + people.getSize()); //should be one less than before
        printDictionary(people);

        System.out.println("Removing Carole");
        System.out.println("Size before remove: " + people.getSize());
        System.out.println(people.remove("Carole")); //return carole's number
        System.out.println("Size after remove: " + people.getSize()); //again should be one less
        printDictionary(people);

        System.out.println("Removing Bo, this should not change the dictionary");
        System.out.println("Size before remove: " + people.getSize());
        System.out.println(people.remove("Bo")); //return null, Bo is not in the dictionary
        System.out.println("Size after remove: " + people.getSize()); //should not change
        printDictionary(people);

        System.out.println("This should print out nothing.");
        people.clear();
        printDictionary(people);
    }

    private static void printDictionary(DictionaryInterface<String, Integer> dict) {
        if (dict.getClass() == HashedDictionary.class) {
            ((HashedDictionary) dict).displayHashTable();
            return;
        }
        Iterator<String> name = dict.getKeyIterator();
        Iterator<Integer> number = dict.getValueIterator();
        while (name.hasNext()) { //iterator traversal, this will print every name and number in the array assuming the iterator was created correctly
            System.out.println(name.next() + ": " + number.next()); //We only need to use one iterator as both arrays should be the same size.
        }
    }
}

