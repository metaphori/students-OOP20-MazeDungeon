package model.room;

import java.util.LinkedList;
import java.util.List;

import model.gameobject.GameObject;
import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.simpleobject.SimpleObject;

public class RoomImpl implements Room {

    private final List<SimpleObject> simpleObjects = new LinkedList<>();
    private final List<DinamicObject> dinamicObjects = new LinkedList<>();
    private final RoomManager roomManager;

    public RoomImpl(final RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    /**
     * @param elapsed the time passed
     */
    @Override
    public void update(final double elapsed) {
        for (final DinamicObject obj : dinamicObjects) {
            obj.updateState(elapsed);
        }
    }

    /**
     * 
     * @param obj
     */
    public void addDinamicObject(final DinamicObject obj) {
        dinamicObjects.add(obj);
    }

    /**
     * 
     * @param obj
     */
    public void addSimpleObject(final SimpleObject obj) {
        simpleObjects.add(obj);
    }

    /**
     * 
     * @return .
     */
    public List<GameObject> getCurrentGameObjects() {
        final List<GameObject> gameObjects = new LinkedList<>(simpleObjects);
        gameObjects.addAll(dinamicObjects);
        return gameObjects;
    }

    /**
     * 
     */
    @Override
    public void deleteGameObject(final GameObject gameObject) {
        simpleObjects.remove(gameObject);
        dinamicObjects.remove(gameObject);
    }

}
