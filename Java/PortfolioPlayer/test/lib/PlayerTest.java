package lib;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class PlayerTest {

	/* -- START OF UNIT TESTS -- */

	@Test
	public void testDefaultConstructor() {
		Player p = new Player();

		assertEquals("Name field should be initialised with a default Name object ", new Name(), p.getName());
		assertTrue("Player's rollable field should be initialised with an implementing instance of the Rollable interface", p.getRollable() instanceof Rollable);
	}

	@Test
	public void testCustomConstructor1arg() {
		Name n = new Name("Joe", "Bloggs");
		Player p = new Player(n);

		assertSame("Player's name field should be initialised with and return the same object received by the constructor", n, p.getName()); 
		assertTrue("Player's rollable field should be initialised with an implementing instance of the Rollable interface", p.getRollable() instanceof Rollable);
	}

	@Test
	public void testCustomConstructor2argsWithDie() {
		Name n = new Name("Joe", "Bloggs");
		Rollable r = new Die(5);
		Player p = new Player(n, r);

		assertSame("Player's name field should be initialised with and return the same object received by the constructor", n, p.getName()); 
		assertSame("Player's rollable field should be initialised with and return the same object received by the constructor", r, p.getRollable()); 
	}

	@Test
	public void testCustomConstructor2argsWithPairOfDice() {
		Name n = new Name("Joe", "Bloggs");
		Rollable r = new PairOfDice(new Die(5), new Die(5));
		Player p = new Player(n, r);

		assertSame("Player's name field should be initialised with and return the same object received by the constructor", n, p.getName()); 
		assertSame("Player's rollable field should be initialised with and return the same object received by the constructor", r, p.getRollable()); 
	}

	@Test
	public void testSetAndGetName() {
		Name n = new Name("Joe", "Bloggs");
		Player p = new Player();
		p.setName(n);

		assertSame("Name field should be set to and return the same object", n, p.getName());
	}

	@Test
	public void testGetRollableDie() {
		Rollable r = new Die(5);
		Player p = new Player(new Name(), r);

		assertTrue("The getRollable method should return the current object instance held by the Rollable field",
				p.getRollable() == r);
	}

	@Test
	public void testGetRollablePairOfDice() {
		Rollable r = new PairOfDice(new Die(5), new Die(5));
		Player p = new Player(new Name(), r);

		assertTrue("The getRollable method should return the current object instance held by the Rollable field", 
				p.getRollable() == r);
	}

	@Test
	public void testRollDice() {
		Rollable r = new Die(5);
		Player p = new Player(new Name(), r);

		int initialScore = r.getScore();
		int i = 0;
		
		//attempts to roll the player's dice up to 1000 times, until the score of the Rollable reference received by the constructor has changed
		while (r.getScore() == initialScore && i < 1000) {
			p.rollDice();
			i++;
		}
		
		//if the score was not updated then the roll method cannot be working - you're not going to roll the same score 1000 times!
		if (i == 1000) {
			fail("The rollDice method did not change the score of the Rollable reference received by the constructor");
		} 
	}
	
	/* To ensure the score returned cannot be hardcoded, this test method firstly checks the initial score matches that of the
	 * Rollable reference passed to the constructor - if they do not, the getDiceScore method must have an issue.
	 * 
	 * It then continually invokes the roll method on the external Rollable reference until its score has been updated
	 * and will fail if there is a score mismatch at any time. 
	 */
	@Test
	public void testGetDiceScore() {
		Rollable r = new Die(5);
		Player p = new Player(new Name(), r);

		int initialScore = r.getScore();
		assertEquals("The getDiceScore method is not working so rollDice cannot be tested properly", initialScore, p.getDiceScore());
					
		do {
			r.roll();

			if (r.getScore() != p.getDiceScore()) {
				fail("Score mismatch between Rollable reference and the score returned by the Player");
			}
		} while (r.getScore() == initialScore);
	}

	@Test
	public void testSetFullPlayerName() {
		Player p = new Player();
		p.setFullPlayerName("Joe Bloggs");

		assertEquals("The Name object held by the Player should have its first name set to Joe", "Joe", p.getName().getFirstName());
		assertEquals("The Name object held by the Player should have its family name set to Bloggs", "Bloggs", p.getName().getFamilyName());
	
		/* Further check to avoid the possibility of hardcoding */
		p = new Player();
		p.setFullPlayerName("Tim Jones");

		assertEquals("The Name object held by the Player should have its first name set to Tim", "Tim", p.getName().getFirstName());
		assertEquals("The Name object held by the Player should have its family name set to Jones", "Jones", p.getName().getFamilyName());
	}

	@Test
	public void testToString() {
		Name n = new Name("Joe", "Bloggs");
		Rollable r = new PairOfDice(new Die(5), new Die(5));
		Player p = new Player(n, r);

		String toStr = p.toString();
		
		assertTrue("The toString method should be in the standard convention format as taught",
				toStr.startsWith("Player:[") &&
				toStr.contains(n.toString()) &&
				toStr.contains(r.toString()) &&
				toStr.endsWith("]"));

		/* Further check to avoid the result of toString being hardcoded */
		n = new Name("Fred", "Jones");
		r = new Die();
		p = new Player(n, r);

		toStr = p.toString();

		assertTrue("The toString method should be in the standard convention format as taught",
				toStr.startsWith("Player:[") &&
				toStr.contains(n.toString()) &&
				toStr.contains(r.toString()) &&
				toStr.endsWith("]"));
	}

	@Test
	public void testCompareTo() {
		Player p1 = new Player(new Name("Ted", "Bloggs"));
		Player p2 = new Player(new Name("Fred", "Bones"));
		Player p3 = new Player(new Name("Joe", "Bloggs"));
		
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		Collections.sort(players); //will only work on Comparable elements that have a compareTo method

		assertTrue("Elements should have been sorted based on the compareTo method of Name", 
				players.get(0) == p3 && players.get(1) == p1 && players.get(2) == p2);
	}



	/* -- GENERAL TESTS TO ENSURE CORRECT USE OF FIELDS AND METHODS -- */

	@Test
	public void testFieldModifiers() {
		Field[] fields = Player.class.getDeclaredFields();

		assertTrue("Modifiers cannot be assessed if no fields exist", fields.length > 0);
		
		assertTrue("All fields must be private", Arrays.stream(fields).allMatch(f -> (Modifier.PRIVATE & f.getModifiers()) != 0));

		assertTrue("No fields should be static", Arrays.stream(fields).allMatch(f -> (Modifier.STATIC & f.getModifiers()) == 0));
	}

	@Test
	public void testFieldNumber() {
		Field[] fields = Player.class.getDeclaredFields();

		assertEquals("The Player class should only have two fields", 2, fields.length);
	}
	
	@Test
	public void testFieldTypes() {
		Field[] fields = Player.class.getDeclaredFields();

		boolean correct = true;

		if (fields[0].getType().isAssignableFrom(Rollable.class)) {
			correct = fields[1].getType().isAssignableFrom(Name.class);
		} else {
			correct = fields[1].getType().isAssignableFrom(Rollable.class);
			if (correct) {
				correct = fields[0].getType().isAssignableFrom(Name.class);
			}
		}

		assertTrue("The Player class should only have fields of type Name and Rollable", correct);
	}

}
