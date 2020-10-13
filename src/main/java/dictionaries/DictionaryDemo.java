package dictionaries;

import java.util.Iterator;

public class DictionaryDemo {
    public static void main(String...args){
        DictionaryInterface<String, Integer> people = new ArrayDictionary<>();
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
        System.out.println(people.getValue("Abel"));
        System.out.println(people.getValue("Sam"));
        System.out.println(people.getValue("Tom"));
        System.out.println(people.getValue("Reiss"));
        System.out.println(people.getValue("Miguel"));
        System.out.println(people.getValue("gsdsfdsfds"));

        System.out.println("Testing contains()");
        System.out.println(people.contains("Sam"));
        System.out.println(people.contains("Abel"));
        System.out.println(people.contains("Miguel"));
        System.out.println(people.contains("Tom"));
        System.out.println(people.contains("Bo"));

        System.out.println("Testing remove()");
        System.out.println(people.remove("Dirk"));
        System.out.println(people.remove("Dirk"));

        System.out.println("Testing replace");
        System.out.println(people.add("Reiss", 5555432));
        System.out.println(people.add("Miguel", 5552109));
        printDictionary(people);

        System.out.println("Removing Reiss");
        System.out.println(people.remove("Reiss"));
        printDictionary(people);

        System.out.println("Removing Carole");
        System.out.println(people.remove("Carole"));
        printDictionary(people);

        System.out.println("Removing Bo, this should not change the dictionary");
        System.out.println(people.remove("Bo"));
        printDictionary(people);

        System.out.println("This should print out nothing.");
        people.clear();
        printDictionary(people);
    }

    private static void printDictionary(DictionaryInterface<String, Integer> dict){
        Iterator<String> name = dict.getKeyIterator();
        Iterator<Integer> number = dict.getValueIterator();
        while(name.hasNext()){
            System.out.println(name.next() + ": " + number.next());
        }
    }

}

