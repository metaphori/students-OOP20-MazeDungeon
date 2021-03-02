package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.RoomManager;
import model.room.RoomManagerImpl;
import model.shop.Shop;
import model.shop.ShopImpl;
import mvc.Model;


public class ModelImpl implements Model {
    private final RoomManager roomManager = new RoomManagerImpl();
    private final Shop shop = new ShopImpl(this, this.roomManager.getCharacter());
    
    /**
     */
    @Override
    public Point2D getGameObjectPosition(final int id) {
       return this.getGameObject(id).getPosition();
    }

    /**
     * @param id
     * @return . 
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
     * @return .
     */
    public List<GameObject> getActualGameObjects() {
        return new LinkedList<GameObject>(roomManager.getCurrentRoom().getCurrentGameObjects());
    }

    @Override
    public void update(final double elapsed) {
        this.roomManager.update(elapsed);
    }

    @Override
    public RoomManager getRoomManager() {
        return this.roomManager;
    }

    @Override
    public boolean isGameOver() {
        return this.roomManager.getCharacter().isDead();
    }
    @Override
    public Shop getShop() {
        return this.shop;
    }
}
