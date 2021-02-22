package gamestructure.ingamemenu;

public interface InGameMenuView {

    void showShop();

    void showInGameMenu();

    void show();

    /**
     * @Override
     */
    void hide();

    void returnMessage(String messageOuput);

}