package gamestructure.ingamemenu;

import gamestructure.game.GameController;
import model.shop.Items;
import model.shop.Shop;
import mvc.Model;

public class InGameMenuControllerImpl implements InGameMenuController {
    private final GameController gameController;
    private final Shop shopModel;
    private final Model model;
    private final InGameMenuView view = new InGameMenuViewImpl(this);
    private boolean menuIsOpen;

    public InGameMenuControllerImpl(final GameController gameController, final Model model) {
        this.gameController = gameController;
        this.shopModel = model.getShop();
        this.model = model;
    }

    /**
     * 
     */
    @Override
    public void setup() {
        this.view.show();
    }

    /**
     * Set new message and check the item.
    * @param itemSelected 
    */
    public void buyItem(final Items itemSelected) {
        this.view.removeMessage();
        shopModel.checkItem(itemSelected);
        this.view.returnMessage(shopModel.getMessageOuput());
    }
    /**
     * open the shop view.
     */
    public void openShop() {
        this.view.setPriceItem(shopModel.addPrice());
        this.view.showShop();
    }
    /**
     * open the in game menu view.
     */
    public void openInGameMenu() {
        if (!this.menuIsOpen && this.model.getRoomManager().getCurrentRoom().isDoorOpen() 
                && !this.model.getRoomManager().getCharacter().isDead()) {
            this.view.removeMessage();
            this.view.showInGameMenu();
            this.menuIsOpen = true;
        }
    }
    /**
     * close the game.
     */
    public void exit() {
       System.exit(0);
    }
    /**
     * resume the game and closes the menu in game.
     */
    public void resume() {
        this.view.hide();
        this.menuIsOpen = false;
        this.gameController.notifyClosedInGameMenu();
    }
}
