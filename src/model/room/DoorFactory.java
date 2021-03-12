package model.room;

import model.common.CardinalPoint;
import model.gameobject.simpleobject.Door;

public interface DoorFactory {
    Door createWestDoor();

    Door createEastDoor();

    Door createNorthDoor();

    Door createSouthDoor();

    Door createDoor(CardinalPoint direction);

}
