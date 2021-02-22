package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.simpleobject.SimpleObjectImpl;

public class RoomImpl implements Room {

    private final List<SimpleObject> simpleObjects = new LinkedList<>();
    private final List<DinamicObject> dinamicObjects = new LinkedList<>();
    private final RoomManager roomManager;

    public RoomImpl(final RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    //TODO va fatto bene. cosi è solo temporaneo (solo per test)
    /*NOTA: I MURI POSSONO ESSERE CARICATI DIRETTAMENTE DALLA FACTORY CHE SI OCCUPA
     * DI CARICARE GLI OSTACOLI. OGNI STANZA INDIPENDENTEMENTE DALLA DISPOSIZIONE DEGLI 
     * OSTACOLI HA GLI STESSI MURI
     */

    /**
     * @param elapsed the time passed
     */
    @Override
    public void update(final double elapsed) {
        for (final DinamicObject obj : new LinkedList<>(this.dinamicObjects)) {
            obj.updateState(elapsed);
        }
        this.checkCollisions();
    }

    /**
     * 
     * @param obj
     */
    public void addDinamicObject(final DinamicObject obj) {
      //  obj.setRoom(this);
        dinamicObjects.add(obj);
    }

    /**
     * 
     * @param obj
     */
    public void addSimpleObject(final SimpleObject obj) {
       // obj.setRoom(this);
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

    private void checkCollisions() {
        for (final DinamicObject obj1 : new LinkedList<>(this.dinamicObjects)) {
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

    @Override
    public RoomManager getRoomManager() {
        return roomManager;
    }

    @Override
    public Optional<Point2D> getCharacterPosition() {
        return dinamicObjects.stream().filter(obj -> obj.getGameObjectType() == GameObjectType.CHARACTER).map(obj -> obj.getPosition()).findAny();
    }

}
