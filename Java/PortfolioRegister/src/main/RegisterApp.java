package main;

import lib.Name;
import lib.Register;

public class RegisterApp {

    public static void main(String[] args) {
        Register r = new Register();
        r.addName(new Name("Zana", "Ahmad"));
        r.addName(new Name("Sana", "Ahmad"));

        r.sortRegister();
    }

    public static String execute(Register reg, Name n) {

        reg.addName(n); // Adds the new Name object to list provided.
        reg.removeName(1); // Removes an element in position 1.

        String returnString = ""; // Instantiate the new String object.

        /*
         * Creates a new list and then appends a standardised string sequence if the register has a surname with the
         * length of equal to or greater than 5.
         *
         * eg.
         * "BLOGGS, J"
         */
        for (int i = 0; i < reg.registerSize(); i++) {
            if (reg.getName(i).getFamilyName().length() >= 5) {
                returnString += reg.getName(i).getFamilyName().toUpperCase() + ", " +
                        reg.getName(i).getFirstName().substring(0, 1).toUpperCase() + "\n";
            }
        }

        // Return the string slenderised sequence
        return returnString;
    }
}