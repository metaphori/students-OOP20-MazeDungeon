package model.room;

import model.common.Direction;
import model.gameobject.simpleobject.Door;

public interface DoorFactory {
    Door createLeftDoor();

    Door createRightDoor();

    Door createUpDoor();

    Door createDownDoor();

    Door createDoor(Direction direction);

}
