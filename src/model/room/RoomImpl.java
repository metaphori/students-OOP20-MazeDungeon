package model.room;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.DynamicObject;
import model.gameobject.dynamicobject.enemy.AbstractEnemy;
import model.gameobject.dynamicobject.enemy.Enemy;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.simpleobject.door.Door;

/**
 * The implementation of the Interface Room.
 * 
 * A Room is a GameObject container.
 * 
 * On each GameObject, the Room checks collisions.
 * On each DyamicObject, the Room updates the state.
 */
public class RoomImpl implements Room {

    private final List<SimpleObject> simpleObjects = new LinkedList<>();
    private final List<DynamicObject> dynamicObjects = new LinkedList<>();
    private final Set<CardinalPoint> nearRooms = new HashSet<>();
    private final RoomManager roomManager;
    private boolean isVisited;
 
    /**
     * create a new Room giving a RoomManager.
     * @param roomManager : RoomManager of the new Room
     * @param enemies : 
     * @param obstacles : 
     * @param doors : 
     */
    public RoomImpl(final RoomManager roomManager, final List<Enemy> enemies, final List<SimpleObject> obstacles, final List<Door> doors) {
        this.roomManager = roomManager;

        for (final SimpleObject obstacle : obstacles) {
            this.initGameObject(obstacle);
            simpleObjects.add(obstacle);
        }

        for (final Door door : doors) {
            nearRooms.add(door.getCardinalPoint());
            this.initGameObject(door);
            simpleObjects.add(door);
        }

        for (final Enemy enemy : enemies) {
            this.initGameObject(enemy);
            dynamicObjects.add(enemy);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double elapsed) {
        final List<DynamicObject> temp = new LinkedList<>(List.copyOf(this.dynamicObjects));
        temp.iterator().forEachRemaining(obj -> {
            obj.update(elapsed);
        });
        this.checkCollisions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDynamicObject(final DynamicObject obj) {
        this.initGameObject(obj);
        dynamicObjects.add(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSimpleObject(final SimpleObject obj) {
        this.initGameObject(obj);
        simpleObjects.add(obj);
    }

    private void initGameObject(final GameObject obj) {
        obj.setRoom(this);
        obj.setID(this.roomManager.getIdIterator().next());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clean() {
        this.dynamicObjects.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getCurrentGameObjects() {
        final List<GameObject> gameObjects = new LinkedList<>(simpleObjects);
        gameObjects.addAll(dynamicObjects);
        return gameObjects;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public RoomManager getRoomManager() {
        return roomManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<CardinalPoint> getDoors() {
        return new HashSet<CardinalPoint>(nearRooms);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDoorOpen() {
        for (final DynamicObject dinamicObject : dynamicObjects) {
            if (AbstractEnemy.class.isAssignableFrom(dinamicObject.getClass()) && dinamicObject.getGameObjectType() != GameObjectType.MAINCHARACTER) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit() {
        this.isVisited = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getBossID() {
        return this.getCurrentGameObjects().stream()
                                           .filter(o -> o.getGameObjectType() == GameObjectType.ENEMY_BOSS)
                                           .map(o -> o.getID())
                                           .findAny();
    }

}
