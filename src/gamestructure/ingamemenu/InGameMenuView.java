package gamestructure.ingamemenu;

import mvc.View;

public interface InGameMenuView extends View{

    void showShop();

    void showInGameMenu();

    /**
     * @Override
     */
    void returnMessage(String messageOuput);

    void removeMessage();
}
