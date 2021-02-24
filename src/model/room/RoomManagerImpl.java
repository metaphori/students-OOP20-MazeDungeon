package model.room;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import model.common.Direction;
import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.enemy.EnemyFactory;
import model.gameobject.dinamicobject.enemy.EnemyFactoryImpl;
import model.gameobject.dinamicobject.character.Character;
import model.gameobject.dinamicobject.character.CharacterImpl;

public class RoomManagerImpl implements RoomManager {

    private static final int NUMBER_OF_ROOMS = 10;
    private final IdIterator idIterator = new IdIterator();
    private final Map<Point2D, Room> rooms = new HashMap<>();
    private Room actualRoom;
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final ObstaclesFactory obstaclesFactory = new ObstaclesFactory();
    private final DoorFactory doorFactory = new DoorFactoryImpl();

    public RoomManagerImpl() {
        this.createGameMap();
    }

    /**
     * @param elapsed the time passed
     */
    public void update(final double elapsed) {
        this.actualRoom.update(elapsed);
    }

    /**
     * @return a random point in the floor
     */
    private Point2D getRandomPointInFloor() {
        final Random rnd = new Random();
        return new LinkedList<>(rooms.keySet()).get(rnd.nextInt(rooms.size()));
    }

    private Point2D getNearbyPoint(final Point2D point, final Direction direction) {
        switch (direction) {
        case UP:
            return new Point2D(point.getX(), point.getY() + 1);
        case DOWN:
            return new Point2D(point.getX(), point.getY() - 1);
        case LEFT:
            return new Point2D(point.getX() - 1, point.getY());
        case RIGHT:
            return new Point2D(point.getX() + 1, point.getY());
        default:
            throw new IllegalStateException("not valid direction");
        }
    }

    private void createGameMap() {

        actualRoom = new RoomImpl(this);
        final Character character = new CharacterImpl(this.idIterator.next(), 130, new Point2D(300, 200), new Vector2D(0, 0), GameObjectType.CHARACTER, this.actualRoom);
        actualRoom.addDinamicObject(character);
        rooms.put(new Point2D(0, 0), actualRoom);

        while (rooms.size() < NUMBER_OF_ROOMS) {
            final Direction randomDirection = Direction.getRandomDirection();
            final Point2D randomPoint = this.getRandomPointInFloor();
            final Point2D newPoint = this.getNearbyPoint(randomPoint, randomDirection);
            if (rooms.containsKey(newPoint)) {
                if (!rooms.get(randomPoint).getDoors().contains(randomDirection)) {
                    rooms.get(randomPoint).addSimpleObject(doorFactory.createDoor(rooms.get(randomPoint), randomDirection));
                    rooms.get(randomPoint).addDoor(randomDirection);
                    rooms.get(newPoint).addDoor(Direction.getOppositeDirection(randomDirection));
                    rooms.get(newPoint).addSimpleObject(doorFactory.createDoor(rooms.get(newPoint), Direction.getOppositeDirection(randomDirection)));
                }
            } else {
                final Room newRoom = new RoomImpl(this);
                newRoom.addDoor(Direction.getOppositeDirection(randomDirection));
                newRoom.addSimpleObject(doorFactory.createDoor(newRoom, Direction.getOppositeDirection(randomDirection)));
                rooms.put(newPoint, newRoom);
                rooms.get(randomPoint).addSimpleObject(doorFactory.createDoor(rooms.get(randomPoint), randomDirection));
                rooms.get(randomPoint).addDoor(randomDirection);
            }
        }
        /*actualRoom = new RoomImpl(this);
        rooms.put(new Point2D(0, 0), actualRoom);

        final Character character = new CharacterImpl(130, new Point2D(300, 200), new Vector2D(0, 0), GameObjectType.CHARACTER, this.actualRoom);
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
        }*/
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

    private Point2D getRoomPosition(final Room room) {
        for (final Entry<Point2D, Room> entry : rooms.entrySet()) {
            if (entry.getValue().equals(room)) {
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
        final Point2D actualPosition = this.getRoomPosition(actualRoom);
        Room newRoom = rooms.get(this.getNearbyPoint(this.getRoomPosition(actualRoom), direction));
        actualRoom.getCharacter().get().setPosition(new Point2D(300, 300));
        newRoom.addDinamicObject(actualRoom.getCharacter().get());
        actualRoom = newRoom;
    }

}
