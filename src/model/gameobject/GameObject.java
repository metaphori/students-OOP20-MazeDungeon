package model.gameobject;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;

public interface GameObject {
    int getID();

    Point2D getPosition();

    void setPosition(Point2D position);

    GameObjectType getGameObjectType();

    BoundingBox getBoundingBox();

    void setBoundingBox(BoundingBox boundingBox);

    void collideWith(GameObject obj2);

}
