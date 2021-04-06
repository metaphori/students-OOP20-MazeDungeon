package model.gameobject.simpleobject.door;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.simpleobject.AbstractSimpleObject;

/**
 * the door permit to the character to move between rooms.
 */
public class Door extends AbstractSimpleObject {

    private final CardinalPoint cardinalPoint;

    /**
     * @param position : position of the new Door
     * @param gameObjectType : type of gameObject
     * @param cardinalPoint : the cardinal point of the door
     */
    public Door(final Point2D position, final GameObjectType gameObjectType, final CardinalPoint cardinalPoint) {
        super(position, gameObjectType);
        this.cardinalPoint = cardinalPoint;
    }

    /**
     * @return the cardinal point of the door
     */
    public CardinalPoint getCardinalPoint() {
        return this.cardinalPoint;
    }

    /**
     * when a door collide with the character and the door is open, 
     * the new room is loaded. In other cases nothing happens.
     */
    @Override
    public void collideWith(final GameObject obj2) {
        if (!this.getRoom().isDoorOpen()) {
            return;
        }
        switch (obj2.getGameObjectType()) {
        case MAINCHARACTER:
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
