package model;

import java.util.Set;

public interface BingoCard {
    public static final int BINGO_CARD_WIDTH = 5;
    static final int MINIMUM_BINGO_CARD_VALUE = 1;
    static final int MAXIMUM_BINGO_CARD_VALUE = 75;
    public static final Integer FREE_SPACE = -1;

    public Integer getValueAtLocation(int row, int col);

}
