package model.gameobject.simpleobject.obstacle;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.simpleobject.AbstractSimpleObject;

/**
 * The wall is used to set the edges of the room.
 */
public class Wall extends AbstractSimpleObject {

    private final CardinalPoint cardinalPoint;
    private final WallType wallType;

    /**
     * @param position : position of the wall in the room
     * @param cardinalPoint : cardinal point of the wall in the room
     * @param wallType : indicate if the wall is solid or perspective.
     */
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
     * Collision with walls are managed from other gameObjects.
     */
    @Override
    public void collideWith(final GameObject obj2) {
    }

}
