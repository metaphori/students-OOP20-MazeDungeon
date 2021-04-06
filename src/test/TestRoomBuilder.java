package test;

import model.common.Point2D;
import model.gameobject.simpleobject.Coin;
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
        roomBuilder.addRandomObstacle()
                   .addBoss();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testBossAndEnemy() {
        roomBuilder.addRandomEnemy()
                   .addBoss();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testObstacleWithBoss() {
        roomBuilder.addBoss()
                   .addRandomObstacle();
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testEnemyWithBoss() {
        roomBuilder.addBoss()
                   .addRandomEnemy();
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testInitParameter() {
        final RoomBuilder testRoomBuilder = new RoomBuilderImpl(null);
        testRoomBuilder.build();
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testDoorsParameter() {
        roomBuilder.addDoors(null);
    }

    @org.junit.Test (expected = IllegalStateException.class)
    public void testBuildTwice() {
        roomBuilder.addRandomObstacle();
        final Room room1 = roomBuilder.build();
        room1.addSimpleObject(new Coin(new Point2D(0, 0)));
        final Room room2 = roomBuilder.build();
        room2.addSimpleObject(new Coin(new Point2D(0, 0)));
    }

}
