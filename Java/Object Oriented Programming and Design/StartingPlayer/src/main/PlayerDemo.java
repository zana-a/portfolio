package main;

import lib.Name;
import lib.Player;

public class PlayerDemo {
    public static void main(String[] args) {
        Player p1 = new Player();
        Player p2 = new Player(new Name("Bob", "Doe"));

        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p2.getName());
        System.out.println(p2.getPairOfDice());
    }
}
