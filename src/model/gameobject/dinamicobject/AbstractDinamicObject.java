package model.gameobject.dinamicobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.simpleobject.SimpleObjectImpl;
import model.room.Room;

public abstract class AbstractDinamicObject extends SimpleObjectImpl implements DinamicObject {
    private Vector2D direction;
    private int speed;
    private Point2D lastPosition;

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
     * @param direction : set direction value as the direction of the DinamicObject
     */
    @Override
    public void setDirection(final Vector2D direction) {
        this.direction = direction;
    }

    /**
     * @param position
     */
    public void setPosition(final Point2D position) {
        this.lastPosition = this.getPosition();
        super.setPosition(position);
        this.updateBoundingBoxPosition();
    }

    /**
     * 
     */
    private void updateBoundingBoxPosition() {
        if (this.getBoundingBox() != null) {
            this.getBoundingBox().move(this.getPosition());
        }
    }

    /**
     * @param elapsed
     */
    protected void move(final double elapsed) {
        this.setPosition(this.getPosition().sum(this.getDirection().mul(speed).mul(elapsed)));
    }

    /**
     * 
     * @return last position of the object
     */
    public Point2D getLastPosition() {
        return this.lastPosition;
    }

    @Override
    public abstract void updateState(double elapsed);

}
