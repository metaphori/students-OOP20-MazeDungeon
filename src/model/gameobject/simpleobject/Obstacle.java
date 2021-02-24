package model.gameobject.simpleobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.Room;

public class Obstacle extends SimpleObjectImpl {

    public Obstacle(final int id, final Point2D position, final GameObjectType gameObjectType, final Room room) {
        super(id, position, gameObjectType, room);
    }

    @Override
    public void collideWith(final GameObject obj2) {
    }

}
