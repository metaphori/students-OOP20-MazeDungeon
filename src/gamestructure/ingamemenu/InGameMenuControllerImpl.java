package gamestructure.ingamemenu;

import model.ModelImpl;
import model.shop.Items;
import model.shop.Shop;
import model.shop.ShopImpl;

public class InGameMenuControllerImpl implements InGameMenuController {

    private Shop shopModel = new ShopImpl(15,3);
    private InGameMenuView view;
    private ModelImpl model = new ModelImpl();
    public InGameMenuControllerImpl(final InGameMenuView view) {
        this.view = view;
    }

    @Override
    public void buyItem(Items itemSelected) {
        if(shopModel.checkItem(itemSelected)) {
            //AGGIUNGE L' ITEM AL PERSONAGGIO
           // model.getRoomManager().getCurrentRoom().getCharacter().get().addItem(Items.);
        }
        this.view.returnMessage(shopModel.getMessageOuput());
        //System.out.println(shopModel.getMessageOuput());
    }


    @Override
    public void openShop() {
        this.view.showShop();

    }


    @Override
    public void openInGameMenu() {
        this.view.showInGameMenu();

    }


    @Override
    public void exit() {
       this.view.hide();

    }
}
