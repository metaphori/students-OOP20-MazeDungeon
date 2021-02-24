package model.room;

import model.common.Direction;
import model.gameobject.simpleobject.Door;

public interface DoorFactory {
    Door createLeftDoor(Room room);

    Door createRightDoor(Room room);

    Door createUpDoor(Room room);

    Door createDownDoor(Room room);

    Door createDoor(Room room, Direction direction);

}
