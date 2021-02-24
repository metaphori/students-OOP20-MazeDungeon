package model.room;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import model.common.Direction;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.dinamicobject.character.Character;
import model.gameobject.dinamicobject.enemy.AbstractEnemy;

public class RoomImpl implements Room {

    private final List<SimpleObject> simpleObjects = new LinkedList<>();
    private final List<DinamicObject> dinamicObjects = new LinkedList<>();
    private final Set<Direction> nearRooms = new HashSet<>();
    private final RoomManager roomManager;
 
    public RoomImpl(final RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    /**
     * @param elapsed the time passed
     */
    @Override
    public void update(final double elapsed) {
        final List<DinamicObject> temp = new LinkedList<>(List.copyOf(this.dinamicObjects));
        temp.iterator().forEachRemaining(obj -> {
            obj.updateState(elapsed);
        });
        this.checkCollisions();
    }

    /**
     * @param obj
     */
    public void addDinamicObject(final DinamicObject obj) {
        obj.setRoom(this);
        obj.setID(this.roomManager.getIdIterator().next());
        dinamicObjects.add(obj);
    }

    /**
     * @param obj
     */
    public void addSimpleObject(final SimpleObject obj) {
        obj.setRoom(this);
        obj.setID(this.roomManager.getIdIterator().next());
        simpleObjects.add(obj);
    }

    /**
     * 
     */
    @Override
    public void clean() {
        this.dinamicObjects.clear();
    }

    /**
     * @return .
     */
    public List<GameObject> getCurrentGameObjects() {
        final List<GameObject> gameObjects = new LinkedList<>(simpleObjects);
        gameObjects.addAll(dinamicObjects);
        return gameObjects;
    }

    /**
     * @param gameObject
     * @Override
     */
    public void deleteGameObject(final GameObject gameObject) {
        simpleObjects.remove(gameObject);
        dinamicObjects.remove(gameObject);
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
     * @return .
     * @Override
     */
    public RoomManager getRoomManager() {
        return roomManager;
    }

    /**
     * @return return the position of the character if the character is in the room
     * @Override 
     */
    public Optional<Point2D> getCharacterPosition() {
        if (getCharacter().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.getCharacter().get().getPosition());
    }

    /**
     * @return the character if is in the room
     * @Override
     */
    public Optional<Character> getCharacter() {
        return dinamicObjects.stream()
                             .filter(obj -> obj.getGameObjectType() == GameObjectType.CHARACTER) //<- posso essere indipendente da meobjectType e controllare se è castabile a character?
                             .map(obj -> (Character) obj)
                             .findAny();
    }

    /**
     * 
     */
    @Override
    public void addDoor(final Direction direction) {
        nearRooms.add(direction);
    }

    /**
     * 
     * @return . 
     */
    @Override
    public Set<Direction> getDoors() {
        return new HashSet<Direction>(nearRooms);
    }

    /**
     * 
     */
    @Override
    public boolean isDoorOpen() {
        for (final DinamicObject dinamicObject : dinamicObjects) {
            if (AbstractEnemy.class.isAssignableFrom(dinamicObject.getClass()) && dinamicObject.getGameObjectType() != GameObjectType.CHARACTER) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addDinamicObject(final List<DinamicObject> objs) {
        for (final DinamicObject dinamicObject : objs) {
            this.addDinamicObject(dinamicObject);
        }
    }

    @Override
    public void addSimpleObject(final List<SimpleObject> objs) {
        for (final SimpleObject simpleObject : objs) {
            this.addSimpleObject(simpleObject);
        }
    }

}
