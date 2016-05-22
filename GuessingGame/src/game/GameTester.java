package game;

import javax.swing.JOptionPane;

/**
 * @author Hae Yeon Kang and Cindy Diaz
 * @version 5th August 2015
 *
 */
public class GameTester {

    public static void main(String[] args) {
        int lowerLimit; 	// lower limit of the range
        int upperLimit; 	// upper limit of the range
        int numOfChance; 	// number of chances the user gets
        int response; 		// whether the player want to continue the game or not

        do {
            try {
                //ask user to set the range and the number of chances they want
                lowerLimit = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Please enter the range of numbers to guess. (Numbers entered inclusive) \nLower Limit: ",
                        "Enter Lower Limit", JOptionPane.QUESTION_MESSAGE));

                upperLimit = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Upper Limit: ",
                        "Enter Upper Limit",
                        JOptionPane.QUESTION_MESSAGE));

                numOfChance = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Please enter the number of chances to guess : ",
                        "Enter Number of Chances",
                        JOptionPane.QUESTION_MESSAGE));

                //create the object
                GuessingGame game = new GuessingGame(lowerLimit, upperLimit, numOfChance);

                //play guessing game
                //game.playGame() method will be used twice so it is better to store the value in a variable.
                boolean result = game.playGame();

                //display winning or losing message here
                String message = result ? "You got the number! You Win!" : "You used all your chances.. Game Over.";
                JOptionPane.showMessageDialog(null,
                        message,
                        "Result",
                        JOptionPane.INFORMATION_MESSAGE);

                //show the result of the game they just played
                JOptionPane.showMessageDialog(null,
                        game.toString() + "\n Result = " + (result ? "WON" : "LOST"),
                        "Game Result",
                        JOptionPane.PLAIN_MESSAGE);

            } catch (IllegalArgumentException e) { //throw error message if there's any
                JOptionPane.showMessageDialog(null,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }//of catch

            //ask player if they want to play the game again
            response = JOptionPane.showConfirmDialog(null,
                    "Do you wish to continue?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

        } while (response == JOptionPane.YES_OPTION); //the game will start again if player pressed yes.

        //if not, they will get Good Bye message and the game is terminated
        JOptionPane.showMessageDialog(null,
                "Good Bye.",
                "Game closing..",
                JOptionPane.INFORMATION_MESSAGE);
    }//of main
}//of GameTester class
