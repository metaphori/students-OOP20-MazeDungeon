package model;

import java.util.LinkedList;
import java.util.List;

import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.RoomManager;
import model.room.RoomManagerImpl;
import model.shop.Shop;
import model.shop.ShopImpl;


public class ModelImpl implements Model {
    private final RoomManager roomManager = new RoomManagerImpl();
    private final Shop shop = new ShopImpl(this.roomManager.getCharacter());

    /**
     * @param id : the id of the GameObject.
     * @return the position of the GameObject using it's ID.
     */
    @Override
    public Point2D getGameObjectPosition(final int id) {
       return this.getGameObject(id).getPosition();
    }

    /**
     * @param id : the id of the GameObject.
     * @return the GameObject using it's ID. 
     */
    public GameObject getGameObject(final int id) {
        for (final GameObject gameObject : roomManager.getCurrentRoom().getCurrentGameObjects()) {
            if (gameObject.getID() == id) {
                return gameObject;
            }
        }
        return null;
    }

    /**
     * @return the list of all the GameObject presents in the actual room.
     */
    public List<GameObject> getActualGameObjects() {
        return new LinkedList<GameObject>(roomManager.getCurrentRoom().getCurrentGameObjects());
    }

    /**
     * @param elapsed : the time passed from the last gameLoop cycle.
     */
    @Override
    public void updateGameWorld(final double elapsed) {
        this.roomManager.update(elapsed);
    }

    /**
     * @return the roomManager.
     */
    @Override
    public RoomManager getRoomManager() {
        return this.roomManager;
    }

    /**
     * @return the shop.
     */
    @Override
    public Shop getShop() {
        return this.shop;
    }

    /**
     * @return true if the game is over.
     */
    @Override
    public boolean isGameOver() {
        return this.roomManager.getCharacter().isDead();
    }

    /**
     * @return true if the game is won.
     */
    @Override
    public boolean isWon() {
        return this.roomManager.getCharacter().isWon();
    }
}
