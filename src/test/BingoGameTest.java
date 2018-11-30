package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import controller.BingoGame_OnePlayer;
import model.*;
import view.BingoView;

public class BingoGameTest {
    private BingoView view;
    private BingoGame_OnePlayer controller;

    private BingoView mockedView;
    private BingoGame_OnePlayer spyBingoGame;

    @Before
    public void initTests() {
        view = new BingoView();
        mockedView = mock(BingoView.class);
        controller = new BingoGame_OnePlayer(mockedView);
        spyBingoGame = spy(controller);
    }

    @Test
    public void constructorCompletedSuccessfullyForRowBasedBingoCard() {
        BingoCard bingoCard = createRowListBingoCard(getLowestPossibleBingoCardValueArray());
    }

    @Test
    public void properFreeSpaceLocationOfRowBasedBingoCard() {
        BingoCard bingoCard = createRowListBingoCard(getLowestPossibleBingoCardValueArray());
        //getValueAtLocation requires 1-based row and column numbers
        assertEquals(BingoCard.FREE_SPACE, bingoCard.getEntry(3, 3));
    }

    @Test
    public void returnFalseOnUserDecideToExit() {
        when(mockedView.doesUserWantToContinue()).thenReturn(false);
        boolean retVal = controller.playGame();
        verify(mockedView).doesUserWantToContinue();
        assertFalse(retVal);
    }

    @Test
    public void bingoCardProperlyMarked() {
        BingoCard bingoCard = createRowListBingoCard(getLowestPossibleBingoCardValueArray());
        bingoCard.mark(1);
        assertTrue(bingoCard.isMarked(1,1));
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
