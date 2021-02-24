package model.room;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.common.Direction;
import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.common.Vector2D;

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
    private final DoorFactory doorFactory = new DoorFactoryImpl(this.idIterator);

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
        //final Enemy enemyBoss = this.enemyFactory.createBoss(new Point2D(500, 300), new Vector2D(1, 0), this.actualRoom);
        final Enemy enemySprout = this.enemyFactory.createSprout(new Point2D(500, 200), new Vector2D(0, 1), this.actualRoom);

        actualRoom.addDinamicObject(character);
        actualRoom.addDinamicObject(enemySkeletonSeeker);
        actualRoom.addDinamicObject(this.enemyFactory.createSkeletonSeeker(new Point2D(500, 300), new Vector2D(-1, 1), this.actualRoom));
        actualRoom.addDinamicObject(enemySoul);
        actualRoom.addDinamicObject(this.enemyFactory.createSoul(new Point2D(350, 500), new Vector2D(1, 1), this.actualRoom));
        actualRoom.addDinamicObject(this.enemyFactory.createSoul(new Point2D(400, 500), new Vector2D(1, 1), this.actualRoom));
        actualRoom.addDinamicObject(this.enemyFactory.createSoul(new Point2D(350, 200), new Vector2D(1, 1), this.actualRoom));
        actualRoom.addDinamicObject(enemySprout);
        //actualRoom.addDinamicObject(enemyBoss);
        actualRoom.addSimpleObject(doorFactory.createDownDoor(actualRoom));
        actualRoom.addSimpleObject(doorFactory.createRightDoor(actualRoom));
        actualRoom.addSimpleObject(doorFactory.createUpDoor(actualRoom));
        actualRoom.addSimpleObject(doorFactory.createLeftDoor(actualRoom));

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

    private Point2D getActualPosition() {
        for (final Entry<Point2D, Room> entry : rooms.entrySet()) {
            if (entry.getValue().equals(actualRoom)) {
                return entry.getKey();
            }
        }
        return null; //TODO non deve restituire null
    }

    /**
     * @param direction .
     * @Override
     */
    public void changeRoom(final Direction direction) {
        final Point2D actualPosition = this.getActualPosition();
        final Room newRoom;
        switch (direction) {
        case UP:
            newRoom = rooms.get(new Point2D(actualPosition.getX(), actualPosition.getY() + 1));
            break;
        case DOWN:
            newRoom = rooms.get(new Point2D(actualPosition.getX(), actualPosition.getY() - 1));
            break;
        case LEFT:
            newRoom = rooms.get(new Point2D(actualPosition.getX() - 1, actualPosition.getY()));
            break;
        case RIGHT:
            newRoom = rooms.get(new Point2D(actualPosition.getX() + 1, actualPosition.getY()));
            break;
        default:
            return;
        }
        //seleziona newRoom come room attuale
    }

}
