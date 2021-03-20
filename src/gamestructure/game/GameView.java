package gamestructure.game;

import java.util.Set;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.animations.State;
import model.shop.Items;
import mvc.View;

public interface GameView extends View {
    void updateHUD();

    void setController(GameController controller);

    void addAnimation(Integer id, GameObjectType gameObjectType, Point2D position);

    void updateAnimation(int id, Point2D position, State state);

    void removeAnimation(int id);

    void initialize();

    void gameOver();

    void renderItems(Set<Items> purchasedItems);

    void isWon();
}
