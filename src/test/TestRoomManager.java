package test;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.Random;

import model.common.CardinalPoint;
import model.room.RoomManager;
import model.room.RoomManagerImpl;
import model.room.Rooms;

/**
 * test the RoomManager.
 */
public class TestRoomManager {
    private RoomManager roomManager;

    @org.junit.Before
    public void init() {
        roomManager = new RoomManagerImpl();
    }

    /**
     * check if the character is present in the RoomManager.
     */
    @org.junit.Test
    public void testCharacter() {
        assertNotNull(roomManager.getMainCharacter());
    }

    /**
     * check if the actualRoom is not null.
     */
    @org.junit.Test
    public void testActualRoom() {
        assertNotNull(roomManager.getCurrentRoom());
    }

    /**
     * test the change of a room. What we expect is that the new room is not null.
     */
    @org.junit.Test
    public void testChangeRoom() {
        final CardinalPoint newRoomPosition = new LinkedList<>(roomManager.getCurrentRoom().getDoors()).get(0);
        roomManager.changeRoom(newRoomPosition);
        assertNotNull(roomManager.getCurrentRoom());
    }

    /**
     * explore all the rooms of the RoomManager and check that all the rooms are not null.
     */
    @org.junit.Test
    public void testFloorExploration() {
        final Random rnd = new Random();
        while (roomManager.getVisitedRooms() != Rooms.NUMBER_OF_ROOMS) {
            final LinkedList<CardinalPoint> doorsPos = new LinkedList<>(roomManager.getCurrentRoom().getDoors());
            final CardinalPoint newRoomPosition = doorsPos.get(rnd.nextInt(doorsPos.size()));
            roomManager.changeRoom(newRoomPosition);
            assertNotNull(roomManager.getCurrentRoom());
        }
    }
}
