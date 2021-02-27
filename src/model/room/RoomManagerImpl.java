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

    private static final Point2D UL_CORNER = new Point2D(240, 177); //TODO in caso di resize della finestra vanno cambiati!!!!
    private static final Point2D BR_CORNER = new Point2D(1025, 633);
    private static final int NUMBER_OF_ROOMS = 10;
    private final IdIterator idIterator = new IdIterator();
    private final Map<Point2D, Room> rooms = new HashMap<>();
    private Room actualRoom;
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final ObstaclesFactory obstaclesFactory = new ObstaclesFactory(UL_CORNER, BR_CORNER);
    private final DoorFactory doorFactory = new DoorFactoryImpl();
    private final Map<Direction, Point2D> characterSpawnPosition = new HashMap<>() {{
        put(Direction.UP, new Point2D(620, 550));
        put(Direction.DOWN, new Point2D(620, 200));
        put(Direction.LEFT, new Point2D(975, 360));
        put(Direction.RIGHT, new Point2D(265, 360));
    }};

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
        //actualRoom.addSimpleObject(obstaclesFactory.getEmptyRoom());
        actualRoom.addSimpleObject(obstaclesFactory.createXComposition());
        final Character character = new CharacterImpl(new Point2D(300, 200), GameObjectType.CHARACTER);
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
                newRoom.addDinamicObject(this.enemyFactory.createSprout(new Point2D(450, 300)));
                newRoom.addSimpleObject(obstaclesFactory.getEmptyRoom());
                newRoom.addDoor(Direction.getOppositeDirection(randomDirection));
                newRoom.addSimpleObject(doorFactory.createDoor(newRoom, Direction.getOppositeDirection(randomDirection)));
                rooms.put(newPoint, newRoom);
                rooms.get(randomPoint).addSimpleObject(doorFactory.createDoor(rooms.get(randomPoint), randomDirection));
                rooms.get(randomPoint).addDoor(randomDirection);
            }
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
        final Room newRoom = rooms.get(this.getNearbyPoint(this.getRoomPosition(actualRoom), direction));
        actualRoom.getCharacter().get().setPosition(characterSpawnPosition.get(direction));
        newRoom.addDinamicObject(actualRoom.getCharacter().get());
        actualRoom.clean();
        actualRoom = newRoom;
    }

}
