package model.room;

import java.util.HashMap;
import java.util.Map;

import model.common.Point2D;

public class RoomManagerImpl implements RoomManager {

    private static final int NUMBER_OF_ROOMS = 10;

    private final Map<Point2D, Room> rooms = new HashMap<>();
    private Room actualRoom;

    public RoomManagerImpl() {
        this.createFloor();
    }

    /**
     * 
     */
    public void update() {
        actualRoom.update();
    }

    private void createFloor() {
        
    }

}
