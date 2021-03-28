package model.gameobject.simpleobject;

import animations.State;
import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.Room;

/**
 * An abstract class representing a SimpleObject.
 * 
 * A SimpleObject has an ID for it's identification, a BoundingBox for manage it's collisions with 
 * other GameObjects, a State, a Room which is placed in at specific Position.
 * 
 * The method for manage collisions is to be implemented in base of the type of the SimpleObject that need it.
 *
 */
public abstract class SimpleObjectImpl implements SimpleObject {
    private int id;
    private Point2D position;
    private final GameObjectType gameObjectType;
    private BoundingBox boundingBox;
    private Room room;
    private State state = State.IDLE;

    /**
     * Build a new SimpleObject, by it's type and it's position in the room.
     * 
     * @param position : the position where the SimpleObject will be placed.
     * @param gameObjectType : the type of the SimpleObject will be built.
     */
    public SimpleObjectImpl(final Point2D position, final GameObjectType gameObjectType) {
        this.position = position;
        this.gameObjectType = gameObjectType;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public int getID() {
        return this.id;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void setID(final int id) {
        this.id = id;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * @param position : set position as the current position of the GameObject.
     */
    protected void setPosition(final Point2D position) {
        this.position = position;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public GameObjectType getGameObjectType() {
        return this.gameObjectType;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public State getState() {
        return this.state;
    }

    /**
     * @param state : set state as the current state of the GameObject.
     */
    protected void setState(final State state) {
        this.state = state;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void setBoundingBox(final BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * @return the room where the GameObject is placed in.
     */
    protected Room getRoom() {
        return this.room;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void setRoom(final Room room) {
        this.room = room;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public abstract void collideWith(GameObject obj2);
}
