package model.gameobject.dinamicobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.simpleobject.SimpleObjectImpl;

public abstract class AbstractDinamicObject extends SimpleObjectImpl implements DinamicObject {
    private Vector2D direction;
    private int speed;
    private Point2D lastPosition;

    /**
     * @param speed : the initial speed of the DinamicObject
     * @param position : the initial position of the DinamicObject
     * @param gameObjectType : the type of the DinamicObject
     */
    public AbstractDinamicObject(final int speed, final Point2D position, final GameObjectType gameObjectType) {
        super(position, gameObjectType);
        this.speed = speed;
        this.lastPosition = this.getPosition();
        this.direction = new Vector2D(0, 0);
    }

    /**
     * @return the speed of the DinamicObject
     */
    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * @param speed : set speed value as the speed of the DinamicObject
     */
    @Override
    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    /**
     * @return the direction of the DinamicObject
     */
    public Vector2D getDirection() {
        return this.direction;
    }

    /**
     * @param newDirection : set direction value as the direction of the DinamicObject
     */
    @Override
    public void setDirection(final Vector2D newDirection) {
        this.direction = newDirection;
    }

    /**
     * @param newPosition : set the current position of the DInamicObject to the new position
     */
    public void setPosition(final Point2D newPosition) {
        this.lastPosition = this.getPosition();
        super.setPosition(newPosition);
        this.updateBoundingBoxPosition();
    }

    /**
     * Move the bounding box, following the DinamicObject movement.
     */
    private void updateBoundingBoxPosition() {
        if (this.getBoundingBox() != null) {
            this.getBoundingBox().move(this.getPosition());
        }
    }

    /**
     * @return last position of the object
     */
    public Point2D getLastPosition() {
        return this.lastPosition;
    }

    /**
     * @param elapsed : the time passed from the last movement
     */
    protected void move(final double elapsed) {
        this.setPosition(this.getPosition().sum(this.getDirection().mul(speed).mul(elapsed)));
    }

    @Override
    public abstract void updateState(double elapsed);
}
