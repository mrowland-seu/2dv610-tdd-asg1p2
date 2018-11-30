package model;

import java.util.List;
import java.util.Set;

public interface BingoCard {
    int BINGO_CARD_WIDTH = 5;
    int MINIMUM_BINGO_CARD_VALUE = 1;
    int MAXIMUM_BINGO_CARD_VALUE = 75;
    Integer FREE_SPACE = -1;
    int FREE_SPACE_ROW = 3;
    int FREE_SPACE_COL = 3;

    Integer getEntry(int row, int col);
    void mark(int value);
    boolean isMarked(int row, int col);
    boolean contains(int value);
    boolean isWinner();

    List<List<Integer>> getRowInformation();
    Set<Integer> getIntegersMarked();

}
