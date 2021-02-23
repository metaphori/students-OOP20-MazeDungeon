package model.gameobject.simpleobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.room.Room;

public class Door extends SimpleObjectImpl {
    public Door(final int id, final Point2D position, final GameObjectType gameObjectType, final Room room) {
        super(id, position, gameObjectType, room);
    }

    private boolean isOpen = false;

    /**
     * 
     */
    public void open() {
        this.isOpen = true;
    }

    /**
     * 
     * @return true if the door is open, false if not
     */
    public boolean getIsOpen() {
        return this.isOpen;
    }
}
