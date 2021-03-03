package model.gameobject.simpleobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.room.Room;

public class Coin extends SimpleObjectImpl {

    public Coin(final Point2D position) {
        super(position, GameObjectType.COIN);
    }

    /**
     * 
     */
    @Override
    public void collideWith(final GameObject obj2) {
    }

}
