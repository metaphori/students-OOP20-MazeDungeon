package test;

import java.util.concurrent.TimeUnit;

import gamestructure.ingamemenu.InGameMenuView;
import gamestructure.ingamemenu.InGameMenuViewImpl;


public class TestInGameMenu {

    public static void main(final String[] args){
        InGameMenuViewImpl view = new InGameMenuViewImpl();
        //view.show();
        view.showInGameMenu();
        view.showShop();
       /* try {
            while(true) {
                view.showInGameMenu();
                TimeUnit.SECONDS.sleep(3);
                view.showShop();
                TimeUnit.SECONDS.sleep(3);
            }
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
       
    }

}
