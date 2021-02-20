package model.gameobject.dinamicobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.room.Room;

public class Coin extends AbstractDinamicObject {

    public Coin(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final Room room) {
        super(id, speed, position, direction, gameObjectType, room);
    }

    /**
     * 
     */
    @Override
    public void updateState(final double elapsed) {
        this.move(elapsed);
    }

    @Override
    public void collideWith(final GameObject obj2) {
        //this.setDirection(this.getDirection().mul(-1));
        this.getRoom().deleteGameObject(obj2);
    }

}
