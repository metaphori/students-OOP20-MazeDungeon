package model.gameobject.dynamicobject;

import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;

/**
 * An interface that define a DynamicObject as a GameObject,
 * with features that allows movement with a direction and a speed.
 *
 */
public interface DynamicObject extends GameObject {

    /**
     * @return the speed of the DynamicObject.
     */
    int getSpeed();

    /**
     * @param speed : set speed value as the speed of the DynamicObject.
     */
    void setSpeed(int speed);

    /**
     * @return the direction of the DynamicObject.
     */
    Vector2D getDirection();

    /**
     * @param newDirection : set direction value as the direction of the DynamicObject.
     */
    void setDirection(Vector2D newDirection);

    /**
     * @param newPosition : set the current position of the DynamicObject to the new position.
     */
    void setPosition(Point2D newPosition);

    /**
     * @return the last position of the DynamicObject.
     */
    Point2D getLastPosition();

    /**
     * Update the state of the DynamicObject considering the time passed from the last update.
     * 
     * @param elapsed : the time passed from the last updateState.
     */
    void update(double elapsed);

}
