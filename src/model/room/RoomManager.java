package model.room;

import model.gameobject.dinamicobject.character.Character;

public interface RoomManager {

    Room getCurrentRoom();

    void update(double elapsed);

    Character getCharacter();
}
