package model.gameobject.simpleobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;

public class Coin extends AbstractSimpleObject {

    public Coin(final Point2D position) {
        super(position, GameObjectType.COIN);
    }

    /**
     * it doesn't interact with anything at the moment.
     */
    @Override
    public void collideWith(final GameObject obj2) {
    }

}
