package gamestructure.ingamemenu;

import gamestructure.game.GameController;
import input.Command;
import model.ModelImpl;
import model.shop.Item;
import model.shop.Items;
import model.shop.Shop;
import model.shop.ShopImpl;
import mvc.Model;

public class InGameMenuControllerImpl implements InGameMenuController {
    private final Model model;
    private final GameController gameController;

    private final Shop shopModel;
    private final InGameMenuView view = new InGameMenuViewImpl(this);
   // private final ModelImpl model = new ModelImpl();
    private final double actualLife;
    private final int actualMoney;
    private final double maxLife;
    
    public InGameMenuControllerImpl(final GameController gameController, final Model model) {
        this.gameController = gameController;
        this.view.show();
        this.model = model; 
        this.actualLife = model.getRoomManager().getCurrentRoom().getCharacter().get().getLife();
        this.actualMoney = model.getRoomManager().getCurrentRoom().getCharacter().get().getMoney();
        this.maxLife = model.getRoomManager().getCurrentRoom().getCharacter().get().getMaxLife();
        this.shopModel = new ShopImpl(this.maxLife, this.actualLife, this.actualMoney);
    }
    /**
     *
     */
    public void buyItem(final Items itemSelected) {
        this.view.removeMessage();
        //final int actualMoney = model.getRoomManager().getCurrentRoom().getCharacter().get().getMoney();
        //System.out.println(actualMoney);
        if (shopModel.checkItem(itemSelected)) {
            model.getRoomManager().getCurrentRoom().getCharacter().get().setMoney(this.shopModel.moneyLeft());
            model.getRoomManager().getCurrentRoom().getCharacter().get().addItem(itemSelected);
            //System.out.println(this.shopModel.moneyLeft());
        }
        this.view.returnMessage(shopModel.getMessageOuput());
    }
    /**
     * 
     */
    public void openShop() {
        this.view.showShop();

    }
    /**
     * 
     */
    public void openInGameMenu() {
        this.view.removeMessage();
        this.view.showInGameMenu();

    }
    /**
     * 
     */
    public void exit() {
       System.exit(0);
    }
    /**
     * 
     */
    public void resume() {
        this.view.hide();
        this.gameController.notifyClosedMenu();
    }
}
