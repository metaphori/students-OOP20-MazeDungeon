package model.gameobject.dinamicobject;

import model.common.Vector2D;
import model.gameobject.simpleobject.SimpleObjectImpl;

public abstract class AbstractDinamicObject extends SimpleObjectImpl implements DinamicObject {
    private Vector2D direction;
    private int speed;

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

    @Override
    public abstract void updateState();

    @Override
    public abstract void move();
}
