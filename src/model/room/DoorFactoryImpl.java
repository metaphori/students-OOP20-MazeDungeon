package model.room;

import model.common.Direction;
import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.gameobject.simpleobject.Door;

public class DoorFactoryImpl implements DoorFactory {

    /**
     * 
     */
    @Override
    public Door createLeftDoor() {
        return new Door(new Point2D(110, 360), GameObjectType.DOOR_LEFT);
    }

    /**
     * 
     */
    @Override
    public Door createRightDoor() {
        return new Door(new Point2D(1010, 360), GameObjectType.DOOR_RIGHT);
    }

    /**
     * 
     */
    @Override
    public Door createUpDoor() {
        return new Door(new Point2D(590, 40), GameObjectType.DOOR_UP);
    }

    /**
     * 
     */
    @Override
    public Door createDownDoor() {
        return new Door(new Point2D(590, 615), GameObjectType.DOOR_DOWN);
    }

    /**
     * 
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
