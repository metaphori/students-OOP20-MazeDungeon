package model.room;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.Coin;
import model.gameobject.dinamicobject.bullet.Bullet;
import model.gameobject.dinamicobject.enemy.Enemy;
import model.gameobject.dinamicobject.enemy.EnemyFactory;
import model.gameobject.dinamicobject.enemy.EnemyFactoryImpl;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.dinamicobject.character.CharacterImpl;
import model.gameobject.dinamicobject.character.Character;

public class RoomManagerImpl implements RoomManager {

    private static final int NUMBER_OF_ROOMS = 10;
    private final IdIterator idIterator = new IdIterator();
    private final Map<Point2D, Room> rooms = new HashMap<>();
    private Room actualRoom;
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl(this.idIterator);
    private final ObstaclesFactory obstaclesFactory = new ObstaclesFactory(this.idIterator);

    public RoomManagerImpl() {
        this.createGameMap();
    }

    /**
     * @param elapsed the time passed
     */
    public void update(final double elapsed) {
        this.actualRoom.update(elapsed);
    }

    private void createGameMap() {
        //TODO
        actualRoom = new RoomImpl(this);
        rooms.put(new Point2D(0, 0), actualRoom);
        final Character character = new CharacterImpl(this.idIterator.next(), 130, new Point2D(300, 200), new Vector2D(0, 0), GameObjectType.CHARACTER, this.actualRoom);
        final Enemy enemySoul = this.enemyFactory.createSoul(new Point2D(500, 500), new Vector2D(1, 1), this.actualRoom);
        final Enemy enemySkeletonSeeker = this.enemyFactory.createSkeletonSeeker(new Point2D(300, 300), new Vector2D(-1, 1), this.actualRoom);
        final Enemy enemyBoss = this.enemyFactory.createBoss(new Point2D(500, 300), new Vector2D(1, 0), this.actualRoom);
        final Enemy enemySprout = this.enemyFactory.createSprout(new Point2D(500, 200), new Vector2D(0, 1), this.actualRoom);
        actualRoom.addDinamicObject(enemySkeletonSeeker);
        actualRoom.addDinamicObject(enemySoul);
        actualRoom.addDinamicObject(character);
        actualRoom.addDinamicObject(enemySprout);
        
        actualRoom.addDinamicObject(enemyBoss);

        final Random rnd = new Random();
        for (int i = 1; i < 2; i++) {
            actualRoom.addDinamicObject(new Coin(this.idIterator.next(), 50, new Point2D(rnd.nextInt(785) + 240, rnd.nextInt(456) + 177), 
                                                        new Vector2D(-1 , 0), GameObjectType.COIN, this.actualRoom));
        }
        for (final SimpleObject obj: obstaclesFactory.getEmptyRoom(this.actualRoom)) {
            actualRoom.addSimpleObject(obj);
        }
    }

    /**
     * 
     * @return actual room
     */
    public Room getCurrentRoom() {
        return actualRoom;
    }

    /**
     * 
     */
    @Override
    public IdIterator getIdIterator() {
        return this.idIterator;
    }



}
