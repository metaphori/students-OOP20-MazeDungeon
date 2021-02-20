package model.gameobject.dinamicobject;

import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;

public interface DinamicObject extends GameObject {
    int getSpeed();

    void setSpeed(int speed);

    Vector2D getDirection();

    void setDirection(Vector2D direction);

    void setPosition(Point2D position);

    void updateState(double elapsed);

    void collideWith(GameObject obj2);
}
