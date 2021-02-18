package model.gameobject;

import java.awt.Rectangle;

import model.common.GameObjectType;
import model.common.Point2D;

public interface GameObject {
    int getID();

    Point2D getPosition();

    void setPosition(Point2D position);

    GameObjectType getGameObjectType();

    Rectangle getBoundingBox();

    void setBoundingBox(Rectangle boundingBox);

}
