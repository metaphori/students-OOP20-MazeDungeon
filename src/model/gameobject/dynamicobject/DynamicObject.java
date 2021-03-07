package model.gameobject.dynamicobject;

import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;

public interface DynamicObject extends GameObject {
    int getSpeed();

    void setSpeed(int speed);

    Vector2D getDirection();

    void setDirection(Vector2D newDirection);

    void setPosition(Point2D newPosition);

    void updateState(double elapsed);

    void collideWith(GameObject obj2);
}
