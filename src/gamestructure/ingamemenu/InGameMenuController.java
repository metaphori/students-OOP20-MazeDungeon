package gamestructure.ingamemenu;

import gamestructure.Controller;
import model.shop.Items;

/**
 * This interface represent the Controller of the InGameMenu, 
 * its main task is to create and manage the InGameMenu.
 */
public interface InGameMenuController extends Controller {
    /**
     * Set new message and check the item.
    * @param itemSelected 
    */
    void buyItem(Items itemSelected);
    /**
     * open the shop view.
     */
    void openShop();
    /**
     * open the in game menu view.
     * @return boolean : true if the in game menu opened
     */
    boolean openInGameMenu();
    /**
     * close the game.
     */
    void exit();
    /**
     * resume the game and closes the menu in game.
     */
    void resume();
}
