package model.gameobject.dinamicobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;

public class Coin extends AbstractDinamicObject {

    public Coin(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType) {
        super(id, speed, position, direction, gameObjectType);
    }

    /**
     * 
     */
    @Override
    public void updateState(final double elapsed) {
        this.move(elapsed);
    }

}
