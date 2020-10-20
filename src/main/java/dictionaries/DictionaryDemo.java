package dictionaries;

import java.util.Iterator;

public class DictionaryDemo {
    public static void main(String...args){
        dictDemo();
    }

    private static void dictDemo() {
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

    private static void printDictionary(DictionaryInterface<String, Integer> dict){
        Iterator<String> name = dict.getKeyIterator();
        Iterator<Integer> number = dict.getValueIterator();
        while(name.hasNext()){ //iterator traversal, this will print every name and number in the array assuming the iterator was created correctly
            System.out.println(name.next() + ": " + number.next()); //We only need to use one iterator as both arrays should be the same size.
        }
    }

}

