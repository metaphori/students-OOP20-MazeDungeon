package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.common.BoundingBox;
import model.common.Point2D;
import model.gameobject.dynamicobject.enemy.Enemy;
import model.gameobject.dynamicobject.enemy.EnemyFactory;
import model.gameobject.dynamicobject.enemy.EnemyFactoryImpl;
import model.room.Room;
import model.room.RoomImpl;
import model.room.RoomManagerImpl;

public class TestEnemyFactory {
    private static final int SOUL_LIFE = 50;
    private static final int SPROUT_LIFE = 50;
    private static final int SKELETON_LIFE = 75;
    private static final int BOSS_LIFE = 300;

    private static final int SOUL_SPEED = 90;
    private static final int SPROUT_SPEED = 50;
    private static final int SKELETON_SPEED = 90;
    private static final int BOSS_SPEED = 150;
    private EnemyFactory ef;

    @org.junit.Before
    public void initFactory() {
        ef = new EnemyFactoryImpl();
    }

    @org.junit.Test
    public void testSprout() {
        final Enemy sprout = ef.createSprout(new Point2D(100, 100));
        assertEquals(sprout.getPosition(), new Point2D(100, 100));
        assertEquals(sprout.getLife(), SPROUT_LIFE);
        assertEquals(sprout.getSpeed(), SPROUT_SPEED);
        final Room room = new RoomImpl(new RoomManagerImpl());
        sprout.setBoundingBox(new BoundingBox(new Point2D(100, 100), 100, 100));
        sprout.setRoom(room);
        sprout.shoot();
        assertFalse(room.getCurrentGameObjects().isEmpty());
        sprout.takesDamage(10);
        assertEquals(SPROUT_LIFE - 10, sprout.getLife());
        sprout.takesDamage(SPROUT_LIFE);
        assertFalse(room.getCurrentGameObjects().contains(sprout));
    }

    @org.junit.Test
    public void testSoul() {
        final Enemy soul = ef.createSoul(new Point2D(200, 200));
        assertEquals(soul.getPosition(), new Point2D(200, 200));
        assertEquals(soul.getLife(), SOUL_LIFE);
        assertEquals(soul.getSpeed(), SOUL_SPEED);
        final Room room = new RoomImpl(new RoomManagerImpl());
        soul.setBoundingBox(new BoundingBox(new Point2D(100, 100), 100, 100));
        soul.setRoom(room);
        soul.shoot();
        assertFalse(room.getCurrentGameObjects().isEmpty());
        soul.takesDamage(10);
        assertEquals(SOUL_LIFE - 10, soul.getLife());
        soul.takesDamage(SOUL_LIFE);
        assertFalse(room.getCurrentGameObjects().contains(soul));
    }

    @org.junit.Test
    public void testSkeleton() {
        final Enemy skeleton = ef.createSkeletonSeeker(new Point2D(300, 300));
        assertEquals(skeleton.getPosition(), new Point2D(300, 300));
        assertEquals(skeleton.getLife(), SKELETON_LIFE);
        assertEquals(skeleton.getSpeed(), SKELETON_SPEED);
        final Room room = new RoomImpl(new RoomManagerImpl());
        skeleton.setBoundingBox(new BoundingBox(new Point2D(100, 100), 100, 100));
        skeleton.setRoom(room);
        skeleton.shoot();
        assertFalse(room.getCurrentGameObjects().isEmpty());
        skeleton.takesDamage(10);
        assertEquals(SKELETON_LIFE - 10, skeleton.getLife());
        skeleton.takesDamage(SKELETON_LIFE);
        assertFalse(room.getCurrentGameObjects().contains(skeleton));
    }

}
