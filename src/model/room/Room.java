package model.room;

import java.util.List;

import model.gameobject.GameObject;
import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.dinamicobject.character.Character;

public interface Room {

    void update(double elapsed);

    void addDinamicObject(DinamicObject obj);

    void addSimpleObject(SimpleObject obj);

    List<GameObject> getCurrentGameObjects();

    void deleteGameObject(GameObject gameObject);

   
}
