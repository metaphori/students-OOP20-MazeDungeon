package gamestructure.ingamemenu;

import model.shop.Items;
import mvc.Controller;
public interface InGameMenuController extends Controller {
    /**
    * @param itemSelected
    */
    void buyItem(Items itemSelected);

    void openShop();

    void openInGameMenu();

    void exit();

    void resume();
}
