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
import model.gameobject.dynamicobject.character.Character;
import model.gameobject.dynamicobject.character.CharacterImpl;
import model.gameobject.simpleobject.FinalArtifact;

public class RoomManagerImpl implements RoomManager {

    private static final Point2D CHARACTER_NORTH_SPAWN_POSITION = new Point2D(620, 550);
    private static final Point2D CHARACTER_SOUTH_SPAWN_POSITION = new Point2D(620, 200);
    private static final Point2D CHARACTER_EAST_SPAWN_POSITION = new Point2D(265, 360);
    private static final Point2D CHARACTER_WEST_SPAWN_POSITION = new Point2D(975, 360);
    private final IdIterator idIterator = new IdIterator();
    private final Map<Point2D, Room> rooms = new HashMap<>();
    private Room actualRoom;
    private final Character character = new CharacterImpl(new Point2D(300, 200), GameObjectType.CHARACTER);
    private int exploredRooms = 1;

    private final Map<CardinalPoint, Point2D> characterSpawnPosition = new HashMap<>() {{
        put(CardinalPoint.NORTH, CHARACTER_NORTH_SPAWN_POSITION);
        put(CardinalPoint.SOUTH, CHARACTER_SOUTH_SPAWN_POSITION);
        put(CardinalPoint.WEST, CHARACTER_WEST_SPAWN_POSITION);
        put(CardinalPoint.EAST, CHARACTER_EAST_SPAWN_POSITION);
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

    private Point2D getNearbyPoint(final Point2D point, final CardinalPoint cardinalPoint) {
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
                rooms.put(entry.getKey(), new RoomBuilderImpl().initialize(this)
                                                           .addDoors(entry.getValue())
                                                           .build());
                continue;
            }
            if (!bossRoom.isEmpty() && entry.getKey().equals(bossRoom.get())) {
                rooms.put(entry.getKey(), new RoomBuilderImpl().initialize(this)
                                                           .addDoors(entry.getValue())
                                                           .addBoss()
                                                           .build());
                continue;
            }
            final Room room = new RoomBuilderImpl().initialize(this)
                                               .addObstacle()
                                               .addDoors(entry.getValue())
                                               .addEnemy()
                                               .build();
            rooms.put(entry.getKey(), room);
        }

        actualRoom = rooms.get(new Point2D(0, 0));
        actualRoom.addDynamicObject(character);
        actualRoom.visit();
    }

    private Map<Point2D, Set<CardinalPoint>> createGameMap() {

        final Map<Point2D, Set<CardinalPoint>> map = new HashMap<>();
        map.put(new Point2D(0, 0), new HashSet<CardinalPoint>());

        while (map.size() < Rooms.NUMBER_OF_ROOMS) {
            final CardinalPoint extractedCP = CardinalPoint.getAny();
            final Point2D extractedPosition = new LinkedList<>(map.keySet()).get(new Random().nextInt(map.size()));
            final Point2D newRoomPosition = this.getNearbyPoint(extractedPosition, extractedCP);
            if (!map.containsKey(newRoomPosition)) {
                map.put(newRoomPosition, new HashSet<CardinalPoint>());
            }
            map.get(newRoomPosition).add(CardinalPoint.getOpposite(extractedCP));
            map.get(extractedPosition).add(extractedCP);
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
     * @param cp : the cardinalPoint of the new room in relation at the actual room
     */
    @Override
    public void changeRoom(final CardinalPoint cp) {
        final Room newRoom = rooms.get(this.getNearbyPoint(this.getRoomPosition(actualRoom), cp));
        if (newRoom == null) {
            return;
        }
        if (!newRoom.isVisited()) {
            exploredRooms++;
        }
        this.getCharacter().setPosition(characterSpawnPosition.get(cp));
        newRoom.addDynamicObject(this.getCharacter());
        actualRoom.clean();
        actualRoom = newRoom;
        actualRoom.visit();
        //.out.println(actualRoom.getDoors());

        if (this.allVisited()) {
            final Room startRoom = this.rooms.get(new Point2D(0, 0));
            final double x = (Rooms.UL_CORNER.getX() + Rooms.BR_CORNER.getX()) / 2;
            final double y = (Rooms.UL_CORNER.getX() + Rooms.BR_CORNER.getY()) / 2;
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

}
