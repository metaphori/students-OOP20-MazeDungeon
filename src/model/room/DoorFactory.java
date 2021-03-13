package model.room;

import model.common.CardinalPoint;
import model.gameobject.simpleobject.Door;

public interface DoorFactory {

    Door createDoor(CardinalPoint direction);

}
