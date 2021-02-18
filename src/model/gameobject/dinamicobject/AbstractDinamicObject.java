package model.gameobject.dinamicobject;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.simpleobject.SimpleObjectImpl;

public abstract class AbstractDinamicObject extends SimpleObjectImpl implements DinamicObject {
    private Vector2D direction;
    private int speed;

    public AbstractDinamicObject(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType) {
        super(id, position, gameObjectType);
        this.speed = speed;
        this.direction = direction;
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
     * 
     */
    private void setBoundingBoxPosition(final Point2D position) {
        this.getBoundingBox().move(position);
    }

    @Override
    public abstract void updateState(double elapsed);

    @Override
    public abstract void move(double elapsed);
}
