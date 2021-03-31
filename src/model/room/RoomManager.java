package model.room;

import model.common.CardinalPoint;
import model.common.IdIterator;
import model.gameobject.dynamicobject.maincharacter.MainCharacter;

/**
 * An interface that help the user to managing multiple Rooms.
 * 
 * The RoomManager can update the current room and can select 
 * a new Room as current.
 *
 */
public interface RoomManager {

    /**
     * Update the current Room.
     * @param elapsed : the time elapsed from the last call
     */
    void update(double elapsed);

    /**
     * @return the current Room that's the Room where the MainCharacter is.
     */
    Room getCurrentRoom();

    /**
     * @return the IdIterator
     */
    IdIterator getIdIterator();

    /**
     * load a new Room ad current Room.
     * @param cp : the cardinal point of the new Room in reference at the current
     */
    void changeRoom(CardinalPoint cp);

    /**
     * @return the MainCharacter.
     */
    MainCharacter getMainCharacter();

    /**
     * @return the number of visited Rooms
     */
    int getVisitedRooms();
}
