package model.room;

import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.simpleobject.SimpleObject;

public interface Room {

    void update();

    void addDinamicObject(DinamicObject obj);

    void addSimpleObject(SimpleObject obj);

}
