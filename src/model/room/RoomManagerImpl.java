package model.room;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import java.util.Random;
import java.util.Set;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.gameobject.dynamicobject.maincharacter.MainCharacter;
import model.gameobject.dynamicobject.maincharacter.MainCharacterImpl;
import model.gameobject.simpleobject.FinalArtifact;

/**
 * this class implements RoomManager.
 * 
 * a RoomManagerImpl contain a collection of Rooms.
 * The Room disposition is random and all the Rooms have at least a door that connect
 * to another Room.
 * 
 * MainCharacter can move only between adjacent Rooms.
 */
public class RoomManagerImpl implements RoomManager {

    private static final Point2D CHARACTER_NORTH_SPAWN_POSITION = new Point2D(620, 550);
    private static final Point2D CHARACTER_SOUTH_SPAWN_POSITION = new Point2D(620, 200);
    private static final Point2D CHARACTER_EAST_SPAWN_POSITION = new Point2D(265, 360);
    private static final Point2D CHARACTER_WEST_SPAWN_POSITION = new Point2D(975, 360);
    private final IdIterator idIterator = new IdIterator();
    private final Map<Point2D, Room> rooms = new HashMap<>();
    private Room actualRoom;
    private final MainCharacter mainCharacter = new MainCharacterImpl(new Point2D(300, 200), GameObjectType.MAINCHARACTER);
    private int exploredRooms = 1;

    private final Map<CardinalPoint, Point2D> characterSpawnPosition = new HashMap<>();

    /**
     * create a new RoomManagerImpl without specify any parameter.
     */
    public RoomManagerImpl() {
        this.initializeRooms(this.createGameMap());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double elapsed) {
        this.actualRoom.update(elapsed);
        this.initializeSpawnPosition();
    }

    private void initializeSpawnPosition() {
        characterSpawnPosition.put(CardinalPoint.NORTH, CHARACTER_NORTH_SPAWN_POSITION);
        characterSpawnPosition.put(CardinalPoint.SOUTH, CHARACTER_SOUTH_SPAWN_POSITION);
        characterSpawnPosition.put(CardinalPoint.WEST, CHARACTER_WEST_SPAWN_POSITION);
        characterSpawnPosition.put(CardinalPoint.EAST, CHARACTER_EAST_SPAWN_POSITION);
    }

    private Point2D getNearRoomPosition(final Point2D point, final CardinalPoint cardinalPoint) {
        switch (cardinalPoint) {
        case NORTH:
            return new Point2D(point.getX(), point.getY() + 1);
        case SOUTH:
            return new Point2D(point.getX(), point.getY() - 1);
        case WEST:
            return new Point2D(point.getX() - 1, point.getY());
        case EAST:
            return new Point2D(point.getX() + 1, point.getY());
        default:
            throw new IllegalStateException("not valid cardinal point");
        }
    }

    private void initializeRooms(final Map<Point2D, Set<CardinalPoint>> map) {
        final Point2D spawnRoom = new Point2D(0, 0);
        final Optional<Point2D> bossRoom = map.entrySet().stream()
                                              .filter(entry -> !entry.getKey().equals(spawnRoom))
                                              .filter(entry -> !entry.getValue().contains(CardinalPoint.NORTH))
                                              .map(entry -> entry.getKey())
                                              .findAny();

        for (final Entry<Point2D, Set<CardinalPoint>> entry : map.entrySet()) {
            if (entry.getKey().equals(spawnRoom)) {
                rooms.put(entry.getKey(), new RoomBuilderImpl(this).addDoors(entry.getValue())
                                                                   .build());
                continue;
            }
            if (!bossRoom.isEmpty() && entry.getKey().equals(bossRoom.get())) {
                rooms.put(entry.getKey(), new RoomBuilderImpl(this).addDoors(entry.getValue())
                                                                   .addBoss()
                                                                   .build());
                continue;
            }
            final Room room = new RoomBuilderImpl(this).addObstacle()
                                                       .addDoors(entry.getValue())
                                                       .addEnemy()
                                                       .build();
            rooms.put(entry.getKey(), room);
        }

        actualRoom = rooms.get(new Point2D(0, 0));
        actualRoom.addDynamicObject(mainCharacter);
        actualRoom.visit();
    }

    private Map<Point2D, Set<CardinalPoint>> createGameMap() {

        final Map<Point2D, Set<CardinalPoint>> map = new HashMap<>();
        map.put(new Point2D(0, 0), new HashSet<CardinalPoint>());

        while (map.size() < Rooms.NUMBER_OF_ROOMS) {
            final CardinalPoint extractedCP = CardinalPoint.getAny();
            final Point2D extractedPosition = new LinkedList<>(map.keySet()).get(new Random().nextInt(map.size()));
            final Point2D newRoomPosition = this.getNearRoomPosition(extractedPosition, extractedCP);
            if (!map.containsKey(newRoomPosition)) {
                map.put(newRoomPosition, new HashSet<CardinalPoint>());
            }
            map.get(newRoomPosition).add(CardinalPoint.getOpposite(extractedCP));
            map.get(extractedPosition).add(extractedCP);
        }
        return map;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Room getCurrentRoom() {
        return actualRoom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdIterator getIdIterator() {
        return this.idIterator;
    }

    private Point2D getRoomPosition(final Room room) {
        final Optional<Point2D> pos = rooms.entrySet().stream()
                                                      .filter(e -> e.getValue().equals(room))
                                                      .map(e -> e.getKey())
                                                      .findFirst();
        return pos.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeRoom(final CardinalPoint cp) {
        final Room newRoom = rooms.get(this.getNearRoomPosition(this.getRoomPosition(actualRoom), cp));
        if (newRoom == null) {
            return;
        }
        if (!newRoom.isVisited()) {
            exploredRooms++;
        }
        this.getMainCharacter().setPosition(characterSpawnPosition.get(cp));
        newRoom.addDynamicObject(this.getMainCharacter());
        actualRoom.clean();
        actualRoom = newRoom;
        actualRoom.visit();

        if (exploredRooms == Rooms.NUMBER_OF_ROOMS) {
            final Room startRoom = this.rooms.get(new Point2D(0, 0));
            final double x = (Rooms.UL_CORNER.getX() + Rooms.BR_CORNER.getX()) / 2;
            final double y = (Rooms.UL_CORNER.getX() + Rooms.BR_CORNER.getY()) / 2;
            startRoom.addSimpleObject(new FinalArtifact(new Point2D(x, y)));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainCharacter getMainCharacter() {
        return this.mainCharacter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVisitedRooms() {
        return exploredRooms;
    }

}
