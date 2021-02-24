package model.room;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import model.common.Direction;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.dinamicobject.character.Character;
import model.gameobject.simpleobject.SimpleObject;

public interface Room {
    void update(double elapsed);

    void addDinamicObject(DinamicObject obj);

    void addDinamicObject(List<DinamicObject> objs);

    void addSimpleObject(SimpleObject obj);

    void addSimpleObject(List<SimpleObject> objs);

    List<GameObject> getCurrentGameObjects();

    void deleteGameObject(GameObject gameObject);

    RoomManager getRoomManager();

    Optional<Point2D> getCharacterPosition();

    Optional<Character> getCharacter();

    void addDoor(Direction direction);

    boolean isDoorOpen();

    Set<Direction> getDoors();

    void clean();

}
