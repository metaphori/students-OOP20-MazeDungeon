package gamestructure.ingamemenu;

import model.shop.Items;
import model.shop.ShopImpl;

public class InGameMenuControllerImpl implements InGameMenuController {

    private ShopImpl shopModel = new ShopImpl();
    private InGameMenuViewImpl view;

    public InGameMenuControllerImpl(final InGameMenuViewImpl view) {
        this.view = view;
    }

    public void buyItem(Items itemSelected) {
        if(shopModel.checkItem(itemSelected)) {
            //AGGIUNGE L' ITEM AL PERSONAGGIO
            this.view.returnMessage(shopModel.getMessageOuput());
        }

    }


    public void openShop() {
        this.view.showShop();

    }


    public void openInGameMenu() {
        this.view.showInGameMenu();

    }


    public void exit() {
       this.view.hide();

    }
}
