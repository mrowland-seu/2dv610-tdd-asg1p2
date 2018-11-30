package controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import model.BingoCard;
import model.BingoCardImpl_RowList;
import view.BingoView;

public class BingoGame_OnePlayer {
    private BingoView view;
    private Random random;
    private BingoCard bingoCard;

    public BingoGame_OnePlayer(BingoView view) {
        this.view = view;
        random = new Random();
    }

    public boolean playGame() {
        bingoCard = generateBingoCard();
        int userGuesses = view.promptUserForNumberOfGuessesUntilBingo();




        return view.doesUserWantToContinue();
    }

    public BingoCard generateBingoCard() {
        Integer[][] values = new Integer[BingoCard.BINGO_CARD_WIDTH][BingoCard.BINGO_CARD_WIDTH];
        for (int i = 0; i < BingoCard.BINGO_CARD_WIDTH; i++) {
            int low = i*15+1;
            int high = (i+1)*15;
            Set<Integer> newColumn = new HashSet<>();
            while (newColumn.size()!= 5) {
                newColumn.add(random.nextInt(high - low) + low);
            }

            Iterator colIterator = newColumn.iterator();
            for (int j = 0; j < BingoCard.BINGO_CARD_WIDTH; j++) {
                values[j][i] = (Integer)colIterator.next();
            }
        }
        return new BingoCardImpl_RowList(values);
    }

    public void markNextNumber(BingoCard card) {
        int size = card.getIntegersMarked().size();
        while (size == card.getIntegersMarked().size()) {
            card.mark(random.nextInt(75)+1);
        }
    }

    public boolean checkWinner() {
        return bingoCard.isWinner();
    }
}
