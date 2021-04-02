package model.gameobject.simpleobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;

/**
 * SimpleObject that represent a coin. if character walk on coin,
 * the amount of coins of the character increase and the coin disappear. 
 */
public class Coin extends AbstractSimpleObject {

    /**
     * 
     * @param position : position of the coin
     */
    public Coin(final Point2D position) {
        super(position, GameObjectType.COIN);
    }

    /**
     * it doesn't interact with anything at the moment.
     * Collision with coins are managed only from the character at the moment.
     */
    @Override
    public void collideWith(final GameObject obj2) {
    }

}
