package view;

import java.util.Scanner;

public class BingoView {
    private Scanner in;

    public BingoView() {
        in = new Scanner(System.in);
    }

    public boolean doesUserWantToContinue() {
        throw new RuntimeException("Not Implemented");
    }

    public String acceptUserInput() {
        return in.nextLine();
    }
}
