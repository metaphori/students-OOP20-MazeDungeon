package model.room;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.simpleobject.Door;

public class DoorFactoryImpl implements DoorFactory {

    private static final Point2D WEST_DOOR_POSITION = new Point2D(110, 360);
    private static final Point2D NORTH_DOOR_POSITION = new Point2D(590, 40);
    private static final Point2D EAST_DOOR_POSITION = new Point2D(1010, 360);
    private static final Point2D SOUTH_DOOR_POSITION = new Point2D(590, 615);

    /**
     * @return the left door
     */
    @Override
    public Door createWestDoor() {
        return new Door(WEST_DOOR_POSITION, GameObjectType.DOOR_WEST);
    }

    /**
     * @return the right door
     */
    @Override
    public Door createEastDoor() {
        return new Door(EAST_DOOR_POSITION, GameObjectType.DOOR_EAST);
    }

    /**
     * @return the up door
     */
    @Override
    public Door createNorthDoor() {
        return new Door(NORTH_DOOR_POSITION, GameObjectType.DOOR_TOP);
    }

    /**
     * @return the down door
     */
    @Override
    public Door createSouthDoor() {
        return new Door(SOUTH_DOOR_POSITION, GameObjectType.DOOR_SOUTH);
    }

    /**
     * @param cardinalPoint the cardinal point of the door
     * @return the door in the cardinal point of the parameter
     */
    @Override
    public Door createDoor(final CardinalPoint cardinalPoint) {
        switch (cardinalPoint) {
        case NORTH:
            return this.createNorthDoor();
        case SOUTH:
            return this.createSouthDoor();
        case WEST:
            return this.createWestDoor();
        case EAST:
            return this.createEastDoor();
        default:
            throw new IllegalStateException("not valid cardinal point");
        }
    }

}
