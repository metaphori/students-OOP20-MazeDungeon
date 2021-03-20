package gamestructure.ingamemenu;

import java.util.Map;

import gamestructure.View;
import model.shop.Items;

public interface InGameMenuView extends View {

    void showShop();

    void showInGameMenu();

    void returnMessage(String messageOuput);

    void removeMessage();

    void setPriceItem(Map<Items, Integer> map);
}
