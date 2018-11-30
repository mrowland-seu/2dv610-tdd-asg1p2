package view;

import model.BingoCard;

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
        throw new RuntimeException("Not Implemented");
    }
}
