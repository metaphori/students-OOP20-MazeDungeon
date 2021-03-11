package test;

import gamestructure.game.GameControllerImpl;
import gamestructure.ingamemenu.InGameMenuController;
import gamestructure.ingamemenu.InGameMenuControllerImpl;
import model.ModelImpl;


public class TestInGameMenu {

    public static void main(final String[] args) {
        final InGameMenuController controller = new InGameMenuControllerImpl(new GameControllerImpl(new ModelImpl()), new ModelImpl());
        controller.setup();
    }

}
