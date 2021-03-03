package model.gameobject.simpleobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.Room;

public class Wall extends SimpleObjectImpl {

    public Wall(final Point2D position) {
        super(position, GameObjectType.INVISIBLE_OBJECT);
    }

    @Override
    public void collideWith(final GameObject obj2) {
        // TODO Auto-generated method stub
    }

}
