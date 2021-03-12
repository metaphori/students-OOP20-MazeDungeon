package model.room;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import model.common.CardinalPoint;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.DynamicObject;
import model.gameobject.simpleobject.SimpleObject;

public interface Room {
    void update(double elapsed);

    void addDynamicObject(DynamicObject obj);

    void addSimpleObject(SimpleObject obj);

    void addAllSimpleObject(List<SimpleObject> objs);

    void deleteGameObject(GameObject gameObject);

    List<GameObject> getCurrentGameObjects();

    RoomManager getRoomManager();

    void addDoor(CardinalPoint direction);

    boolean isDoorOpen();

    Set<CardinalPoint> getDoors();

    void clean();

    boolean isVisited();

    void visit();

    Optional<Integer> getBossID();
}
