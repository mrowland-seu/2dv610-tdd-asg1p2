package controller;

import model.BingoCard;
import model.BingoCardImpl_RowList;
import view.BingoView;

public class BingoGame_OnePlayer {
    private BingoView view;
    private BingoCard card;

    public BingoGame_OnePlayer(BingoView view) {
        this.view = view;
    }

    public boolean playGame() {
        //marking & win determination functionality to be added

        return view.doesUserWantToContinue();
    }

    //generate card funcitonality to be added
}
