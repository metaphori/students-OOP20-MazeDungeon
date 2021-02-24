package model.gameobject.simpleobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.Room;

public class Wall extends SimpleObjectImpl {

    public Wall(final Point2D position, final GameObjectType gameObjectType) {
        super(position, gameObjectType);
    }

    @Override
    public void collideWith(final GameObject obj2) {
        // TODO Auto-generated method stub
    }

}
