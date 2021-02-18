package test;

import java.util.concurrent.TimeUnit;

import gamestructure.ingamemenu.InGameMenuView;
import gamestructure.ingamemenu.InGameMenuViewImpl;
import gamestructure.shop.ShopView;
import gamestructure.shop.ShopViewImpl;

public class TestInGameMenu {

    public static void main(final String[] args){
        InGameMenuViewImpl view = new InGameMenuViewImpl();
        //view.show();
       
        try {
            while(true) {
                view.showInGameMenu();
                TimeUnit.SECONDS.sleep(3);
                view.showShop();
                TimeUnit.SECONDS.sleep(3);
            }
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }

}
