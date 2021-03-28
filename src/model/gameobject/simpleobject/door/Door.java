package model.gameobject.simpleobject.door;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.simpleobject.AbstractSimpleObject;

public class Door extends AbstractSimpleObject {

    public Door(final Point2D position, final GameObjectType gameObjectType) {
        super(position, gameObjectType);
    }

    /**
     * it doesn't interact with anything at the moment.
     */
    @Override
    public void collideWith(final GameObject obj2) {
        if (!this.getRoom().isDoorOpen()) {
            return;
        }
        switch (obj2.getGameObjectType()) {
        case CHARACTER:
            final CardinalPoint direction;
            switch (this.getGameObjectType()) {
            case DOOR_SOUTH:
                direction = CardinalPoint.SOUTH;
                break;
            case DOOR_NORTH:
                direction = CardinalPoint.NORTH;
                break;
            case DOOR_WEST:
                direction = CardinalPoint.WEST;
                break;
            case DOOR_EAST:
                direction = CardinalPoint.EAST;
                break;
            default:
                return;
            }
            this.getRoom().getRoomManager().changeRoom(direction);
            break;
        default:
            break;
        }
    }
}
