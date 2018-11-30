package view;

import java.util.Scanner;

public class BingoView {
    private Scanner in;

    public BingoView() {
        in = new Scanner(System.in);
    }

    public boolean doesUserWantToContinue() {
        String userChoice = acceptUserInput();
        if (userChoice.equals("y")) {
            return true;
        }
        return false;
    }

    public String acceptUserInput() {
        return in.nextLine();
    }
}
