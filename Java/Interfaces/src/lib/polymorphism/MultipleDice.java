package lib.polymorphism;

import java.util.ArrayList;
import java.util.List;

/** An example of an Aggregation class, i.e. one that encapsulates
 * a collection of objects - in this case a list of dice.
 * 
 * Common operations such as add, remove, get, etc, are provided.
 * There are additional operations to roll all die and get the total combined score.
 *  
 * The MultipleDice class implements the Rollable interface and therefore
 * specifies its required behaviour (rolling and retrieving a score).
 */
public class MultipleDice implements Rollable {

	// Fields
	private List<Die> dice;

	// Constructors
	public MultipleDice() {
		dice = new ArrayList<>();
	}

	public MultipleDice(int init_size) {
		dice = new ArrayList<>();

		for(int count = 0; count < init_size; count++) {
			dice.add(new Die());
		}
	}	

	// Methods
	public void addDie(Die die) {
		dice.add(die);
	}

	public void removeDie(int index) {
		dice.remove(index);
	}

	public Die getDie(int index) {
		return dice.get(index);
	}

	public int getSize() {
		return dice.size();
	}

	public boolean isEmpty(){
		return dice.isEmpty();
	}

	public void clear() {
		dice.clear();
	}

	@Override
	public void roll() {
		/*for (Die element: dice) {
			element.roll();
		}*/
		
		//same as above using the forEach method
		dice.forEach(d -> d.roll());
	}

	@Override
	public int getScore() {
		return dice.stream().mapToInt(d -> d.getScore()).sum();
	}

	@Override
	public String toString() {
		return "MultipleDice:[dice=" + dice + "]";
	}
}