package view;

import model.BingoCard;

import java.util.List;
import java.util.Scanner;

public class BingoView {
    private Scanner in;

    public BingoView() {
        in = new Scanner(System.in);
    }

    public boolean doesUserWantToContinue() {
        displayOutput("would you like to play again (y/n): ");
        String userChoice = "";
        while(!userChoice.equals("y") && !userChoice.equals("n")) {
            userChoice = acceptUserInput();
        }
        return userChoice.equals("y");
    }

    //can't reach full coverage on this method, since all it does is uses a scanner and i mock its return value every time
    public String acceptUserInput() {
        return in.next();
    }

    /*
    public void printWelcome() {
        displayOutput("Welcome!");
    }
    */

    public void printBingoCard(BingoCard bingoCard) {
        List<List<Integer>> rowInformation = bingoCard.getRowInformation();

        String retVal = "";
        for (List<Integer> row : rowInformation) {
            for (Integer value : row) {
                if (value == -1) {
                    retVal += "FS ";
                } else {
                    retVal += value + " ";
                }
            }
            retVal += "\n";
        }
        displayOutput(retVal);
    }

    public void displayOutput(String output) {
        System.out.println(output);
    }

    public void displayWinMessage(int guess, int actual) {
        if (guess == actual) {
            displayOutput("You guessed the correct number of turns to reach bingo!");
        } else {
            displayOutput("You guessed it would take " + guess + " turns but it actually took " + actual + " turns.");
        }
    }

    public int promptUserForNumberOfGuessesUntilBingo() {
        displayOutput("Enter the number of guesses you think it will take to reach bingo: ");
        return in.nextInt();
    }
}
