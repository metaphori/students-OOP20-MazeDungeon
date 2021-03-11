package model.room;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import java.util.Random;
import java.util.Set;

import model.common.Direction;
import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.gameobject.dynamicobject.character.Character;
import model.gameobject.dynamicobject.character.CharacterImpl;
import model.gameobject.simpleobject.FinalArtifact;

public class RoomManagerImpl implements RoomManager {

    private static final int NUMBER_OF_ROOMS = 3;
    private static final Point2D CHARACTER_UP_SPAWN_POSITION = new Point2D(620, 550);
    private static final Point2D CHARACTER_DOWN_SPAWN_POSITION = new Point2D(620, 200);
    private static final Point2D CHARACTER_RIGHT_SPAWN_POSITION = new Point2D(265, 360);
    private static final Point2D CHARACTER_LEFT_SPAWN_POSITION = new Point2D(975, 360);
    private final IdIterator idIterator = new IdIterator();
    private final Map<Point2D, Room> rooms = new HashMap<>();
    private Room actualRoom;
    private final Character character = new CharacterImpl(new Point2D(300, 200), GameObjectType.CHARACTER);
    private int exploredRooms = 1;

    private final Map<Direction, Point2D> characterSpawnPosition = new HashMap<>() {{
        put(Direction.UP, CHARACTER_UP_SPAWN_POSITION);
        put(Direction.DOWN, CHARACTER_DOWN_SPAWN_POSITION);
        put(Direction.LEFT, CHARACTER_LEFT_SPAWN_POSITION);
        put(Direction.RIGHT, CHARACTER_RIGHT_SPAWN_POSITION);
    }};

    public RoomManagerImpl() {
        this.initializeRooms(this.createGameMap());
    }

    /**
     * @param elapsed : the time elapsed from the last call
     */
    public void update(final double elapsed) {
        this.actualRoom.update(elapsed);
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

    private void initializeRooms(final Map<Point2D, Set<Direction>> map) {
        final Point2D spawnRoom = new Point2D(0, 0);
        final Optional<Point2D> bossRoom = map.entrySet().stream()
                                              .filter(entry -> !entry.getKey().equals(spawnRoom))
                                              .filter(entry -> !entry.getValue().contains(Direction.UP))
                                              .map(entry -> entry.getKey())
                                              .findAny();

        for (final Entry<Point2D, Set<Direction>> entry : map.entrySet()) {
            if (entry.getKey().equals(spawnRoom)) {
                rooms.put(entry.getKey(), new RoomBuilder().initialize(this)
                                                           .addDoors(entry.getValue())
                                                           .build());
                continue;
            }
            if (!bossRoom.isEmpty() && entry.getKey().equals(bossRoom.get())) {
                rooms.put(entry.getKey(), new RoomBuilder().initialize(this)
                                                           .addDoors(entry.getValue())
                                                           .addBoss()
                                                           .build());
                continue;
            }
            final Room room = new RoomBuilder().initialize(this)
                                               .addRandomObstacle()
                                               .addDoors(entry.getValue())
                                               .addRandomEnemy()
                                               .build();
            rooms.put(entry.getKey(), room);
        }

        actualRoom = rooms.get(new Point2D(0, 0));
        actualRoom.addDynamicObject(character);
        actualRoom.visit();
    }

    private Map<Point2D, Set<Direction>> createGameMap() {

        final Map<Point2D, Set<Direction>> map = new HashMap<>();
        map.put(new Point2D(0, 0), new HashSet<Direction>());

        while (map.size() < NUMBER_OF_ROOMS) {
            final Direction extractedDirection = Direction.getRandomDirection();
            final Point2D extractedPosition = new LinkedList<>(map.keySet()).get(new Random().nextInt(map.size()));
            final Point2D newRoomPosition = this.getNearbyPoint(extractedPosition, extractedDirection);
            if (!map.containsKey(newRoomPosition)) {
                map.put(newRoomPosition, new HashSet<Direction>());
            }
            map.get(newRoomPosition).add(Direction.getOppositeDirection(extractedDirection));
            map.get(extractedPosition).add(extractedDirection);
        }
        return map;
    }


    /**
     * 
     * @return the actual room
     */
    public Room getCurrentRoom() {
        return actualRoom;
    }

    /**
     * @return the id iterator
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
        throw new IllegalStateException("the room parameter is not part of the floor");
    }

    /**
     * @param direction : the direction of the new room in relation at the actual room
     */
    @Override
    public void changeRoom(final Direction direction) {
        final Room newRoom = rooms.get(this.getNearbyPoint(this.getRoomPosition(actualRoom), direction));
        if (newRoom == null) {
            return;
        }
        if (!newRoom.isVisited()) {
            exploredRooms++;
        }
        this.getCharacter().setPosition(characterSpawnPosition.get(direction));
        newRoom.addDynamicObject(this.getCharacter());
        actualRoom.clean();
        actualRoom = newRoom;
        actualRoom.visit();
        //.out.println(actualRoom.getDoors());

        if (this.allVisited()) {
            final Room startRoom = this.rooms.get(new Point2D(0, 0));
            final double x = (startRoom.getUL().getX() + startRoom.getBR().getX()) / 2;
            final double y = (startRoom.getUL().getY() + startRoom.getBR().getY()) / 2;
            startRoom.addSimpleObject(new FinalArtifact(new Point2D(x, y)));
        }
    }

    /**
     * @return the character
     */
    @Override
    public Character getCharacter() {
        return this.character;
    }

    /**
     * @return true if all the room has been visited
     */
    private boolean allVisited() {
        for (final Room room : rooms.values()) {
            if (!room.isVisited()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the number of visited rooms
     */
    @Override
    public int getVisitedRooms() {
        return exploredRooms;
    }

    /**
     * @return the number of total rooms
     */
    @Override
    public int getTotalRooms() {
        return NUMBER_OF_ROOMS;
    }

}
