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
}
