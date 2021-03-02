package test;

import gamestructure.game.GameControllerImpl;
import gamestructure.game.GameViewImpl;
import gamestructure.ingamemenu.InGameMenuController;
import gamestructure.ingamemenu.InGameMenuControllerImpl;
import gamestructure.ingamemenu.InGameMenuView;
import gamestructure.ingamemenu.InGameMenuViewImpl;
import model.ModelImpl;


public class TestInGameMenu {

    public static void main(final String[] args) {
        final InGameMenuController controller = new InGameMenuControllerImpl(new GameControllerImpl(new ModelImpl()), new ModelImpl());
        controller.setup();
    }

}
