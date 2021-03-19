package model.gameobject.simpleobject;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;

public class Wall extends SimpleObjectImpl {

    private final CardinalPoint cardinalPoint;
    private final WallType wallType;

    public Wall(final Point2D position, final CardinalPoint cardinalPoint, final WallType wallType) {
        super(position, GameObjectType.INVISIBLE_OBJECT);
        this.cardinalPoint = cardinalPoint;
        this.wallType = wallType;
    }

    /**
     * @return the cardinal point of the wall
     */
    public CardinalPoint getCardinalPoint() {
        return cardinalPoint;
    }

    /**
     * @return true if the wall is perspective
     */
    public boolean isPerspective() {
        return wallType == WallType.PERSPECTIVE;
    }

    /**
     * it doesn't interact with anything at the moment.
     */
    @Override
    public void collideWith(final GameObject obj2) {
    }

    /**
     * 
     */
    public enum WallType { SOLID, PERSPECTIVE };

}
