package model.room;

import model.common.Direction;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.simpleobject.Door;

public class DoorFactoryImpl implements DoorFactory {

    private static final Point2D LEFT_DOOR_POSITION = new Point2D(110, 360);
    private static final Point2D UP_DOOR_POSITION = new Point2D(590, 40);
    private static final Point2D RIGHT_DOOR_POSITION = new Point2D(1010, 360);
    private static final Point2D DOWN_DOOR_POSITION = new Point2D(590, 615);

    /**
     * @return the left door
     */
    @Override
    public Door createLeftDoor() {
        return new Door(LEFT_DOOR_POSITION, GameObjectType.DOOR_LEFT);
    }

    /**
     * @return the right door
     */
    @Override
    public Door createRightDoor() {
        return new Door(RIGHT_DOOR_POSITION, GameObjectType.DOOR_RIGHT);
    }

    /**
     * @return the up door
     */
    @Override
    public Door createUpDoor() {
        return new Door(UP_DOOR_POSITION, GameObjectType.DOOR_UP);
    }

    /**
     * @return the down door
     */
    @Override
    public Door createDownDoor() {
        return new Door(DOWN_DOOR_POSITION, GameObjectType.DOOR_DOWN);
    }

    /**
     * @param direction the direction of the door
     * @return the door in the direction of the parameter
     */
    @Override
    public Door createDoor(final Direction direction) {
        switch (direction) {
        case UP:
            return this.createUpDoor();
        case DOWN:
            return this.createDownDoor();
        case LEFT:
            return this.createLeftDoor();
        case RIGHT:
            return this.createRightDoor();
        default:
            throw new IllegalStateException("not valid direction");
        }
    }

}
