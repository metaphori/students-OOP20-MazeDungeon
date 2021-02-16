package model.gameobject.dinamicobject;

import model.common.Vector2D;
import model.gameobject.GameObject;

public interface DinamicObject extends GameObject {
    int getSpeed();

    Vector2D getDirection();

    void updateState();

    void move();
}
