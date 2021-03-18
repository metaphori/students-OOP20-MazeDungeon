package model.gameobject;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.animations.State;
import model.room.Room;

public interface GameObject {
    int getID();

    void setID(int id);

    Point2D getPosition();

    GameObjectType getGameObjectType();

    BoundingBox getBoundingBox();

    void setBoundingBox(BoundingBox boundingBox);

    void setRoom(Room room);

    void collideWith(GameObject obj2);

    State getState();
}
