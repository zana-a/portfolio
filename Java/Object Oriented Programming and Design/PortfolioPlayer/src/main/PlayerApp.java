package main;

import lib.Player;

import java.util.ArrayList;

public class PlayerApp {

    /**
     * Used for testing. Part of the PlayerAppTest unit test. Please see testExecute
     * method.
     *
     * @param players  the array of players
     * @param fullName string object representing the first and last names of a
     * @return String object of all the players in a formatted style
     */
    public static String execute(ArrayList<Player> players, String fullName) {
        players.get(0).setFullPlayerName(fullName);

        String returnString = ""; // the string of players to return after the players are appended by the for
        // loop below

        for (Player player : players) {
            if (player.getName().getFullName().contains("a")) {
                returnString += player.getName().getFirstName().toLowerCase() + ", "
                        + player.getName().getFamilyName().toUpperCase() + "\n";
            }
        }

        return returnString;
    }
}
