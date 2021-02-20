package model.room;

import java.util.HashMap;
import java.util.Map;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.Coin;
import model.gameobject.dinamicobject.enemy.EnemyFactory;
import model.gameobject.dinamicobject.enemy.EnemyFactoryImpl;
import model.gameobject.dinamicobject.character.CharacterImpl;
import model.gameobject.dinamicobject.character.Character;

public class RoomManagerImpl implements RoomManager {

    private static final int NUMBER_OF_ROOMS = 10;

    private final Map<Point2D, Room> rooms = new HashMap<>();
    private Room actualRoom;
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final Character character = new CharacterImpl(5, 0, new Point2D(300, 200), new Vector2D(0, 0), GameObjectType.COIN);

    public RoomManagerImpl() {
        this.createGameMap();
    }

    /**
     * @param elapsed the time passed
     */
    public void update(final double elapsed) {
        actualRoom.update(elapsed);
    }

    private void createGameMap() {
        //TODO
        actualRoom = new RoomImpl(this);
        rooms.put(new Point2D(0, 0), actualRoom);
        final Coin coin1 = new Coin(0, 0, new Point2D(300, 300), new Vector2D(30, 30), GameObjectType.COIN);

        actualRoom.addDinamicObject(coin1);
        actualRoom.addDinamicObject(character);

        actualRoom.addDinamicObject(new Coin(1, 60, new Point2D(400, 400), new Vector2D(1, 1), GameObjectType.COIN));
        actualRoom.addDinamicObject(new Coin(2, 30, new Point2D(400, 600), new Vector2D(0, -1), GameObjectType.COIN));
        actualRoom.addDinamicObject(new Coin(3, 30, new Point2D(500, 400), new Vector2D(0, 1), GameObjectType.COIN));
        actualRoom.addDinamicObject(new Coin(4, 60, new Point2D(500, 800), new Vector2D(0, -1), GameObjectType.COIN));
        actualRoom.deleteGameObject(coin1);
    }
    
    /**
     * 
     * @return actual room
     */
    public Room getCurrentRoom() {
        return actualRoom;
    }
    
    @Override
    public Character getCharacter() {
        return this.character;
    }

}
