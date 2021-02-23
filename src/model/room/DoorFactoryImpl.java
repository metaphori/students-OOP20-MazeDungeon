package model.room;

import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.gameobject.simpleobject.Door;

public class DoorFactoryImpl implements DoorFactory {

    private final IdIterator idIterator;

    public DoorFactoryImpl(final IdIterator idIterator) {
        this.idIterator = idIterator;
    }

    /**
     * 
     */
    @Override
    public Door createLeftDoor(final Room room) {
        return new Door(idIterator.next(), new Point2D(110, 360), GameObjectType.DOOR_LEFT, room);
    }

    /**
     * 
     */
    @Override
    public Door createRightDoor(final Room room) {
        return new Door(idIterator.next(), new Point2D(1010, 360), GameObjectType.DOOR_RIGHT, room);
    }

    /**
     * 
     */
    @Override
    public Door createUpDoor(final Room room) {
        return new Door(idIterator.next(), new Point2D(590, 40), GameObjectType.DOOR_UP, room);
    }

    /**
     * 
     */
    @Override
    public Door createDownDoor(final Room room) {
        return new Door(idIterator.next(), new Point2D(590, 615), GameObjectType.DOOR_DOWN, room);
    }

}
