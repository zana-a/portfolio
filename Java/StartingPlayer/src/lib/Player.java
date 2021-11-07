package lib;

/**
 * Player Class
 * <p>
 * A player class to associate a name (full name) and a pair of dice.
 *
 * @author p2401470
 */
public class Player {
    private Name name;
    private PairOfDice pairOfDice;

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
     * @param pairOfDice an object of class PairOfDice. Takes in two dice max number of sides.
     */
    public Player(Name name, PairOfDice pairOfDice) {
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
     * @return a pre-formatted string outputting the sides and score of the dice pair.
     */
    public PairOfDice getPairOfDice() {
        return pairOfDice;
    }

    /**
     * Roll the dice pair.
     */
    public void rollDice() {
        pairOfDice.getBlue().roll();
        pairOfDice.getRed().roll();
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
     * @return a pre-formatted string of the players full name and pairOfDice results and sides.
     */
    @Override
    public String toString() {
        return "Player:[" + name + ", " + pairOfDice + "]";
    }
}
