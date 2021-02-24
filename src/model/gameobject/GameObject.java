package model.gameobject;

import model.common.BoundingBox;
import model.common.CollisionType;
import model.common.GameObjectType;
import model.common.Point2D;
import model.room.Room;

public interface GameObject {
    int getID();

    Point2D getPosition();

    GameObjectType getGameObjectType();

    BoundingBox getBoundingBox();

    void setBoundingBox(BoundingBox boundingBox);

    void setRoom(Room room);

    void collideWith(GameObject obj2);

}
