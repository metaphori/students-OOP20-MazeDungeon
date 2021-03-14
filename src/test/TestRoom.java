package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.common.Point2D;
import model.gameobject.dynamicobject.DynamicObject;
import model.gameobject.dynamicobject.enemy.EnemyFactoryImpl;
import model.gameobject.simpleobject.Coin;
import model.gameobject.simpleobject.SimpleObject;
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

        room.clean();
        assertTrue(room.isDoorOpen());

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

    @org.junit.Test
    public void testAddGameObject() {
        room = roomBuilder.initialize(roomManager)
                          .build();
        final SimpleObject gameObject = new Coin(new Point2D(500, 500));
        assertFalse(room.getCurrentGameObjects().contains(gameObject));
        room.addSimpleObject(gameObject);
        assertTrue(room.getCurrentGameObjects().contains(gameObject));
    }

    @org.junit.Test
    public void testBoss() {
        room = roomBuilder.initialize(roomManager)
                          .build();
        final DynamicObject boss = new EnemyFactoryImpl().createBoss(new Point2D(500, 500));
        assertTrue(room.getBossID().isEmpty());
        room.addDynamicObject(boss);
        assertTrue(room.getBossID().isPresent());
    }
}
