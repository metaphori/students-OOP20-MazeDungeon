package gamestructure.ingamemenu;

import java.util.Map;

import model.shop.Items;
import mvc.View;

public interface InGameMenuView extends View {

    void showShop();

    void showInGameMenu();

    void returnMessage(String messageOuput);

    void removeMessage();

    void setPriceItem(Map<Items, Integer> map);
}
