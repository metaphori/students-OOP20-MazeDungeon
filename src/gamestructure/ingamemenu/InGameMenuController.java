package gamestructure.ingamemenu;

import model.shop.Items;

public interface InGameMenuController {

    void buyItem(Items itemSelected);

    void openShop();

    void openInGameMenu();

    void exit();

}