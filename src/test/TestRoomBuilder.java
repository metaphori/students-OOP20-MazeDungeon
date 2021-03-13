package test;


import static org.junit.Assert.assertFalse;

import model.room.RoomBuilder;
import model.room.RoomBuilderImpl;
import model.room.RoomManager;
import model.room.RoomManagerImpl;

public class TestRoomBuilder {

    private RoomBuilder roomBuilder;
    private RoomManager roomManager;

    @org.junit.Before
    public void initBuilder() {
        roomBuilder = new RoomBuilderImpl();
        roomManager = new RoomManagerImpl();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testInitialize() {
        roomBuilder.addBoss();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testBossAndObstacle() {
        roomBuilder.initialize(roomManager)
                   .addObstacle()
                   .addBoss();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testBossAndEnemy() {
        roomBuilder.initialize(roomManager)
                   .addEnemy()
                   .addBoss();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testBuildWithouthInit() {
        roomBuilder.build();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testObstacleWithBoss() {
        roomBuilder.initialize(roomManager)
                   .addBoss()
                   .addObstacle();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testEnemyWithBoss() {
        roomBuilder.initialize(roomManager)
                   .addBoss()
                   .addEnemy();
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testInitParameter() {
        roomBuilder.initialize(null);
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testDoorsParameter() {
        roomBuilder.initialize(roomManager)
                   .addDoors(null);
    }

    @org.junit.Test
    public void testBuildWithInit() {
        boolean thrown = false;
        try {
            roomBuilder.initialize(roomManager)
                       .build();
        } catch (IllegalStateException e) {
          thrown = true;
        }
        assertFalse(thrown);
    }

}
