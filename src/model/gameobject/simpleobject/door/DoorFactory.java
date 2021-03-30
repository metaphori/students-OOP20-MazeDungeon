package model.gameobject.simpleobject.door;

import model.common.CardinalPoint;

/**
 * a DoorFactory permit to create doors by cardinal point.
 * 
 */
public interface DoorFactory {

    /**
     * Given a CardinalPoint it return the door at the specific cardinal point.
     * 
     * @param cardinalPoint : the cardinal point of the door you want to create
     * @return the door at the specific cardinal point 
     */
    Door createDoor(CardinalPoint cardinalPoint);

}
