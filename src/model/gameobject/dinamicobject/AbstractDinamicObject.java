package model.gameobject.dinamicobject;

import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.simpleobject.SimpleObjectImpl;

public abstract class AbstractDinamicObject extends SimpleObjectImpl implements DinamicObject {
    private int id;
    private int speed;
    private Point2D position;
    private Vector2D direction;


    public AbstractDinamicObject(int id, int speed, Point2D position, Vector2D direction) {
        this.id = id;
        this.speed = speed;
        this.position = position;
        this.direction = direction;
    }

    public int getID() {
        return this.id;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public Vector2D getDirection() {
        return this.direction;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public void setDirection(final Vector2D direction) {
        this.direction = direction;
    }

    public void setPosition(final Point2D position) {
        this.position = position;
    }

    public abstract void updateState();

    public abstract void move();
}
