package model.shop;

import java.util.Map;
import java.util.Set;

public interface Shop {

    String getMessageOuput();

    void checkItem(Items i);

    Item getArthemideBow();

    /**
     * @return Item with features of HermesBoots
     */
    Item getHermesBoots();

    /**
     * @return Item with features of ZeusBolt
     */
    Item getZeusBolt();

    /**
     * @return Item with features more Health
     */
    Item getHealth();
    /**
     * 
     * @return .
     */
    //int moneyLeft();
    Map<Items, Integer> addPrice();

    void clearCart();

    Set<Items> getCart();
}
