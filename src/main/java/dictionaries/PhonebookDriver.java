package dictionaries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PhonebookDriver {
    private static final TelephoneDirectory.Name INPUT_ERROR = new TelephoneDirectory.Name("error", "error");
    private static final TelephoneDirectory.Name QUIT = new TelephoneDirectory.Name("quit", "quit");

    public static void main(String[] args)
    {
        TelephoneDirectory directory = new TelephoneDirectory();
        String fileName = "data.txt"; // Or file name could be read
        File source = new File(fileName);

        try
        {
            Scanner data = new Scanner(source);
            directory.readFile(data);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + e.getMessage());
        }

        TelephoneDirectory.Name nextName = getName();    // Get name for search from user
        while (!nextName.equals(QUIT))
        {
            if (nextName.equals(INPUT_ERROR))
                System.out.println("Error in entering name. Try again.");
            else
            {
                String phoneNumber = directory.getPhoneNumber(nextName);
                if (phoneNumber == null)
                    System.out.println(nextName + " is not in the directory.");
                else
                    System.out.println("The phone number for " + nextName +
                            " is " + phoneNumber);
            } // end if

            nextName = getName();
        } // end while

        System.out.println("Bye!");
    } // end main

    // Returns either the name read from user, INPUT_ERROR, or QUIT.
    private static TelephoneDirectory.Name getName()
    {
        TelephoneDirectory.Name result = null;
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter first name and last name, " +
                "or quit to end: ");
        String line = keyboard.nextLine();

        if (line.trim().toLowerCase().equals("quit"))
            result = QUIT;
        else
        {
            String firstName = null;
            String lastName = null;
            Scanner scan = new Scanner(line);

            if (scan.hasNext())
            {
                firstName = scan.next();
                if (scan.hasNext())
                    lastName = scan.next();
                else
                    result = INPUT_ERROR;
            }
            else
                result = INPUT_ERROR;

            if (result == null)
            {
                // First and last names have been read
                result = new TelephoneDirectory.Name(firstName, lastName);
            } // end if
        } // end if

        return result;
    } // end getName
}
