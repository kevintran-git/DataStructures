package hashing;

import java.io.ByteArrayInputStream;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class HashingDemo {

    public static void main(String... args) {
        System.out.println("Middle squares hashing 123456: " + SquaresHashing.mid(123456));
        System.out.println("Least squares hashing 123456: " + SquaresHashing.leastSig(123456));
        System.out.println("Most squares hashing 123456: " + SquaresHashing.mostSig(123456));
        //reading txt file
        System.out.println("Collisions with most significant digit squares hash: " + testCollisions(SquaresHashing::mostSig));
        System.out.println("Collisions with mid significant digit squares hash: " + testCollisions(SquaresHashing::mid));
        System.out.println("Collisions with least significant digit squares hash: " + testCollisions(SquaresHashing::leastSig));

        System.out.println("Hashing Hello: " + stringsHash("Hello"));
        System.out.println("Hashing World: " + stringsHash("World"));
    }

    public static int testCollisions(UnaryOperator<Integer> hashMethod) {
        Scanner input = new Scanner(new ByteArrayInputStream(HashPhoneNumsTXT.getFile().getBytes())); //read file
        Hashtable<Integer, Integer> table = new Hashtable<>();
        while (input.hasNext()) { //read each line
            int next = hashMethod.apply(Integer.parseInt(input.nextLine())); //applies our inputted hash function
            if (table.containsKey(next))
                table.put(next, table.get(next) + 1); //If the entry exists, increase the value. This will determine how many collisions a particular hash has.
            else table.put(next, 0); //0 means no collisions.
        }
        return (int) table.values().stream().filter(i -> i > 0).count(); //Counts the amount of entries with values above zero. This is the amount of addresses with collisions.
    }

    public static int stringsHash(String s) {
        int h = 3; //start off with 3
        for (int i = 0; i < s.length(); i++)
            h = 31 * h + s.charAt(i); //adds char value to hash times a value
        return Math.abs(h); //avoids overflow
    }

    public static class SquaresHashing {

        public static int mid(int i) {
            String s = Long.toString((long) i * i);
            while (s.length() > 3) s = s.substring(1, s.length() - 1); //chop off last two values until you get 3 digits
            return Integer.parseInt(s.substring(0, 2)); //chop off the last digit
        }

        public static int mostSig(int i) {
            return Integer.parseInt(Long.toString((long) i * i).substring(0, 2)); //first 2 digits
        }

        public static int leastSig(int i) {
            return Integer.parseInt(Long.toString((long) i * i).substring(Long.toString((long) i * i).length() - 2)); //last 2 digits
        }
    }

}
