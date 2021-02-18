package gamestructure.game;

import model.common.GameObjectType;
import mvc.View;

public interface GameView extends View {
    void render();

    void setController(GameController controller);

    void addSprite(Integer id, GameObjectType gameObjectType);
}
