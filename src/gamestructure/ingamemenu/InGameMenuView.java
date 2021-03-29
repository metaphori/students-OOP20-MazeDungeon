package gamestructure.ingamemenu;

import java.util.Map;

import gamestructure.View;
import model.shop.Items;
 /**
  * 
  * creates a specific interface for the MenuInGame and for the shop. 
  * This is different from the game view.
  *
  */
public interface InGameMenuView extends View {
    /**
     * show shop panel and set button and label.
     */
    void showShop();
    /**
     * open in game menu panel and set button and label.
     */
    void showInGameMenu();
    /**
     * create and set message label, for output information.
     * @param messageOutput : set message in JLabel
     */
    void returnMessage(String messageOutput);
    /**
     * clear message label.
     */
    void removeMessage();
    /**
     * set output price item.
     * @param map : for set Item price in view
     */
    void setPriceItem(Map<Items, Integer> map);
}
