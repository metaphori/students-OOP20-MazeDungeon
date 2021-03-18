package gamestructure.ingamemenu;

import model.shop.Items;
import mvc.Controller;
public interface InGameMenuController extends Controller {

    void buyItem(Items itemSelected);

    void openShop();

    boolean openInGameMenu();

    void exit();

    void resume();
}
