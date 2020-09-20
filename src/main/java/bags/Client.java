package bags;

/** A test of the methods add, toArray, isEmpty, and getCurrentSize,
 as defined in the first draft of the class bags.LinkedBag.
 @author Frank M. Carrano, Timothy M. Henry
 @version 5.0
 */
public class Client
{
    public static void main(String[] args)
    {
        System.out.println("Creating an empty bag.");
        BagInterface<String> aBag = new LinkedBag<>();
        testIsEmpty(aBag, true);
        displayBag(aBag);

        String[] contentsOfBag = {"A", "D", "B", "A", "C", "A", "D"};
        testAdd(aBag, contentsOfBag);
        testIsEmpty(aBag, false);

        //---------------------ASSIGNMENT 2--------------------------//
        String[] testStrings2 = {"A", "B", "C", "D", "Z"};
        testFrequency(aBag, testStrings2);
        testContains(aBag, testStrings2);

        // Removing strings
        String[] testStrings3 = {"", "B", "A", "C", "Z"};
        testRemove(aBag, testStrings3);

    } // end main

    // Tests the method isEmpty.
    // Precondition: If the bag is empty, the parameter empty should be true;
    // otherwise, it should be false.
    private static void testIsEmpty(BagInterface<String> bag, boolean empty)
    {
        System.out.print("\nTesting isEmpty with ");
        if (empty)
            System.out.println("an empty bag:");
        else
            System.out.println("a bag that is not empty:");

        System.out.print("isEmpty finds the bag ");
        if (empty && bag.isEmpty())
            System.out.println("empty: OK.");
        else if (empty)
            System.out.println("not empty, but it is: ERROR.");
        else if (!empty && bag.isEmpty())
            System.out.println("empty, but it is not empty: ERROR.");
        else
            System.out.println("not empty: OK.");
    } // end testIsEmpty

    // Tests the method add.
    private static void testAdd(BagInterface<String> aBag, String[] content)
    {
        System.out.print("Adding the following strings to the bag: ");
        for (int index = 0; index < content.length; index++)
        {
            if (aBag.add(content[index]))
                System.out.print(content[index] + " ");
            else
                System.out.print("\nUnable to add " + content[index] +
                        " to the bag.");
        } // end for
        System.out.println();

        displayBag(aBag);
    } // end testAdd

    // Tests the method toArray while displaying the bag.
    private static void displayBag(BagInterface<String> aBag)
    {
        System.out.println("The bag contains the following string(s):");
        Object[] bagArray = aBag.toArray();
        for (int index = 0; index < bagArray.length; index++)
        {
            System.out.print(bagArray[index] + " ");
        } // end for

        System.out.println();
    } // end displayBag
//-----------------------------------------------------------LAB ASSIGNMENT 2------------------------------------------------//

    // Tests the method getFrequencyOf.
    private static void testFrequency(BagInterface<String> aBag, String[] tests)
    {
        System.out.println("\nTesting the method getFrequencyOf:");
        for (int index = 0; index < tests.length; index++)
            System.out.println("In this bag, the count of " + tests[index] +
                    " is " + aBag.getFrequencyOf(tests[index]));
    } // end testFrequency

    // Tests the method contains.
    private static void testContains(BagInterface<String> aBag, String[] tests)
    {
        System.out.println("\nTesting the method contains:");
        for (int index = 0; index < tests.length; index++)
            System.out.println("Does this bag contain " + tests[index] +
                    "? " + aBag.contains(tests[index]));
    } // end testContains


    // Tests the two remove methods.
    private static void testRemove(BagInterface<String> aBag, String[] tests)
    {
        for (int index = 0; index < tests.length; index++)
        {
            String aString = tests[index];
            if (aString.equals("") || (aString == null))
            {
                // Test remove()
                System.out.println("\nRemoving a string from the bag:");
                String removedString = aBag.remove();
                System.out.println("remove() returns " + removedString);
            }
            else
            {
                // Test remove(aString)
                System.out.println("\nRemoving \"" + aString + "\" from the bag:");
                boolean result = aBag.remove(aString);
                System.out.println("remove(\"" + aString + "\") returns " + result);
            } // end if

            displayBag(aBag);
        } // end for
    } // end testRemove

} // end LinkedBagDemo1

/*
 Creating an empty bag.

 Testing isEmpty with an empty bag:
 isEmpty finds the bag empty: OK.
 The bag contains the following string(s):

 Adding the following strings to the bag: A D B A C A D
 The bag contains the following string(s):
 D A C A B D A

 Testing isEmpty with a bag that is not empty:
 isEmpty finds the bag not empty: OK.
 */
