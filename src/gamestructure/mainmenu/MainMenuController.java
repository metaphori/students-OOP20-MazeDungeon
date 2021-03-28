package gamestructure.mainmenu;

import gamestructure.Controller;

/**
 * This interface represent the Controller of the MainMenu, 
 * it's main task consist in the creation of a new game.
 */
public interface MainMenuController extends Controller {

    /**
     * Provide to create a new game.
     */
    void newGame();
}
