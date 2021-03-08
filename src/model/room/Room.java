package model.room;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import model.common.Direction;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.DynamicObject;
import model.gameobject.simpleobject.SimpleObject;

public interface Room {
    void update(double elapsed);

    void addDynamicObject(DynamicObject obj);

    void addAllDynamicObject(List<DynamicObject> objs);

    void addSimpleObject(SimpleObject obj);

    void addAllSimpleObject(List<SimpleObject> objs);

    List<GameObject> getCurrentGameObjects();

    void deleteGameObject(GameObject gameObject);

    RoomManager getRoomManager();

    void addDoor(Direction direction);

    boolean isDoorOpen();

    Set<Direction> getDoors();

    void clean();

    Point2D getUL();

    Point2D getBR();

    boolean isVisited();

    void visit();

    Optional<Integer> getBossID();
}
