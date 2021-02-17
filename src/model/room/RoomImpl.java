package model.room;

import java.util.LinkedList;
import java.util.List;

import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.simpleobject.SimpleObject;
import mvc.Model;

public class RoomImpl implements Room {

    private final List<SimpleObject> simpleObjects = new LinkedList<>();
    private final List<DinamicObject> dinamicObjects = new LinkedList<>();
    private final RoomManager roomManager;
    private final Model model;


    public RoomImpl(final RoomManager roomManager, final Model model) {
        super();
        this.roomManager = roomManager;
        this.model = model;
    }

    /**
     * 
     */
    public void update() {
        for (final DinamicObject obj : dinamicObjects) {
            obj.updateState();
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

}
