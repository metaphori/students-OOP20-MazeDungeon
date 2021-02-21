package model.room;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.Coin;
import model.gameobject.dinamicobject.enemy.Enemy;
import model.gameobject.dinamicobject.enemy.EnemyFactory;
import model.gameobject.dinamicobject.enemy.EnemyFactoryImpl;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.dinamicobject.character.CharacterImpl;
import model.gameobject.dinamicobject.character.Character;

public class RoomManagerImpl implements RoomManager {

    private static final int NUMBER_OF_ROOMS = 10;

    private final Map<Point2D, Room> rooms = new HashMap<>();
    private Room actualRoom;
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private Character character;
    private final ObstaclesFactory obstaclesFactory = new ObstaclesFactory();

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
        character = new CharacterImpl(0, 0, new Point2D(300, 200), new Vector2D(0, 0), GameObjectType.ENEMY_SKELETON);

        final Enemy enemySoul = this.enemyFactory.createSoul(73, 90, new Point2D(500, 500), new Vector2D(1, 1));
        final Enemy enemySkeletonSeeker = this.enemyFactory.createSkeletonSeeker(74, 20, new Point2D(200, 200), new Vector2D(-1, 1));
        actualRoom.addDinamicObject(enemySkeletonSeeker);
        actualRoom.addDinamicObject(enemySoul);
        actualRoom.addDinamicObject(character);

        Random rnd = new Random();
        for (int i = 1; i < 2; i++) {
            actualRoom.addDinamicObject(new Coin(i, 50, new Point2D(rnd.nextInt(785) + 240, rnd.nextInt(456) + 177), 
                                                        new Vector2D(-1 , 0), GameObjectType.COIN));
        }
        for (SimpleObject obj: obstaclesFactory.getEmptyRoom()) {
            actualRoom.addSimpleObject(obj);
        }

        /*actualRoom.addDinamicObject(new Coin(1, 30, new Point2D(400, 400), new Vector2D(-1, 0), GameObjectType.COIN, actualRoom));
        actualRoom.addDinamicObject(new Coin(2, 30, new Point2D(400, 600), new Vector2D(-1, 0), GameObjectType.COIN, actualRoom));
        actualRoom.addDinamicObject(new Coin(3, 30, new Point2D(700, 500), new Vector2D(1, 0), GameObjectType.COIN, actualRoom));
        actualRoom.addDinamicObject(new Coin(4, 30, new Point2D(800, 500), new Vector2D(-1, 0), GameObjectType.COIN, actualRoom));
        */
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
