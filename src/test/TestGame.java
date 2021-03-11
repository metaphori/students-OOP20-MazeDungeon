package test;

import gamestructure.game.GameController;
import gamestructure.game.GameControllerImpl;
import model.ModelImpl;
import mvc.Model;

public class TestGame {
    public static void main(final String[] args) {
        final Model model = new ModelImpl();
        final GameController controller = new GameControllerImpl(model);
        controller.setup();
        controller.mainLoop();
    }
}