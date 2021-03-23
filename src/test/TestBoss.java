package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import model.common.BoundingBox;
import model.common.Point2D;
import model.gameobject.dynamicobject.enemy.Enemy;
import model.gameobject.dynamicobject.enemy.EnemyFactory;
import model.gameobject.dynamicobject.enemy.EnemyFactoryImpl;
import model.room.Room;
import model.room.RoomImpl;
import model.room.RoomManagerImpl;

public class TestBoss {
    private static final int BOSS_LIFE = 400;
    private static final int BOSS_SPEED = 160;
    private EnemyFactory enemy;

    @org.junit.Before
    public void initEnemy() {
        enemy = new EnemyFactoryImpl();
    }

    @org.junit.Test
    public void testBoss() {
        final Enemy boss = enemy.createBoss(new Point2D(100, 100));
        assertEquals(boss.getPosition(), new Point2D(100 - 1, 100));
        assertEquals((int) boss.getLife(), (int) BOSS_LIFE);
        assertEquals(boss.getSpeed(), BOSS_SPEED);
        final Room room = new RoomImpl(new RoomManagerImpl());
        boss.setBoundingBox(new BoundingBox(new Point2D(100, 100), 100, 100));
        room.addDynamicObject(boss);
        boss.tryToShoot();
        assertFalse(room.getCurrentGameObjects().isEmpty());
        boss.takesDamage(10);
        assertEquals((int) BOSS_LIFE - 10, (int) boss.getLife());
        boss.takesDamage(BOSS_LIFE);
        assertFalse(room.getCurrentGameObjects().contains(boss));
    }
}
