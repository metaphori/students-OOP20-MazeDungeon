package model.room;

import java.util.HashMap;
import java.util.Map;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.Coin;
import model.gameobject.dinamicobject.enemy.EnemyFactory;
import model.gameobject.dinamicobject.enemy.EnemyFactoryImpl;

public class RoomManagerImpl implements RoomManager {

    private static final int NUMBER_OF_ROOMS = 10;

    private final Map<Point2D, Room> rooms = new HashMap<>();
    private Room actualRoom;
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();

    public RoomManagerImpl() {
        this.createGameMap();
    }

    /**
     * 
     */
    public void update() {
        actualRoom.update();
    }

    private void createGameMap() {
        //TODO
        actualRoom = new RoomImpl(this);
        rooms.put(new Point2D(0, 0), actualRoom);
        Coin testObject = new Coin(0, 0, new Point2D(300, 300), new Vector2D(1, 0), GameObjectType.COIN);
        actualRoom.addDinamicObject(testObject);
    }

    /**
     * 
     * @return actual room
     */
    public Room getCurrentRoom() {
        return actualRoom;
    }

}
