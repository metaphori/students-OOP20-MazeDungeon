package model.shop;

import java.util.Map;
import java.util.Set;

public interface Shop {
    /**
     * To show the player messages about the Items.
     * @return string for output message
     */
    String getMessageOuput();
    /**
     * To check if an Item can be purchased.
     * Check if the character has enough money and if he hasn't bought it yet.
     * @param i : item selected, to check if the item is affordable
     */
    void checkItem(Items i);
    /**
     * @return ArthemideBow Item
     */
    Item getArthemideBow();
    /**
     * @return HermesBoots Item
     */
    Item getHermesBoots();
    /**
     * @return ZeusBolt Item
     */
    Item getZeusBolt();
    /**
     * @return Health Item
     */
    Item getHealth();
    /**
     * @return Oracle Amulet Item
     */
    Item getOracleAmulet();
    /** 
     * @return a map: every Item with its price 
     */
    Map<Items, Integer> addPrice();
    /**
     * Empty the character's current cart.
     * empty the current cart.
     */
    void clearCart();
    /**
     * To get the character's shopping cart.
     * @return a copy of current cart
     */
    Set<Items> getCart();
}
