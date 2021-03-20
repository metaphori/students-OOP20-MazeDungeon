package model.gameobject.simpleobject.door;

import model.common.CardinalPoint;

public interface DoorFactory {

    Door createDoor(CardinalPoint direction);

}
