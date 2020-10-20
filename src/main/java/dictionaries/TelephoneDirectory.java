package dictionaries;

import java.util.Scanner;

public class TelephoneDirectory {
    private final DictionaryInterface<Name, String> phoneBook; // Sorted dictionary with distinct search keys

    public TelephoneDirectory()
    {
        phoneBook = new SortedArrayDictionary<>();
    } // end default constructor

    // 20.10
    /** Reads a text file of names and telephone numbers.
     @param data  A text scanner for the text file of data. */
    public void readFile(Scanner data)
    {
        while (data.hasNext())
        {
            String firstName   = data.next();
            String lastName    = data.next();
            String phoneNumber = data.next();

            Name fullName = new TelephoneDirectory.Name(firstName, lastName);
            phoneBook.add(fullName, phoneNumber);
        } // end while

        data.close();
    } // end readFile

    // 20.11
    /** Gets the phone number of a given person. */
    public String getPhoneNumber(Name personName)
    {
        return phoneBook.getValue(personName);
    } // end getPhoneNumber

    public String getPhoneNumber(String firstName, String lastName)
    {
        Name fullName = new Name(firstName, lastName);
        return phoneBook.getValue(fullName);
    } // end getPhoneNumber

    public static class Name implements Comparable<Name>
    {
        private String first; // First name
        private String last;  // Last name

        public Name()
        {
            this("", "");
        } // end default constructor

        public Name(String firstName, String lastName)
        {
            first = firstName;
            last = lastName;
        } // end constructor

        public void setName(String firstName, String lastName)
        {
            setFirst(firstName);
            setLast(lastName);
        } // end setName

        public String getName()
        {
            return toString();
        } // end getName

        public void setFirst(String firstName)
        {
            first = firstName;
        } // end setFirst

        public String getFirst()
        {
            return first;
        } // end getFirst

        public void setLast(String lastName)
        {
            last = lastName;
        } // end setLast

        public String getLast()
        {
            return last;
        } // end getLast

        public void giveLastNameTo(Name aName)
        {
            aName.setLast(last);
        } // end giveLastNameTo

        public String toString()
        {
            return first + " " + last;
        } // end toString

        public boolean equals(Object other)
        {
            boolean result;

            if ((other == null) || (getClass() != other.getClass()))
                result = false;
            else
            {
                Name otherName = (Name)other;
                result = first.equals(otherName.first) &&
                        last.equals(otherName.last);
            } // end if

            return result;
        } // end equals

        public int compareTo(Name other)  // As given in the answer to Self-Test Question 1 in Java Interlude 3
        {
            int result = last.compareTo(other.last);

            // If last names are equal, check first names
            if (result == 0)
                result = first.compareTo(other.first);

            return result;
        } // end compareTo
    } // end Name

}
