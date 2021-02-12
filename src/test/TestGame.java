package test;

import core.ModelImpl;
import gamestructure.game.GameController;
import gamestructure.game.GameControllerImpl;
import gamestructure.game.GameView;
import gamestructure.game.GameViewImpl;
import mvc.Model;

public class TestGame {
    public static void main(final String[] args) {
        final GameView view = new GameViewImpl();
        final Model model = new ModelImpl();
        final GameController controller = new GameControllerImpl(view, model);
        controller.setup();
        controller.mainLoop();
    }

}
