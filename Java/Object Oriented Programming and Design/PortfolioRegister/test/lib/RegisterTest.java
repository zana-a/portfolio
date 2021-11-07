package lib;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class RegisterTest {

    /* The Register object is declared as a field so it is in scope throughout this class */
    private Register r;

    /* This method will run and create a new Register object instance before every individual unit test */
    @Before
    public void initialiseRegister() {
        r = new Register();
    }


    /* -- START OF UNIT TESTS -- */

    @Test
    public void testDefaultConstructor() {
        assertEquals("Register should be empty", true, r.isRegisterEmpty());
        assertEquals("Register should be of size zero", 0, r.registerSize());
    }

    @Test
    public void testAddName() {
        r.addName(new Name("Joe", "Bloggs"));
        Name n = new Name("Fred", "Jones");
        r.addName(n);

        assertSame("Register's last element should be the newly added name object", n, r.getName(r.registerSize() - 1));
        assertEquals("Register's size should be two", 2, r.registerSize());
    }

    @Test
    public void testGetName() {
        Name n = new Name("Joe", "Bloggs");
        r.addName(n);
        Name n1 = new Name("Fred", "Jones");
        r.addName(n1);

        assertSame("Register should retrieve the same object added at index 0", n, r.getName(0));
        assertSame("Register should retrieve the same object added at index 1", n1, r.getName(1));
    }

    @Test
    public void testRemoveName() {
        r.addName(new Name("Fred", "Jones"));
        Name n = new Name("Joe", "Bloggs");
        r.addName(n);

        Name n1 = r.removeName(1);

        assertSame("Register should remove and return the same object that was added", n, n1);
        assertEquals("Register's size should now be one", 1, r.registerSize());
    }

    @Test
    public void testRegisterSize() {
        assertEquals("Register's size should be zero", 0, r.registerSize());

        r.addName(new Name("Joe", "Bloggs"));
        r.addName(new Name("Fred", "Jones"));
        r.addName(new Name("Tim", "Jones"));

        assertEquals("Register's size should be three", 3, r.registerSize());
    }

    @Test
    public void testClearRegister() {
        for (int i = 0; i < 10; i++) {
            r.addName(new Name());
        }

        int sizeBeforeClear = r.registerSize();

        assertTrue("Names should have been added to register - clear cannot therefore be tested", sizeBeforeClear > 0);


        r.clearRegister();

        assertEquals("Register's size should be zero after clear", 0, r.registerSize());
    }

    @Test
    public void testIsRegisterEmpty() {
        assertTrue("Register should initially be empty, i.e. return true", r.isRegisterEmpty());

        r.addName(new Name("Joe", "Bloggs"));

        assertFalse("Register should not be empty after a name is added, i.e. return false", r.isRegisterEmpty());
    }

    @Test
    public void testToString() {
        Name n = new Name("Tom", "Bloggs");
        r.addName(n);

        String toStr = r.toString();

        assertTrue("The toString method should be in the standard convention format as taught",
                toStr.startsWith("Register:[") &&
                        toStr.contains(n.toString()) &&
                        toStr.endsWith("]"));

        /* Further check to avoid the result of toString being hardcoded */
        r = new Register();
        n = new Name("Joe", "Bloggs");
        r.addName(n);
        Name n1 = new Name("Fred", "Jones");
        r.addName(n1);

        toStr = r.toString();

        assertTrue("The toString method should be in the standard convention format as taught",
                toStr.startsWith("Register:[") &&
                        toStr.contains(n.toString()) &&
                        toStr.contains(n1.toString()) &&
                        toStr.endsWith("]"));
    }

    @Test
    public void testSearchRegisterByFamilyName() {
        r.addName(new Name("Joe", "Bloggs"));
        r.addName(new Name("Fred", "Jones"));

        assertTrue("First search should find Jones, i.e. return true", r.searchRegisterByFamilyName(new String("Jones")));


        r = new Register();
        r.addName(new Name("Joe", "Bloggs"));
        r.addName(new Name("Fred", "Wayne"));

        assertFalse("Second search should not find Jones, i.e. return false", r.searchRegisterByFamilyName(new String("Jones")));


        r = new Register();
        r.addName(new Name("Joe", "Bloggs"));
        r.addName(new Name("Fred", "Jones"));

        assertTrue("Third search should find Bloggs, i.e. return true", r.searchRegisterByFamilyName(new String("Bloggs")));


        r = new Register();
        r.addName(new Name("Joe", "Woods"));
        r.addName(new Name("Fred", "Jones"));

        assertFalse("Fourth search should not find Bloggs, i.e. return false", r.searchRegisterByFamilyName(new String("Bloggs")));
    }

    @Test
    public void testCountFirstNameOccurrences() {
        r.addName(new Name("Jon", "Bloggs"));
        r.addName(new Name("Fred", "Jones"));

        assertEquals("First count should return 0", 0, r.countFirstNameOccurrences('e'));


        r = new Register();
        r.addName(new Name("Luke", "Bloggs"));
        r.addName(new Name("Jen", "Jones"));

        assertEquals("Second count should return 1", 1, r.countFirstNameOccurrences('e'));


        r = new Register();
        r.addName(new Name("Luke", "Bloggs"));
        r.addName(new Name("Joe", "Jones"));

        assertEquals("Third count should return 2", 2, r.countFirstNameOccurrences('e'));


        r = new Register();
        r.addName(new Name("Luke", "Bloggs"));
        r.addName(new Name("Joe", "Jones"));

        assertEquals("Fourth count should return 0", 0, r.countFirstNameOccurrences('n'));


        r = new Register();
        r.addName(new Name("Jon", "Bloggs"));
        r.addName(new Name("Fred", "Jones"));

        assertEquals("Fifth count should return 1", 1, r.countFirstNameOccurrences('n'));


        r = new Register();
        r.addName(new Name("Jon", "Bloggs"));
        r.addName(new Name("Jen", "Jones"));

        assertEquals("Sixth count should return 2", 2, r.countFirstNameOccurrences('n'));
    }

    @Test
    public void testIterator() {
        r.addName(new Name("Joe", "Bloggs"));
        r.addName(new Name("Fred", "Bones"));

        //NOTE --- There is no assert or fail in this test because the for-each loop below will only work if the Register
        //         class implements Iterable correctly. Otherwise either a compilation error or runtime exception will occur.

        for (Name n : r) {
        }
    }

    @Test
    public void testSort() {
        Name n1 = new Name("Ted", "Bloggs");
        Name n2 = new Name("Fred", "Bones");
        Name n3 = new Name("Joe", "Bloggs");

        r.addName(n1);
        r.addName(n2);
        r.addName(n3);
        r.sortRegister();

        assertTrue("Elements should have been sorted based on the compareTo method of Name", r.getName(0) == n3 && r.getName(1) == n1 && r.getName(2) == n2);
    }


    /* -- GENERAL TESTS TO ENSURE CORRECT USE OF FIELDS AND METHODS -- */

    @Test
    public void testFieldModifiers() {
        Field[] fields = Register.class.getDeclaredFields();

        assertTrue("Modifiers cannot be assessed if no fields exist", fields.length > 0);

        assertTrue("All fields must be private", Arrays.stream(fields).allMatch(f -> (Modifier.PRIVATE & f.getModifiers()) != 0));

        assertTrue("No fields should be static", Arrays.stream(fields).allMatch(f -> (Modifier.STATIC & f.getModifiers()) == 0));
    }

    @Test
    public void testFieldNumber() {
        Field[] fields = Register.class.getDeclaredFields();

        assertEquals("The Register class should only have one field", 1, fields.length);
    }

    @Test
    public void testFieldTypes() {
        Field f = Register.class.getDeclaredFields()[0];

        assertTrue("The Register class should have a single field of type ArrayList<Name>",
                ArrayList.class.isAssignableFrom(f.getType()) &&
                        Name.class.isAssignableFrom((Class<?>) ((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0]));
    }

}
