package model.gameobject.dinamicobject;

import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.AbstractGameObject;

public abstract class AbstractDinamicObject extends AbstractGameObject {

    private int speed;

    private Vector2D direction;

    public AbstractDinamicObject(final int id, final Point2D position) {
        super(id, position);
    }

    /**
     * @return speed of the GameObject
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * @return the direction of the game object
     */
    public Vector2D getDirection() {
        return this.direction;
    }

    public abstract void updateState();

    public abstract void move(Vector2D direction);

}
