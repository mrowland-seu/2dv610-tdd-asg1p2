package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import controller.BingoGame_OnePlayer;
import model.*;
import view.BingoView;

public class BingoGameTest {

    BingoView view;
    BingoGame_OnePlayer controller;

    @Before
    public void initTests() {
    }

    @Test
    public void constructorCompletedSuccessfullyForRowBasedBingoCard() {
        BingoCard bingoCard = createRowListBingoCard(getLowestPossibleBingoCardValueArray());
    }

    private BingoCard createRowListBingoCard(Integer[][] values) {
        return new BingoCardImpl_RowList(values);
    }

    private Integer[][] getLowestPossibleBingoCardValueArray() {
        return new Integer[][] {
                {1,16,31,46,61},
                {2,17,31,47,62},
                {3,18,BingoCard.FREE_SPACE,48,63},
                {4,19,33,48,64},
                {5,20,35,50,65}
        };
    }
}
