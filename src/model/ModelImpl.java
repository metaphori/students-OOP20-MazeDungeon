package model;

import java.util.LinkedList;
import java.util.List;

import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.RoomManager;
import model.room.RoomManagerImpl;
import model.shop.Shop;
import model.shop.ShopImpl;

/**
 * This class implements all the methods specified in the corresponding interface.
 * It contains the main connection to the logics of the application.
 *
 */

public class ModelImpl implements Model {
    private final RoomManager roomManager = new RoomManagerImpl();
    private final Shop shop = new ShopImpl(this.roomManager.getCharacter());

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getGameObjectPosition(final int id) {
       return this.getGameObject(id).getPosition();
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    public List<GameObject> getActualGameObjects() {
        return new LinkedList<GameObject>(roomManager.getCurrentRoom().getCurrentGameObjects());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGameWorld(final double elapsed) {
        this.roomManager.update(elapsed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoomManager getRoomManager() {
        return this.roomManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shop getShop() {
        return this.shop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return this.roomManager.getCharacter().isDead();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWon() {
        return this.roomManager.getCharacter().isWin();
    }
}
