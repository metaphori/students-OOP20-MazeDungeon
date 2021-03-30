package model;

import java.util.List;

import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.RoomManager;
import model.shop.Shop;

/**
 * An interface that define all the main methods of the Model. 
 * It represent the logic part of MazeDungeon, 
 * in particular it provides the interactions with the RoomManager, the Shop and the updating of the game world.
 *
 */
public interface Model {

    /**
     * @param id : the id of the GameObject.
     * @return the position of the GameObject using it's ID.
     */
    Point2D getGameObjectPosition(int id);

    /**
     * @param id : the id of the GameObject.
     * @return the GameObject using it's ID. 
     */
    GameObject getGameObject(int id);

    /**
     * @return the list of all the GameObject presents in the actual room.
     */
    List<GameObject> getActualGameObjects();

    /**
     * @param elapsed : the time passed from the last gameLoop cycle.
     */
    void updateGameWorld(double elapsed);

    /**
     * @return the roomManager.
     */
    RoomManager getRoomManager();

    /**
     * @return true if the game is over.
     */
    boolean isGameOver();

    /**
     * @return the shop.
     */
    Shop getShop();

    /**
     * @return true if the game is won.
     */
    boolean isWon();
}
