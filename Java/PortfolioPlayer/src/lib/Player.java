package lib;

/**
 * A player class to associate a Name and PairOfDice objects.
 *
 * @author p2401470
 */
public class Player implements Comparable<Player> {

    private Name name;
    private Rollable pairOfDice;

    /**
     * Default Constructor
     * <p>
     * Takes in nothing and sets the default value
     */
    public Player() {
        this.name = new Name();
        this.pairOfDice = new PairOfDice();
    }

    /**
     * Custom Constructor 1 (name)
     *
     * @param name an object of class Name. Takes in first and last names.
     */
    public Player(Name name) {
        this.name = name;
        this.pairOfDice = new PairOfDice();
    }

    /**
     * Custom Constructor 2 (name & pairOfDice)
     *
     * @param name       an object of class Name. Takes in first and last names.
     * @param pairOfDice an object of class PairOfDice. Takes in two dice max number
     *                   of sides.
     */
    public Player(Name name, Rollable pairOfDice) {
        this.name = name;
        this.pairOfDice = pairOfDice;
    }

    /**
     * Get Name
     *
     * @return a pre-formatted string outputting the first and last names.
     */
    public Name getName() {
        return name;
    }

    /**
     * Set Name
     *
     * @param name the new name given as a Name type object.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Set full player name
     * <p>
     * Sets the full player name
     *
     * @param fullPlayerName the full given name separated by a space character
     */
    public void setFullPlayerName(String fullPlayerName) {
        String[] playerName = fullPlayerName.split(" ");
        this.name = new Name(playerName[0], playerName[1]);
    }

    /**
     * @return a pre-formatted string outputting the sides and score of the dice
     * pair.
     */
    public Rollable getRollable() {
        return pairOfDice;
    }

    /**
     * Roll the dice pair.
     */
    public void rollDice() {
        pairOfDice.roll();
    }

    /**
     * Get the dice score
     *
     * @return the total sum of the dice scores.
     */
    public int getDiceScore() {
        return pairOfDice.getScore();
    }

    /**
     * @return a formatted string of the players full name and pairOfDice results
     * and sides.
     */
    @Override
    public String toString() {
        return "Player:[" + name + ", " + pairOfDice + "]";
    }

    /**
     * Compares the Name objects of players. If surnames are the same, then it uses
     * the names to compare the values.
     * <p>
     * A negative number is returned if the difference is less than the object you
     * are comparing with. A positive number is returned if the difference is
     * greater. A 0 is returned if the values are equal.
     *
     * @param p the player object to compare with
     * @return the difference between the object you are comparing to parameter p
     */
    @Override
    public int compareTo(Player p) {
        return this.getName().compareTo(p.getName());
    }
}
