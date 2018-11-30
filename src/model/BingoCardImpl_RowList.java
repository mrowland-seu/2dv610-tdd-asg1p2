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
        return isHorizontalWinner() || isVerticalWinner() || isDiagonalWinner();
    }

    private boolean isHorizontalWinner() {
        boolean isWinnerByRow = true;
        //cycles through each row
        for (int row = 1; row <= BINGO_CARD_WIDTH; row++) {
            int column = 1;
            isWinnerByRow = true;
            //checks 1 row, will break if a single spot in the row is not marked
            while (column <= BINGO_CARD_WIDTH && isWinnerByRow){
                isWinnerByRow = isMarked(row, column);
                column++;
            }
            //if a winner was found break for loop and return
            if (isWinnerByRow) break;
        }
        return isWinnerByRow;
    }

    private boolean isVerticalWinner() {
        boolean isWinnerByColumn = true;
        //cycles through each col
        for (int column = 1; column <= BINGO_CARD_WIDTH; column++) {
            isWinnerByColumn = true;
            int row = 1;
            //checks 1 col, will break if a single spot in the col is not marked
            while (row <= BINGO_CARD_WIDTH && isWinnerByColumn) {
                isWinnerByColumn = isMarked(row, column);
                row++;
            }
            //if a winner was found break for loop and return
            if (isWinnerByColumn) break;
        }
        return isWinnerByColumn;
    }

    private boolean isDiagonalWinner() {
        int row = 1;
        int col = 1;
        boolean isWinnerOnDiagonal = true;
        while (row <= BINGO_CARD_WIDTH && col <= BINGO_CARD_WIDTH && isWinnerOnDiagonal) {
            isWinnerOnDiagonal = isMarked(row, col);
            row++; col++;
        }
        if (!isWinnerOnDiagonal) {
            isWinnerOnDiagonal = true;
            row = BINGO_CARD_WIDTH;
            col = 1;
            while (row >= 0 && col <= BINGO_CARD_WIDTH && isWinnerOnDiagonal) {
                isWinnerOnDiagonal = isMarked(row, col);
                row--;
                col++;
            }
        }
        return isWinnerOnDiagonal;
    }

    @Override
    public Iterable<List<Integer>> getRowInformation() {
        return rowListList;
    }
}
