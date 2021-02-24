package model.gameobject.simpleobject;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.Room;

public abstract class SimpleObjectImpl implements SimpleObject {
    private int id;
    private Point2D position;
    private final GameObjectType gameObjectType;
    private BoundingBox boundingBox;
    private Room room;

    public SimpleObjectImpl(final Point2D position, final GameObjectType gameObjectType, final Room room) {
        this.position = position;
        this.room = room;
        this.gameObjectType = gameObjectType;
    }

    /**
     * @return the ID of the object
     */
    @Override
    public int getID() {
        return this.id;
    }

    /**
     * 
     */
    @Override
    public void setID(final int id) {
        this.id = id;
    }

    /**
     * @return the position of the object
     */
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * 
     * @param position
     */
    protected void setPosition(final Point2D position) {
        this.position = position;
    }

    /**
     * 
     */
    @Override
    public GameObjectType getGameObjectType() {
        return this.gameObjectType;
    }

    /**
     * 
     */
    @Override
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /**
     * 
     */
    @Override
    public void setBoundingBox(final BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * 
     * @return .
     */
    protected Room getRoom() {
        return this.room;
    }

    /**
     * 
     */
    @Override
    public void setRoom(final Room room) {
        this.room = room;
    }

    @Override
    public abstract void collideWith(GameObject obj2);
}
