package model.room;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import model.common.CardinalPoint;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.DynamicObject;
import model.gameobject.simpleobject.SimpleObject;

/**
 * An Interface that describe a Room.
 * 
 * The user can add and remove GameObjects, update their state and check collision
 * between them.
 */
public interface Room {

    /**
     * Update the state of each DynamicObject using the time passed from the last update.
     * @param elapsed : the time elapsed from the previous update
     */
    void update(double elapsed);

    /** 
     * Add a DynamicObject to the room.
     * @param obj : DynamicObject to add to the room
     */
    void addDynamicObject(DynamicObject obj);

    /**
     * Add a SimpleObject to the room.
     * @param obj : SimpleObject to add to the room
     */
    void addSimpleObject(SimpleObject obj);

    /**
     * Remove a GameObject from the room.
     * @param gameObject : GameObject to remove from the room
     */
    void deleteGameObject(GameObject gameObject);

    /**
     * @return a list containing all the GameObjects
     */
    List<GameObject> getCurrentGameObjects();

    /**
     * @return the RoomManager that manage the Room
     */
    RoomManager getRoomManager();

    /**
     * @return true if Doors are open. Doors are open if there are no enemy in the Room
     */
    boolean isDoorOpen();

    /**
     * @return the list of CardinalPoint where the doors are
     */
    Set<CardinalPoint> getDoors();

    /**
     * Remove all the DynamicObjects from the room.
     */
    void clean();

    /**
     * @return true if the Room has been visited from the MainCharacter
     */
    boolean isVisited();

    /**
     * Set the visited flag to true.
     */
    void visit();

    /**
     * @return the Boss ID if there is a Boss in the Room, OptionalEmpty otherwise
     */
    Optional<Integer> getBossID();
}
