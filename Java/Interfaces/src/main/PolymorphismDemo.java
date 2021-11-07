package main;

import java.util.ArrayList;
import java.util.List;

import lib.polymorphism.Die;
import lib.polymorphism.MultipleDice;
import lib.polymorphism.PairOfDice;
import lib.polymorphism.Rollable;

public class PolymorphismDemo {

	public static void main(String[] args) {
		List<Rollable> dice = new ArrayList<>();

		dice.add(new Die());
		dice.add(new PairOfDice());
		dice.add(new MultipleDice(5));

		for (Rollable r : dice) {
			System.out.println(r.toString());
			
			r.roll();
			System.out.println("Score after roll? " + r.getScore() + "\n");	
		}

	}

}
