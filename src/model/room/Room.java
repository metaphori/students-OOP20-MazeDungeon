package model.room;

import java.util.List;
import java.util.Optional;

import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.dinamicobject.character.Character;
import model.gameobject.simpleobject.SimpleObject;

public interface Room {

    void update(double elapsed);

    void addDinamicObject(DinamicObject obj);

    void addSimpleObject(SimpleObject obj);

    List<GameObject> getCurrentGameObjects();

    void deleteGameObject(GameObject gameObject);

    RoomManager getRoomManager();

    Optional<Point2D> getCharacterPosition();

    Optional<Character> getCharacter();
}
