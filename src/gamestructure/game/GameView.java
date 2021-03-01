package gamestructure.game;

import java.util.List;
import java.util.Set;

import model.common.GameObjectType;
import model.common.Point2D;
import model.shop.Items;
import mvc.View;

public interface GameView extends View {
    void render();

    void setController(GameController controller);

    void addSprite(Integer id, GameObjectType gameObjectType, Point2D position);

    void setSpritePosition(int id, Point2D position);

    void removeSprite(int id);

    void initialize();

    void gameOver();

    void renderItems(Set<Items> purchasedItems);
}
