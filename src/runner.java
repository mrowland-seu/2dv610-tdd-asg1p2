import controller.BingoGame_OnePlayer;
import view.BingoView;

public class runner {
    public static void main(String[] args) {
        BingoView view = new BingoView();
        BingoGame_OnePlayer game = new BingoGame_OnePlayer(view);

        boolean continuePlaying = true;
        while (continuePlaying) {
            continuePlaying = game.playGame();
        }
    }
}
