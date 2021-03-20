package gamestructure.ingamemenu;

import gamestructure.Controller;
import model.shop.Items;
public interface InGameMenuController extends Controller {

    void buyItem(Items itemSelected);

    void openShop();

    boolean openInGameMenu();

    void exit();

    void resume();
}
