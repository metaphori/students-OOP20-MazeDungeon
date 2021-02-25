package gamestructure.ingamemenu;

import gamestructure.game.GameController;
import input.Command;
import model.ModelImpl;
import model.shop.Item;
import model.shop.Items;
import model.shop.Shop;
import model.shop.ShopImpl;

public class InGameMenuControllerImpl implements InGameMenuController {

<<<<<<< HEAD
    private Shop shopModel = new ShopImpl(15,3);
    private InGameMenuView view = new InGameMenuViewImpl(this);
    private ModelImpl model = new ModelImpl();
    private GameController gameController;
    public InGameMenuControllerImpl(GameController gameController) {
        this.gameController = gameController;
        this.view.show();
=======
    private final Shop shopModel = new ShopImpl(15, 3);
    private final InGameMenuView view;
    private final ModelImpl model = new ModelImpl();
    public InGameMenuControllerImpl(final InGameMenuView view) {
        this.view = view;
>>>>>>> a94d0fecd81b77d64b13749a067b604e78313bf7
    }

    @Override
    public void buyItem(final Items itemSelected) {
        this.view.removeMessage();
        int actualMoney = model.getRoomManager().getCurrentRoom().getCharacter().get().getMoney();
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
