package model.room;

import model.common.CardinalPoint;
import model.common.IdIterator;
import model.gameobject.dynamicobject.character.Character;

public interface RoomManager {
    Room getCurrentRoom();

    void update(double elapsed);

    IdIterator getIdIterator();

    void changeRoom(CardinalPoint direction);

    Character getCharacter();

    int getVisitedRooms();
}
