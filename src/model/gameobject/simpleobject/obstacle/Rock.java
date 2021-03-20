package model.gameobject.simpleobject.obstacle;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.simpleobject.SimpleObjectImpl;

public class Rock extends SimpleObjectImpl {

    public Rock(final Point2D position) {
        super(position, GameObjectType.ROCK);
    }

    @Override
    public void collideWith(final GameObject obj2) {
    }

}
