package test;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import controller.BingoGame_OnePlayer;
import model.*;
import view.BingoView;

import java.util.ArrayList;
import java.util.List;

public class BingoGameTest {
    private BingoView view;
    private BingoGame_OnePlayer controller;

    private BingoView mockedView;
    private BingoView spyView;
    private BingoGame_OnePlayer spyBingoGame;

    @Before
    public void initTests() {
        view = new BingoView();
        mockedView = mock(BingoView.class);
        spyView = spy(view);
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
    public void returnFalseOnUserDecideToExitNoInput() {
        when(mockedView.doesUserWantToContinue()).thenReturn(false);
        boolean retVal = controller.playGame();
        verify(mockedView).doesUserWantToContinue();
        assertFalse(retVal);
    }

    @Test
    public void bingoCardMarks() {
        Integer[][] values = getLowestPossibleBingoCardValueArray();
        BingoCard bingoCard = createRowListBingoCard(values);
        bingoCard.mark(values[0][0]);
        assertTrue(bingoCard.isMarked(1,1));
        assertTrue(bingoCard.isMarked(3,3));
    }

    @Test
    public void correctInputAllowsUserToConfirmOrDenyExitForDoesUserWantToContinue() {
        doReturn("y").when(spyView).acceptUserInput();
        boolean rv = spyView.doesUserWantToContinue();
        assertTrue(rv);
        doReturn("n").when(spyView).acceptUserInput();
        rv = spyView.doesUserWantToContinue();
        assertFalse(rv);
    }

    @Test
    public void refuseToProceedWithoutCorrectInputOnDoesUserWantToContinue() {
        doReturn("1").doReturn("e").doReturn("y").when(spyView).acceptUserInput();
        boolean rv = spyView.doesUserWantToContinue();
        verify(spyView, times(3)).acceptUserInput();
        assertTrue(rv);
    }

    @Test
    public void checkIfBingoCardContainsValuesGiventoContains() {
        Integer[][] values = getLowestPossibleBingoCardValueArray();
        BingoCard bingoCard = createRowListBingoCard(values);
        assertTrue(bingoCard.contains(values[3][1]));
        assertTrue(bingoCard.contains(values[4][0]));
        assertTrue(bingoCard.contains(values[0][4]));
    }

    @Test
    public void checkWinningBingoCardContainsHorizontalWinnerNoFreeSpace() {
        Integer[][] values = getLowestPossibleBingoCardValueArray();
        BingoCard bingoCard = createRowListBingoCard(values);

        for(int i = 0; i < 5; i++) {
            bingoCard.mark(values[0][i]);
        }
        assertTrue(bingoCard.isWinner());
    }

    @Test
    public void checkWinningBingoCardContainsVerticalWinnerNoFreeSpace() {
        Integer[][] values = getLowestPossibleBingoCardValueArray();
        BingoCard bingoCard = createRowListBingoCard(values);

        for(int i = 0; i < 5; i++) {
            bingoCard.mark(values[i][4]);
        }
        assertTrue(bingoCard.isWinner());
    }

    @Test
    public void checkWinningBingoCardContainsDiagonalWinner() {
        Integer[][] values = getLowestPossibleBingoCardValueArray();
        BingoCard bingoCardTLtoBR = createRowListBingoCard(values);

        for(int i = 0; i < 5; i++) {
            bingoCardTLtoBR.mark(values[i][i]);
        }
        assertTrue(bingoCardTLtoBR.isWinner());

        BingoCard bingoCardTRtoBL = createRowListBingoCard(values);
        int j = 4;
        for(int i = 0; i < 5; i++) {
            bingoCardTRtoBL.mark(values[j][i]);
            j--;
        }
        assertTrue(bingoCardTRtoBL.isWinner());
    }

    @Test
    public void checkLosingBingoCardContainsDiagonalWinner() {
        Integer[][] values = getLowestPossibleBingoCardValueArray();
        BingoCard bingoCard = createRowListBingoCard(values);

        bingoCard.mark(values[0][4]);
        assertFalse(bingoCard.isWinner());
    }

    @Test
    public void testPrintBingoCard() {
        Integer[][] values = getLowestPossibleBingoCardValueArray();
        BingoCard bingoCard = createRowListBingoCard(values);

        spyView.printBingoCard(bingoCard);
        String properCardString =
                "1 16 31 46 61 \n" +
                "2 17 31 47 62 \n" +
                "3 18 FS 48 63 \n" +
                "4 19 33 48 64 \n" +
                "5 20 35 50 65 \n";
        verify(spyView).displayOutput(properCardString);
    }

    @Test
    public void testCreateNewBingoCard() {
        BingoCard card = spyBingoGame.generateBingoCard();
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k<5; k++) {
                int numToVerify = card.getEntry(k+1,i+1);
                assert (numToVerify > i*15 && numToVerify <= (i+1)*15);
            }
        }
        view.printBingoCard(card);
    }

    @Test
    public void playGameCreatesNewBingoCardOnRun() {
        spyBingoGame.playGame();
        verify(spyBingoGame, times(1)).generateBingoCard();
    }

    @Test
    public void userAskedForNumberOfGuessesUntilBingo() {
        spyBingoGame.playGame();
        verify(mockedView, times(1)).promptUserForNumberOfGuessesUntilBingo();
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
