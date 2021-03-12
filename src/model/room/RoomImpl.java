package model.room;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.DynamicObject;
import model.gameobject.dynamicobject.enemy.AbstractEnemy;
import model.gameobject.simpleobject.SimpleObject;

public class RoomImpl implements Room {

    private final List<SimpleObject> simpleObjects = new LinkedList<>();
    private final List<DynamicObject> dynamicObjects = new LinkedList<>();
    private final Set<CardinalPoint> nearRooms = new HashSet<>();
    private final RoomManager roomManager;
    private boolean isVisited = false;
 
    public RoomImpl(final RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    /**
     * @param elapsed : the time passed
     */
    @Override
    public void update(final double elapsed) {
        final List<DynamicObject> temp = new LinkedList<>(List.copyOf(this.dynamicObjects));
        temp.iterator().forEachRemaining(obj -> {
            obj.updateState(elapsed);
        });
        this.checkCollisions();
    }

    /**
     * @param obj : the dynamic object to add in the room
     */
    public void addDynamicObject(final DynamicObject obj) {
        obj.setRoom(this);
        obj.setID(this.roomManager.getIdIterator().next());
        dynamicObjects.add(obj);
    }

    /**
     * @param obj : the simple object to add in the room
     */
    public void addSimpleObject(final SimpleObject obj) {
        obj.setRoom(this);
        obj.setID(this.roomManager.getIdIterator().next());
        simpleObjects.add(obj);
    }

    /**
     * delete all dynamic objects.
     */
    @Override
    public void clean() {
        this.dynamicObjects.clear();
    }

    /**
     * @return the list of the objects in the rooms
     */
    public List<GameObject> getCurrentGameObjects() {
        final List<GameObject> gameObjects = new LinkedList<>(simpleObjects);
        gameObjects.addAll(dynamicObjects);
        return gameObjects;
    }

    /**
     * @param gameObject : the GameObject to remove from the room
     */
    @Override
    public void deleteGameObject(final GameObject gameObject) {
        simpleObjects.remove(gameObject);
        dynamicObjects.remove(gameObject);
    }

    private void checkCollisions() {
        for (final GameObject obj1 : this.getCurrentGameObjects()) {
            for (final GameObject obj2 : this.getCurrentGameObjects()) {
                if (obj1.getBoundingBox() == null || obj2.getBoundingBox() == null || obj1.equals(obj2)) {
                    continue;
                }
                if (obj1.getBoundingBox().intersectWith(obj2.getBoundingBox())) {
                    obj1.collideWith(obj2);
                }
            }
        }
    }

    /**
     * @return the RoomManager
     */
    @Override
    public RoomManager getRoomManager() {
        return roomManager;
    }

    /**
     * @param direction : direction of the door.
     */
    @Override
    public void addDoor(final CardinalPoint direction) {
        nearRooms.add(direction);
    }

    /**
     * @return all the directions in the room that have a door
     */
    @Override
    public Set<CardinalPoint> getDoors() {
        return new HashSet<CardinalPoint>(nearRooms);
    }

    /**
     * @return true if character can cross the door
     */
    @Override
    public boolean isDoorOpen() {
        for (final DynamicObject dinamicObject : dynamicObjects) {
            if (AbstractEnemy.class.isAssignableFrom(dinamicObject.getClass()) && dinamicObject.getGameObjectType() != GameObjectType.CHARACTER) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param objs : simple objects to add in the room
     */
    @Override
    public void addAllSimpleObject(final List<SimpleObject> objs) {
        for (final SimpleObject simpleObject : objs) {
            this.addSimpleObject(simpleObject);
        }
    }

    /**
     * @return true if the room has been visited
     */
    @Override
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * set the visited attribute true.
     */
    @Override
    public void visit() {
        this.isVisited = true;
    }

    /**
     * @return an empty optional if the room doesn't contain the boss.
     *         the boss ID otherwise
     */
    @Override
    public Optional<Integer> getBossID() {
        for (final GameObject gameObject : getCurrentGameObjects()) {
            if (gameObject.getGameObjectType() == GameObjectType.ENEMY_BOSS) {
                return Optional.of(gameObject.getID());
            }
        }
        return Optional.empty();
    }

}
