package dictionaries;

import java.util.Scanner;

public class TelephoneDirectory {
    private final DictionaryInterface<Name, String> phoneBook = new SortedArrayDictionary<>(); // Sorted dictionary with distinct search keys

    /**
     * Reads a text file of names and telephone numbers.
     *
     * @param data A text scanner for the text file of data.
     */
    public void readFile(Scanner data) {
        while (data.hasNext()) {
            String firstName = data.next();
            String lastName = data.next();
            String phoneNumber = data.next();

            Name fullName = new TelephoneDirectory.Name(firstName, lastName);
            phoneBook.add(fullName, phoneNumber);
        }
        data.close();
    }

    /**
     * @return Gets the phone number of a given person.
     */
    public String getPhoneNumber(Name personName) {
        return phoneBook.getValue(personName);
    }

    /**
     * @return If the phonebook contains a given person.
     */
    public boolean containsPerson(Name personName) {
        return phoneBook.contains(personName);
    }

    /**
     * Adds a given person to the phonebook if they do not already exist. Does nothing otherwise.
     *
     * @return If the person does not already exist in the phonebook.
     */
    public boolean add(Name personName, String phoneNumber) {
        if (phoneBook.contains(personName)) return false; //add not successful because person already exists
        phoneBook.add(personName, phoneNumber); //add the new person if they don't exist
        return true;
    }

    /**
     * Changes a given person's phone number if they already exist. Does nothing otherwise.
     *
     * @return Person's original phone number, null if they did not exist
     */
    public String change(Name personName, String newPhoneNumber) {
        if (!phoneBook.contains(personName)) return null; //change does nothing if the person doesn't exist
        return phoneBook.add(personName, newPhoneNumber); //changes existing phone number
    }

    public static class Name implements Comparable<Name> {
        private String first; // First name
        private String last;  // Last name

        public Name() {
            this("", "");
        } // end default constructor

        public Name(String firstName, String lastName) {
            first = firstName;
            last = lastName;
        } // end constructor

        public void setName(String firstName, String lastName) {
            setFirst(firstName);
            setLast(lastName);
        } // end setName

        public String getName() {
            return toString();
        } // end getName

        public void setFirst(String firstName) {
            first = firstName;
        } // end setFirst

        public String getFirst() {
            return first;
        } // end getFirst

        public void setLast(String lastName) {
            last = lastName;
        } // end setLast

        public String getLast() {
            return last;
        } // end getLast

        public void giveLastNameTo(Name aName) {
            aName.setLast(last);
        } // end giveLastNameTo

        public String toString() {
            return first + " " + last;
        } // end toString

        public boolean equals(Object other) {
            boolean result;

            if ((other == null) || (getClass() != other.getClass()))
                result = false;
            else {
                Name otherName = (Name) other;
                result = first.toLowerCase().equals(otherName.first.toLowerCase()) && //this will make comparisons not case sensitive.
                        last.toLowerCase().equals(otherName.last.toLowerCase());
            } // end if

            return result;
        } // end equals

        public int compareTo(Name other)  // As given in the answer to Self-Test Question 1 in Java Interlude 3
        {
            int result = last.toLowerCase().compareTo(other.last.toLowerCase());

            // If last names are equal, check first names
            if (result == 0)
                result = first.toLowerCase().compareTo(other.first.toLowerCase());

            return result;
        } // end compareTo
    } // end Name

}
