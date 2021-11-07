package lib;

import java.util.Random; //importing the Random class

/** 
 * A Die has a number of sides and a score shown by its face value.
 * The die can be rolled to give a new score.
 * 
 * The Die class implements the Rollable interface and therefore
 * specifies its required behaviour (rolling and retrieving a score).
 * 
 * @author la/lz
 */
public class Die implements Rollable {

	//Fields
	private int sides; 
	private int score;
	
	/* A random number generator instance. 
	 * 'static' modifier means that the same Random object is used by all instances of Die. 
	 */
	private static Random rand = new Random();
	
	
	//Constructors
	/** Default constructor that creates a die with six sides. 
	 * The die is rolled to give a random initial score.  
	 */
	public Die() {
		this(6); //calls the custom constructor with the default of six sides
	}

	/** Creates a die with the given number of sides. 
	 * The die is rolled to give a random initial score.  
	 * 
	 * @param sides the number of sides the die will have.
	 */
	public Die(int sides){
		this.sides = sides;
		this.roll();
	}
	
	
	//Methods
	/** Rolls the die to give the next score. The score is a
	 * randomly generated number in the range 1..sides inclusive. 
	 */
	@Override
	public void roll() {
		score = rand.nextInt(sides) + 1;
	}
		
	/** Returns the number of sides on the die.
	 * 
	 * @return the number of sides on the die.
	 */
	public int getSides() {
		return sides;
	}

	/** Returns the current face value of the die.
	 * 
	 * @return the current face value of the die.
	 */
	@Override
	public int getScore() {
		return score;
	}
	
	/** Returns a textual representation of the die.
	 * 
	 * @return a textual representation of the die.
	 */
	@Override
	public String toString() {
		return "Die:[sides=" + sides + ", score=" + score + "]";
	}
	
	/** Compares this die to the specified object. The result is true if and 
	 * only if the argument is not null and is a Die object that has the same
	 * number of sides and score (i.e. face value) as this object.
	 * 
	 * @param obj the object to compare this Die against.
	 * 
	 * @return true if the given object represents a Die equivalent to this die, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		// test exceptional cases, i.e. obj not null and is a Die
		if (obj == null  || this.getClass() != obj.getClass())
			return false;

		Die other = (Die) obj; // downcast to a Die object

		// compare sides and score using the == operator as they are primitive types
		return this.sides == other.sides && this.score == other.score;
	}

	
}