package game;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * @author Hae Yeon Kang and Cindy Diaz
 * @version 5th August 2015
 */
public class GuessingGame {
    //instance variables

    private final Random randomNumber;// random number generator
    private ArrayList<Integer> guesses;// guesses that user gives
    private int lowerLimit;// lower limit that was set by the user
    private int upperLimit;// upper limit that was set by the user
    private int numOfChance;// number of chances that was set by the user
    private int guessNum; // random number that the computer generates

    public enum Status {
        WON, LOST, CONTINUE
    }; 	// status of the game

    /**
     * This constructor initializes the variables
     *
     * @param lowerLimit the lower limit entered by the user
     * @param upperLimit the upper limit entered by the user
     * @param numOfChance the number of chances entered by the user
     */
    public GuessingGame(int lowerLimit, int upperLimit, int numOfChance)
            throws IllegalArgumentException {
        randomNumber = new Random();
        guesses = new ArrayList<Integer>();
        setNumOfChance(numOfChance);
        setLowerLimit(lowerLimit);
        setUpperLimit(upperLimit);
        setGuessNum();
    }//of constructor

    /**
     * This method returns the lower limit set by the user
     *
     * @return lowerLimit the lower limit of range of numbers
     */
    public int getLowerLimit() {
        return lowerLimit;
    }//of getLowerLimit

    /**
     * This method returns the upper limit set by the user
     *
     * @return upperLimit the upper limit of range of numbers
     */
    public int getUpperLimit() {
        return upperLimit;
    }//of getUpperLimit

    /**
     * This method returns the number of chances set by the user
     *
     * @return numOfChance the number of chances that user has to guess the
     * random number
     */
    public int getNumOfChance() {
        return numOfChance;
    }//of getNumOfChance

    /**
     * This method sets the lower limit that is given by the user.
     *
     * @param lowerLimit the lower limit of the range of numbers
     */
    public void setLowerLimit(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }//of setLowerLimit

    /**
     * This method sets the upper limit that is given by the user. If the user
     * gives the lower or same value as lower limit, it throws error.
     *
     * @param upperLimit the upper limit of the range of numbers
     */
    public void setUpperLimit(int upperLimit) {
        //validate the upper limit, if bad, throw error message.
        if (lowerLimit >= upperLimit) {
            throw new IllegalArgumentException("The upper limit is less than or equal to lower limit.");
        } else {
            this.upperLimit = upperLimit;
        }
    }//of setUpperLimit

    /**
     * This method sets the number of chances that user can guess the number in
     * one play. If the value is not valid, user will get an error message.
     *
     * @param numOfChance the number of chances that user can guess
     */
    public void setNumOfChance(int numOfChance) {
        //validate the number of chances, if bad, throw error message.
        if (numOfChance <= 0) {
            throw new IllegalArgumentException("The number of chance to guess should be more than 0");
        } else {
            this.numOfChance = numOfChance;
        }
    }//of setNumOfChance

    /**
     * This method sets a random number within the range that set by the user
     * The number is lower & upper limit inclusive
     *
     * @return guessNum the number set by the computer
     */
    public void setGuessNum() {
        guessNum = randomNumber.nextInt(getUpperLimit() - getLowerLimit() + 1) + getLowerLimit();
    }//of setNumber

    /**
     * This method evaluate if the number the user guess is equal or not and
     * saves the numbers guessed by the user
     *
     * @return gameStatus return WON, if the user wins otherwise CONTINUE
     */
    public Status play(int userGuess) {
        Status gameStatus = Status.CONTINUE;

        //check if the user's guess is lower to display message
        if (userGuess < guessNum) {
            JOptionPane.showMessageDialog(null,
                    "The number is higher than " + userGuess,
                    "Wrong Number",
                    JOptionPane.WARNING_MESSAGE);
            //add the user's guess in the guesses arraylist
            guesses.add(userGuess);

        } //check if the user's guess is higher to display message
        else if (userGuess > guessNum) {
            JOptionPane.showMessageDialog(null,
                    "The number is lower than " + userGuess,
                    "Wrong Number",
                    JOptionPane.WARNING_MESSAGE);
            guesses.add(userGuess);
        } else {
            guesses.add(userGuess);
            gameStatus = Status.WON;
        }
        return gameStatus;
    }//of play

    /**
     * This method will play the guessing game by looping until the user guesses
     * the number or uses all the chances
     *
     * @return true if the player won, false if they lose.
     */
    public boolean playGame() {
        int chances = getNumOfChance();
        Status result = Status.CONTINUE;

        //while the chances are left AND the status is continue, it will loop to ask number.
        while (chances > 0 && result == Status.CONTINUE) {
            int userGuess = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Please guess a number between " + getLowerLimit() + " and " + getUpperLimit() + "\n You have " + chances + " attempt left.",
                    "Enter a number",
                    JOptionPane.QUESTION_MESSAGE));

            //if the number entered is lower than lowerLimit or higher than upperLimit, throw error.
            if (userGuess < getLowerLimit() || userGuess > getUpperLimit()) {
                userGuess = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Your guess is not valid.\nPlease guess a number between " + getLowerLimit() + " and " + getUpperLimit(),
                        "Enter a number",
                        JOptionPane.QUESTION_MESSAGE));
            }

            //get the result from the class GuessingGame
            result = play(userGuess);

            //after each guess, the chances go down by -1
            chances--;

        }//of while

        //check if the result was WON or LOST
        if (result == Status.WON) {
            return true;
        } else {
            return false;
        }
    }//of playGame()

    /**
     * This method returns the result of the game in String
     *
     * @return the details of the game that user just played
     */
    @Override
    public String toString() {
        String listOfGuesses = "";

        //loop through the player's guesses and add them in String
        for (int g : guesses) {
            listOfGuesses += g + "  ";
        }

        return "[ Guessing Game ]"
                + "\n Number = " + guessNum
                + "\n Lower Limit = " + lowerLimit
                + "\n Upper Limit = " + upperLimit
                + "\n Number of Chances = " + numOfChance
                + "\n Your guesses = " + listOfGuesses;
    }//of toString

}
