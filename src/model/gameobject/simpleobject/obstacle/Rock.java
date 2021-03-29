package model.gameobject.simpleobject.obstacle;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.simpleobject.AbstractSimpleObject;

public class Rock extends AbstractSimpleObject {

    public Rock(final Point2D position) {
        super(position, GameObjectType.ROCK);
    }

    @Override
    public void collideWith(final GameObject obj2) {
    }

}
