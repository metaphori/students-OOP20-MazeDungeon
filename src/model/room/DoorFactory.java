package model.room;

import model.gameobject.simpleobject.Door;

public interface DoorFactory {
    Door createLeftDoor(Room room);

    Door createRightDoor(Room room);

    Door createUpDoor(Room room);

    Door createDownDoor(Room room);

}
