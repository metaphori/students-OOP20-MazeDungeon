package gamestructure.ingamemenu;

import gamestructure.game.GameController;
import input.Command;
import model.ModelImpl;
import model.shop.Item;
import model.shop.Items;
import model.shop.Shop;
import model.shop.ShopImpl;

public class InGameMenuControllerImpl implements InGameMenuController {

    private Shop shopModel = new ShopImpl(15,3);
    private InGameMenuView view = new InGameMenuViewImpl(this);
    private ModelImpl model = new ModelImpl();
    private GameController gameController;

    public InGameMenuControllerImpl(final GameController gameController) {
        this.gameController = gameController;
        this.view.show();
    }

    @Override
    public void buyItem(final Items itemSelected) {
        this.view.removeMessage();
        final int actualMoney = model.getRoomManager().getCurrentRoom().getCharacter().get().getMoney();
        if (shopModel.checkItem(itemSelected, actualMoney)) {
            model.getRoomManager().getCurrentRoom().getCharacter().get().addItem(itemSelected);
        }
        this.view.returnMessage(shopModel.getMessageOuput());
    }


    @Override
    public void openShop() {
        this.view.showShop();

    }


    @Override
    public void openInGameMenu() {
        this.view.removeMessage();
        this.view.showInGameMenu();

    }


    @Override
    public void exit() {
       this.view.hide();
       this.gameController.notifyClosedMenu();
    }
}
