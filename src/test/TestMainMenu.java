package test;

import gamestructure.mainmenu.MainMenuController;
import gamestructure.mainmenu.MainMenuControllerImpl;

public class TestMainMenu {

    public static void main(final String[] args) {
        final MainMenuController controller = new MainMenuControllerImpl();
        controller.setup();
    }

}