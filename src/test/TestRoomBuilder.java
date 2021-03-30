package test;

import model.room.Room;
import model.room.RoomBuilder;
import model.room.RoomBuilderImpl;
import model.room.RoomManager;
import model.room.RoomManagerImpl;

/**
 * 
 */
public class TestRoomBuilder {

    private RoomBuilder roomBuilder;

    @org.junit.Before
    public void initBuilder() {
        final RoomManager roomManager = new RoomManagerImpl();
        roomBuilder = new RoomBuilderImpl(roomManager);
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testBossAndObstacle() {
        roomBuilder.addObstacle()
                   .addBoss();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testBossAndEnemy() {
        roomBuilder.addEnemy()
                   .addBoss();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testObstacleWithBoss() {
        roomBuilder.addBoss()
                   .addObstacle();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testEnemyWithBoss() {
        roomBuilder.addBoss()
                   .addEnemy();
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testInitParameter() {
        final RoomBuilder roomBuilder = new RoomBuilderImpl(null);
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testDoorsParameter() {
        roomBuilder.addDoors(null);
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testBuildTwice() {
        roomBuilder.addObstacle();
        final Room room1 = roomBuilder.build();
        final Room room2 = roomBuilder.build();
    }

}
