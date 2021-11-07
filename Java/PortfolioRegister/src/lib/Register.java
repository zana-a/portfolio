package lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Register Class
 * <p>
 * Makes use of the Name class to create a array of Name objects as well as provide common methods for inserting,
 * deleting and manipulating the array.
 *
 * @author p2401470
 */
public class Register implements Iterable<Name> {
    private ArrayList<Name> register;

    /**
     * Initialises a new ArrayList object with the generic type of Name objects.
     */
    public Register() {
        register = new ArrayList<Name>();
    }

    /**
     * Checks if the register is empty.
     *
     * @return a boolean value
     */
    public boolean isRegisterEmpty() {
        return register.isEmpty();
    }

    /**
     * Returns the length of the register array.
     *
     * @return an int that refers to the length of the array.
     */
    public int registerSize() {
        return register.size();
    }

    /**
     * Accepts in a new Name object will then be appended to the end of the register array.
     *
     * @param name a Name class object
     */
    public void addName(Name name) {
        register.add(name);
    }

    /**
     * Takes in a int representing the position of the desired Name object to return.
     *
     * @param pos an int referring to the index of a desired Name object
     * @return a Name object at the given position
     */
    public Name getName(int pos) {
        return register.get(pos);
    }

    /**
     * Takes in an int representing the position of the desired Name object to remove.
     *
     * @param pos an int referring to the index of a desired Name object
     * @return the Name object that was deleted
     */
    public Name removeName(int pos) {
        return this.register.remove(pos);
    }

    /**
     * Deletes all the items within the register list.
     */
    public void clearRegister() {
        this.register.clear();
    }

    /**
     * Searches for a given family name (as a String). If it matches in the register, it will return a boolean of true.
     *
     * @param familyName a String object referring to a given family name
     * @return a boolean of true if register contains family name
     */
    public boolean searchRegisterByFamilyName(String familyName) {
        for (Name name : register) {
            if (name.getFamilyName().equals(familyName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the position of the first occurrence of the given letter within the end character of the first name.
     *
     * @param c the chosen char to search for
     * @return an int of the position of where the char was first found
     */
    public int countFirstNameOccurrences(char c) {

        int pos = 0;
        for (Name name : register) {
            if (name.getFirstName().endsWith(String.valueOf(c))) {
                pos += 1;
            }
        }

        return pos;
    }

    /**
     * Sorts the register
     */
    public void sortRegister() {
        Collections.sort(register);
    }

    /**
     * Output a formatted string in the standard convention.
     *
     * @return a String of the register list that consists of the Name objects
     */
    @Override
    public String toString() {
        return "Register:[" + register + "]";
    }

    /**
     * Iterates through the name values within the register list.
     */
    @Override
    public Iterator<Name> iterator() {
        return register.iterator();
    }
}
