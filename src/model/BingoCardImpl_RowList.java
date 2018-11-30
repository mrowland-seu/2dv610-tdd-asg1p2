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

    public Integer getEntry(int row, int col) {
        return rowListList.get(row-1).get(col-1);
    }
}
