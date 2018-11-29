package model;

import java.util.Set;

public abstract class BingoCard {
    protected static final int BINGO_CARD_WIDTH = 5;
    protected static final int MINIMUM_BINGO_CARD_VALUE = 1;
    protected static final int MAXIMUM_BINGO_CARD_VALUE = 75;

    protected Set<Integer> integersMarked;

    BingoCard() {

    }

}
