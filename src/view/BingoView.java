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
        String userChoice = "";
        while(!userChoice.equals("y") && !userChoice.equals("n")) {
            userChoice = acceptUserInput();
        }
        if (userChoice.equals("y")) {
            return true;
        }
        return false;
    }

    //can't reach full coverage on this method, since all it does is uses a scanner and i mock its return value every time
    public String acceptUserInput() {
        return in.nextLine();
    }

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

    public void displayWinMessage(int guesses) {
        displayOutput("You Won in " + guesses + " guesses!");
    }

    public int promptUserForNumberOfGuessesUntilBingo() {
        displayOutput("Enter the number of guesses you think it will take to reach bingo: ");
        return Integer.getInteger(acceptUserInput());
    }
}
