package main;

import lib.Name;
import lib.Register;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterAppTest {

    /* Please note - when we come to mark the solution to this unit test we will change the input
     * data set for the names added to the register to ensure the solution works dynamically based
     * upon any given data set and is not hardcoded in any way.
     */
    @Test
    public void testExecute() {
        Register r = new Register();
        r.addName(new Name("Joe", "Bloggs"));
        r.addName(new Name("Fred", "Jones"));
        r.addName(new Name("Nila", "Singh"));

        String result = RegisterApp.execute(r, new Name("Cassie", "Downturn"));

        String expectedResult = "BLOGGS, J\nSINGH, N\nDOWNTURN, C\n";

        assertEquals("The string returned should match the expected result (run 1)", expectedResult, result);


        /* Test with a second set of input data */
        Register r2 = new Register();
        r2.addName(new Name("Tim", "Russ"));
        r2.addName(new Name("David", "Blunt"));
        r2.addName(new Name("Remi", "Patel"));

        String result2 = RegisterApp.execute(r2, new Name("Cassie", "Downturn"));

        String expectedResult2 = "PATEL, R\nDOWNTURN, C\n";

        assertEquals("The string returned should match the expected result (run 2)", expectedResult2, result2);
    }

}
