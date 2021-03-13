package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.room.Room;
import model.room.RoomBuilder;
import model.room.RoomBuilderImpl;
import model.room.RoomManager;
import model.room.RoomManagerImpl;

public class TestRoom {
    private RoomManager roomManager;
    private RoomBuilder roomBuilder;
    private Room room;

    @org.junit.Before
    public void init() {
        roomManager = new RoomManagerImpl();
        roomBuilder = new RoomBuilderImpl();
    }

    @org.junit.Test
    public void testDoors() {
        room = roomBuilder.initialize(roomManager)
                          .addEnemy()
                          .build();
        assertFalse(room.isDoorOpen());

        roomBuilder = new RoomBuilderImpl();
        room = roomBuilder.initialize(roomManager)
                          .addBoss()
                          .build();
        assertFalse(room.isDoorOpen());

        roomBuilder = new RoomBuilderImpl();
        room = roomBuilder.initialize(roomManager)
                          .build();
        assertTrue(room.isDoorOpen());
    }
}
