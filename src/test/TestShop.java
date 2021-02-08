package test;

import java.io.IOException;

import gamestructure.shop.ShopView;
import gamestructure.shop.ShopViewImpl;

public class TestShop {

    public static void main(final String[] args){
        ShopView view = new ShopViewImpl();
        view.show();
    }

}
