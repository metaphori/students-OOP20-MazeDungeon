package model.shop;

import java.util.Map;
import java.util.Set;

public interface Shop {

    String getMessageOuput();

    void checkItem(Items i);

    Item getArthemideBow();

    Item getHermesBoots();

    Item getZeusBolt();

    Item getHealth();

    Item getOracleAmulet();

    Map<Items, Integer> addPrice();

    void clearCart();

    Set<Items> getCart();
}
