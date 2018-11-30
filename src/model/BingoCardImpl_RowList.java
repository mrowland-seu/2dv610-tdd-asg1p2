package model;

import java.util.*;

public class BingoCardImpl_RowList implements BingoCard {
    private List<List<Integer>> rowListList;
    private Set<Integer> integersMarked;

    public BingoCardImpl_RowList(Integer[][] cardValues) {
        integersMarked = new HashSet<>();

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
}
