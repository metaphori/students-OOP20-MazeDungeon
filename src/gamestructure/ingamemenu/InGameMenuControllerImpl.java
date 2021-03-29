package gamestructure.ingamemenu;

import gamestructure.game.GameController;
import model.Model;
import model.shop.Items;
import model.shop.Shop;

/**
 * It contains the implementation of the methods of a generic Controller,
 * and the methods defined by InGameMenuController's interface.
 */
public class InGameMenuControllerImpl implements InGameMenuController {

    private final GameController gameController;
    private final Shop shopModel;
    private final Model model;
    private final InGameMenuView view = new InGameMenuViewImpl(this);
    private boolean menuIsOpen;
    private boolean shopIsOpen;

    /**
     * Instantiate a new InGameMenuController initializing also the corresponding InGameMenuView.
     * @param gameController
     * @param model
     */
    public InGameMenuControllerImpl(final GameController gameController, final Model model) {
        this.gameController = gameController;
        this.shopModel = model.getShop();
        this.model = model;
    }
    /**
     * @{inheritDoc}
     */
    @Override
    public void setup() {
        this.view.show();
    }
    /**
     * @{inheritDoc}
     * @param itemSelected 
     */
    public void buyItem(final Items itemSelected) {
        this.view.removeMessage();
        shopModel.checkItem(itemSelected);
        this.view.returnMessage(shopModel.getMessageOuput());
    }
    /**
     * @{inheritDoc}
     */
    public void openShop() {
        shopIsOpen = true;
        this.view.setPriceItem(shopModel.addPrice());
        this.view.showShop();
    }
    /**
     * @{inheritDoc}
     * @return boolean : true if the in game menu opened
     */
    public boolean openInGameMenu() {
        if (!this.menuIsOpen && this.model.getRoomManager().getCurrentRoom().isDoorOpen() 
                && !this.model.getRoomManager().getCharacter().isDead() || shopIsOpen) {
            shopIsOpen = false;
            this.view.removeMessage();
            this.view.showInGameMenu();
            this.menuIsOpen = true;
            return true;
        }
        return false;
    }
    /**
     * @{inheritDoc}
     */
    public void exit() {
       System.exit(0);
    }
    /**
     * @{inheritDoc}
     */
    public void resume() {
        this.view.hide();
        this.menuIsOpen = false;
        this.gameController.notifyClosedInGameMenu();
    }
}
