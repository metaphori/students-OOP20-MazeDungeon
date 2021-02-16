package model.gameobject;

import model.common.Point2D;

public interface GameObject {
    int getID();

    Point2D getPosition();

    void setPosition(Point2D position);
}
