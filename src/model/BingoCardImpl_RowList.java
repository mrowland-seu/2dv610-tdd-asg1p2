package model;

import java.util.*;

public class BingoCardImpl_RowList extends BingoCardAbstract implements BingoCard  {
    private List<List<Integer>> rowListList;

    public BingoCardImpl_RowList(Integer[][] cardValues) {
        super();
        rowListList = new ArrayList<>();
        for (Integer[] row : cardValues) {
            rowListList.add(Arrays.asList(row));
        }
    }

    //precond: row and col must be valid 1-based values corresponding to a column or row of a bingo card, with maximum size = BINGO_CARD_WIDTH
    public Integer getEntry(int row, int col) {
        assert (row >= 1 && row <= BINGO_CARD_WIDTH);
        assert (col >= 1 && col <= BINGO_CARD_WIDTH);

        return rowListList.get(row-1).get(col-1);
    }

     public boolean contains(int value) {
        boolean cardContainsNumber = false;
        for (List<Integer> currentRow : rowListList) {
            if (currentRow.contains(value))
                cardContainsNumber = true;
        }
        return cardContainsNumber;
    }

    public boolean isWinner() {
        boolean isWinner = true;
        for (int row = 1; row <= BINGO_CARD_WIDTH; row++) {
            int column = 1;
            isWinner = true;
            while (column <= BINGO_CARD_WIDTH && isWinner){
                isWinner = isMarked(row, column);
                column++;
            }
            if (isWinner) break;
        }
        return isWinner;
    }
}
