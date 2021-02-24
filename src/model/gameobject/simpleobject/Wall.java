package model.gameobject.simpleobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.Room;

public class Wall extends SimpleObjectImpl {

    public Wall(int id, Point2D position, GameObjectType gameObjectType, Room room) {
        super(id, position, gameObjectType, room);
    }

    @Override
    public void collideWith(final GameObject obj2) {
        // TODO Auto-generated method stub
    }

}
