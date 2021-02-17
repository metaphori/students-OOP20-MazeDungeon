package gamestructure.shop;

import model.shop.Items;
import model.shop.ShopImpl;

public class ShopControllerImpl implements ShopController {
    private Items itemSelected;
    public void openShop() {
        ShopImpl shop = new ShopImpl();
        if (shop.buyItem(itemSelected)) {
            System.out.println(shop.getMessageOuput());
        }
        else {
          System.out.println(shop.getMessageOuput());
        }
    }
}
