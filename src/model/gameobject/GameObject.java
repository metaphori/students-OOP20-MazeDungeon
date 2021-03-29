package model.gameobject;

import animations.State;
import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.room.Room;

/**
 * An interface that represent the basic entity which can be placed in a Room during the gameloop.
 * 
 *
 */
public interface GameObject {

    /**
     * @return the id of the GameObject.
     */
    int getID();

    /**
     * @param id : set the id of the GameObject.
     */
    void setID(int id);

    /**
     * @return the position of the object.
     */
    Point2D getPosition();

    /**
     * @return the type of the GameObject.
     */
    GameObjectType getGameObjectType();

    /**
     * @return the BoundingBox corresponding to the GameObject.
     */
    BoundingBox getBoundingBox();

    /**
     * @param boundingBox : set boundingBox as the BoundingBox of the GameObject.
     */
    void setBoundingBox(BoundingBox boundingBox);

    /**
     * @param room : set room as the Room where the GameObject is placed.
     */
    void setRoom(Room room);

    /**
     * This method define what to do when this GameObject collide with another GameObject.
     * @param obj2 : the GameObject which is colliding with.
     */
    void collideWith(GameObject obj2);

    /**
     * @return the state of the object.
     */
    State getState();
}
