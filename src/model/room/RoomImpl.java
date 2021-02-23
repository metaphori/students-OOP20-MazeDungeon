package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.dinamicobject.character.CharacterImpl;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.dinamicobject.character.Character;

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
        final List<DinamicObject> temp = new LinkedList<>(List.copyOf(this.dinamicObjects));
        temp.iterator().forEachRemaining(obj -> {
            obj.updateState(elapsed);
        });
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
     * TODO METODO BASTARDO
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

}
