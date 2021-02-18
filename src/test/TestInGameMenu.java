package test;

import gamestructure.ingamemenu.InGameMenuView;
import gamestructure.ingamemenu.InGameMenuViewImpl;
import gamestructure.shop.ShopView;
import gamestructure.shop.ShopViewImpl;

public class TestInGameMenu {

    public static void main(final String[] args){
        InGameMenuViewImpl view = new InGameMenuViewImpl();
        view.show();
        view.showShop();
    }

}
