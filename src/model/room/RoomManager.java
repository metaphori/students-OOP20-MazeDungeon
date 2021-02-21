package model.room;

import model.common.IdIterator;
import model.gameobject.dinamicobject.bullet.BulletFactory;
import model.gameobject.dinamicobject.character.Character;

public interface RoomManager {

    Room getCurrentRoom();

    void update(double elapsed);

    Character getCharacter();

    IdIterator getIdIterator();

}
