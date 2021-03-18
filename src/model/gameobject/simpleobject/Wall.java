package model.gameobject.simpleobject;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;

public class Wall extends SimpleObjectImpl {

    private final CardinalPoint cardinalPoint;

    public Wall(final Point2D position, final CardinalPoint cardinalPoint) {
        super(position, GameObjectType.INVISIBLE_OBJECT);
        this.cardinalPoint = cardinalPoint;
    }

    /**
     * @return
     */
    public CardinalPoint getCardinalPoint() {
        return cardinalPoint;
    }

    /**
     * it doesn't interact with anything at the moment.
     */
    @Override
    public void collideWith(final GameObject obj2) {
    }

}
