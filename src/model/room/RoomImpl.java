package model.room;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.staticobject.Door;
import mvc.Model;

public class RoomImpl {
    private final Map<Integer, AbstractDinamicObject> dinamicObjects = new HashMap<>();
    private final Map<Integer, StaticObject> staticObjects = new HashMap<>(); // TODO da cambiare in mappa di staticObject
    private final List<Door> doors = new LinkedList<>();
    private final Optional<Character> character = Optional.empty();
    private final RoomManager roomManager;
    private final Model model;

    public RoomImpl(final Model model, final RoomManager roomManager) {
        this.roomManager = roomManager;
        this.model = model;
    }

    /**
     * 
     */
    public void update() {
        for (final AbstractDinamicObject dinamicObject : dinamicObjects.values()) {
            dinamicObject.updateState();
        }
    }

    /**
     * 
     * @param dinamicObject
     */
    public void addDinamicObject(final AbstractDinamicObject dinamicObject) {
        dinamicObjects.put(dinamicObject.getID(), dinamicObject);
    }

    /**
     * 
     * @param gameObject
     */
    public void addStaticObject(final StaticObject gameObject) {
        staticObjects.put(gameObject.getID(), gameObject);
    }
}
