package gamestructure.game;

import model.common.GameObjectType;
import model.common.Point2D;
import mvc.View;

public interface GameView extends View {
    void render();

    void setController(GameController controller);

    void addSprite(Integer id, GameObjectType gameObjectType, Point2D position);

    void setSpritePosition(int id, Point2D position);

    void removeSprite(int id);

    void syncronizeMap();
}
