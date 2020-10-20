package dictionaries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PhonebookDriver {
    private static final TelephoneDirectory.Name INPUT_ERROR = new TelephoneDirectory.Name("error", "error");
    private static final TelephoneDirectory.Name QUIT = new TelephoneDirectory.Name("quit", "quit");

    public static void main(String[] args) {
        TelephoneDirectory directory = new TelephoneDirectory();
        String fileName = "data.txt"; // Or file name could be read
        File source = new File(fileName);

        try {
            Scanner data = new Scanner(source);
            directory.readFile(data);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        int action = getAction();
        while (action != 4) {
            if (action == 1)
                findInDrectory(directory);
            else if (action == 2)
                addName(directory);
            else if (action == 3)
                changeName(directory);
            else System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nThat wasn't one of the options listed. Please try again.\n");
            action = getAction();
        }

        System.out.println("Bye!");
    } // end main

    private static void addName(TelephoneDirectory directory) {
        TelephoneDirectory.Name nextName = getName();    // Get name for search from user
        while (!nextName.equals(QUIT)) {
            if (nextName.equals(INPUT_ERROR))
                System.out.println("Error in entering name. Try again.");
            else {
                if (directory.containsPerson(nextName))
                    System.out.println(nextName + " is already in the phone directory! Please quit and use the change method instead.");
                else {
                    String number = getNumber();
                    directory.add(nextName, number);
                    System.out.println(nextName + " : " + number + " added to the phone directory.");
                }
            } // end if
            nextName = getName();
        } // end while
    }

    private static void changeName(TelephoneDirectory directory) {
        TelephoneDirectory.Name nextName = getName();    // Get name for search from user
        while (!nextName.equals(QUIT)) {
            if (nextName.equals(INPUT_ERROR))
                System.out.println("Error in entering name. Try again.");
            else {
                String oldNumber = directory.getPhoneNumber(nextName);
                if (oldNumber == null)
                    System.out.println(nextName + " is not in the phone directory! Please quit and use the add method instead.");
                else {
                    String number = getNumber();
                    directory.change(nextName, number);
                    System.out.println(nextName + " : " + number + " is the changed entry in the phonebook. Their old number was " + oldNumber + ".");
                }
            } // end if
            nextName = getName();
        } // end while
    }

    private static void findInDrectory(TelephoneDirectory directory) {
        TelephoneDirectory.Name nextName = getName();    // Get name for search from user
        while (!nextName.equals(QUIT)) {
            if (nextName.equals(INPUT_ERROR))
                System.out.println("Error in entering name. Try again.");
            else {
                String phoneNumber = directory.getPhoneNumber(nextName);
                if (phoneNumber == null)
                    System.out.println(nextName + " is not in the directory.");
                else
                    System.out.println("The phone number for " + nextName +
                            " is " + phoneNumber);
            } // end if

            nextName = getName();
        } // end while
    }

    // Returns either the name read from user, INPUT_ERROR, or QUIT.
    private static TelephoneDirectory.Name getName() {
        TelephoneDirectory.Name result = null;
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter first name and last name, " +
                "or quit to end: ");
        String line = keyboard.nextLine();

        if (line.trim().toLowerCase().equals("quit"))
            result = QUIT;
        else {
            String firstName = null;
            String lastName = null;
            Scanner scan = new Scanner(line);

            if (scan.hasNext()) {
                firstName = scan.next();
                if (scan.hasNext())
                    lastName = scan.next();
                else
                    result = INPUT_ERROR;
            } else
                result = INPUT_ERROR;

            if (result == null) {
                // First and last names have been read
                result = new TelephoneDirectory.Name(firstName, lastName);
            } // end if
        } // end if

        return result;
    } // end getName

    // Returns either the name read from user, INPUT_ERROR, or QUIT.
    private static String getNumber() {
        System.out.print("Input phone number: ");
        return new Scanner(System.in).nextLine().trim();
    } // end getName


    private static int getAction() {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Welcome to the phone directory! Please enter the corresponding number for your desired action." +
                "\n1: Lookup phone number" +
                "\n2. Add a new person to directory" +
                "\n3. Change an existing person's phone number" +
                "\n4. Quit");
        try {
            return keyboard.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nYou didn't type in an option! Please try again.\n");
            return getAction();
        }
    } // end getName
}
