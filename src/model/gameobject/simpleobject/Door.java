package model.gameobject.simpleobject;

import model.common.Direction;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.Room;

public class Door extends SimpleObjectImpl {

    public Door(final Point2D position, final GameObjectType gameObjectType) {
        super(position, gameObjectType);
    }

    /**
     * 
     */
    @Override
    public void collideWith(final GameObject obj2) {
        if (!this.getRoom().isDoorOpen()) {
            return;
        }
        switch (obj2.getGameObjectType()) {
        case CHARACTER:
            final Direction direction;
            switch (this.getGameObjectType()) {
            case DOOR_DOWN:
                direction = Direction.DOWN;
                break;
            case DOOR_UP:
                direction = Direction.UP;
                break;
            case DOOR_LEFT:
                direction = Direction.LEFT;
                break;
            case DOOR_RIGHT:
                direction = Direction.RIGHT;
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
