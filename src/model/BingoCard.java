package model;

import java.util.List;

public interface BingoCard {
    public static final int BINGO_CARD_WIDTH = 5;
    static final int MINIMUM_BINGO_CARD_VALUE = 1;
    static final int MAXIMUM_BINGO_CARD_VALUE = 75;
    public static final Integer FREE_SPACE = -1;
    static final int FREE_SPACE_ROW = 3;
    static final int FREE_SPACE_COL = 3;

    public Integer getEntry(int row, int col);
    public void mark(int value);
    public boolean isMarked(int row, int col);
    public boolean contains(int value);
    public boolean isWinner();

    public Iterable<List<Integer>> getRowInformation();

}
