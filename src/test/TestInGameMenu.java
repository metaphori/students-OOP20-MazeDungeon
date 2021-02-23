package test;

import java.util.concurrent.TimeUnit;

import gamestructure.ingamemenu.InGameMenuView;
import gamestructure.ingamemenu.InGameMenuViewImpl;


public class TestInGameMenu {

    public static void main(final String[] args){
        InGameMenuView view = new InGameMenuViewImpl();
        //view.showInGameMenu();
        view.show();

    }

}
