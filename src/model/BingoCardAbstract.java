package model;

import java.util.HashSet;
import java.util.Set;

public abstract class BingoCardAbstract implements BingoCard {
    Set<Integer> integersMarked;

    public BingoCardAbstract() {
        integersMarked = new HashSet<>();
    }

    public void mark(int value) {
        integersMarked.add(value);
    }

    public boolean isMarked(int row, int col) {
        if (row == FREE_SPACE_ROW && col == FREE_SPACE_COL)
            return true;
        return integersMarked.contains(getEntry(row,col));
    }
}
