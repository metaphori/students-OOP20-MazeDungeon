package gamestructure.game;

import java.util.Map;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Sprite;
import mvc.View;

public interface GameView extends View {
    void render();

    void setController(GameController controller);

    void addSprite(Integer id, GameObjectType gameObjectType, Point2D position);

    Map<Integer, Sprite> getSprites();
}
