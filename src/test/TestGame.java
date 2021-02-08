package test;

import gamestructure.game.GameView;
import gamestructure.game.GameViewImpl;

public class TestGame {
    public static void main(final String[] args) {
        GameView view = new GameViewImpl();
        view.show();
    }

}
