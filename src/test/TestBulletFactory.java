package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.gameobject.dynamicobject.bullet.Bullet;
import model.gameobject.dynamicobject.bullet.BulletFactory;
import model.gameobject.dynamicobject.bullet.BulletFactoryImpl;
import model.gameobject.dynamicobject.bullet.BulletImpl;
import model.room.Room;
import model.room.RoomImpl;
import model.room.RoomManagerImpl;
import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;


public class TestBulletFactory {

    private BulletFactory bulletFactory;
    /**
     * constant for speed of each bullet.
     */
    private static final int CHARACTER_BULLET_SPEED = 350;
    private static final int SKELETON_BULLET_SPEED = 200;
    private static final int SOUL_BULLET_SPEED = 200;
    private static final int SPROUT_BULLET_SPEED = 300;
    private static final int BOSS_BULLET_SPEED = 300;
    /**
     * constant for damage of each bullet.
     */
    private static final int CHARACTER_BULLET_DAMAGE = 10;
    private static final int SKELETON_BULLET_DAMAGE = 15;
    private static final int SOUL_BULLET_DAMAGE = 10;
    private static final int SPROUT_BULLET_DAMAGE = 20;
    private static final int BOSS_BULLET_DAMAGE = 30;

    @org.junit.Before
    public void initFactory() {
        this.bulletFactory = new BulletFactoryImpl();
    }

    @org.junit.Test
    public void testCharacterBullet() {
        final Vector2D direction = new Vector2D(0, 1);
        final Point2D position = new Point2D(0, 0);
        int bonusDamage = 0;
        int bonusBulletSpeed = 0;

        Bullet bullet = this.bulletFactory.createCharacterBullet(position, direction, bonusDamage, bonusBulletSpeed);
        assertEquals(bullet.getDamage(), CHARACTER_BULLET_DAMAGE);
        assertEquals(bullet.getSpeed(), CHARACTER_BULLET_SPEED);
        assertEquals(bullet.getDirection(), direction);
        assertEquals(bullet.getPosition(), position);
        assertEquals(bullet.getGameObjectType(), GameObjectType.CHARACTER_BULLET);
        bonusDamage = 10;
        bonusBulletSpeed = 10;
        bullet.setSpeed(CHARACTER_BULLET_SPEED + bonusBulletSpeed);
        bullet.setDamage(CHARACTER_BULLET_DAMAGE + bonusBulletSpeed);
        assertEquals(bullet.getSpeed(), CHARACTER_BULLET_SPEED + bonusBulletSpeed);
        assertEquals(bullet.getDamage(), CHARACTER_BULLET_DAMAGE + bonusDamage);

        bullet = this.bulletFactory.createCharacterBullet(position, direction, bonusDamage, bonusBulletSpeed);
        assertEquals(bullet.getDamage(), CHARACTER_BULLET_DAMAGE + bonusDamage);
        final BoundingBox bb = new BoundingBox(new Point2D(0, 0), 0, 0);
        bullet.setBoundingBox(bb);
        assertEquals(bullet.getBoundingBox(), bb);
        final Room room = new RoomImpl(new RoomManagerImpl());
        room.addDynamicObject(bullet);
        bullet.setRoom(room); //inserisco il bullet nella stanza 
        assertFalse(room.getCurrentGameObjects().isEmpty());
        assertTrue(room.getCurrentGameObjects().contains(bullet));
    }

    @org.junit.Test
    public void testSkeletonBullet() {
        final Vector2D direction = new Vector2D(0, 1);
        final Point2D position = new Point2D(0, 0);
        Bullet bullet = this.bulletFactory.createSkeletonBullet(position, direction);
        bullet.setSpeed(SKELETON_BULLET_SPEED);
        assertEquals(bullet.getDamage(), SKELETON_BULLET_DAMAGE);
        assertEquals(bullet.getSpeed(), SKELETON_BULLET_SPEED);
        assertEquals(bullet.getDirection(), direction);
        assertEquals(bullet.getPosition(), position);
        assertEquals(bullet.getGameObjectType(), GameObjectType.SKELETON_BULLET);
        bullet.setSpeed(SKELETON_BULLET_SPEED + 5);
        assertEquals(bullet.getSpeed(), SKELETON_BULLET_SPEED + 5);
        bullet = this.bulletFactory.createSkeletonBullet(position, direction);
        assertEquals(bullet.getDamage(), SKELETON_BULLET_DAMAGE);
        final BoundingBox bb = new BoundingBox(new Point2D(0, 0), 0, 0);
        bullet.setBoundingBox(bb);
        assertEquals(bullet.getBoundingBox(), bb);
        final Room room = new RoomImpl(new RoomManagerImpl());
        room.addDynamicObject(bullet);
        bullet.setRoom(room); //inserisco il bullet nella stanza 
        assertFalse(room.getCurrentGameObjects().isEmpty());
    }

    @org.junit.Test
    public void testSoulBullet() {
        final Vector2D direction = new Vector2D(0, 1);
        final Point2D position = new Point2D(0, 0);
        Bullet bullet = this.bulletFactory.createSoulBullet(position, direction);
        bullet.setSpeed(SOUL_BULLET_SPEED);
        assertEquals(bullet.getDamage(), SOUL_BULLET_DAMAGE);
        assertEquals(bullet.getSpeed(), SOUL_BULLET_SPEED);
        assertEquals(bullet.getDirection(), direction);
        assertEquals(bullet.getPosition(), position);
        assertEquals(bullet.getGameObjectType(), GameObjectType.SOUL_BULLET);
        bullet.setSpeed(SOUL_BULLET_SPEED + 5);
        assertEquals(bullet.getSpeed(), SOUL_BULLET_SPEED + 5);
        bullet = this.bulletFactory.createSoulBullet(position, direction);
        assertEquals(bullet.getDamage(), SOUL_BULLET_DAMAGE);
        final BoundingBox bb = new BoundingBox(new Point2D(0, 0), 0, 0);
        bullet.setBoundingBox(bb);
        assertEquals(bullet.getBoundingBox(), bb);
        final Room room = new RoomImpl(new RoomManagerImpl());
        room.addDynamicObject(bullet);
        bullet.setRoom(room); //inserisco il bullet nella stanza 
        assertFalse(room.getCurrentGameObjects().isEmpty());
    }

    @org.junit.Test
    public void testSproutBullet() {
        final Vector2D direction = new Vector2D(0, 1);
        final Point2D position = new Point2D(0, 0);
        Bullet bullet = this.bulletFactory.createSproutBullet(position, direction);
        bullet.setSpeed(SPROUT_BULLET_SPEED);
        assertEquals(bullet.getDamage(), SPROUT_BULLET_DAMAGE);
        assertEquals(bullet.getSpeed(), SPROUT_BULLET_SPEED);
        assertEquals(bullet.getDirection(), direction);
        assertEquals(bullet.getPosition(), position);
        assertEquals(bullet.getGameObjectType(), GameObjectType.SPROUT_BULLET);
        bullet.setSpeed(SPROUT_BULLET_SPEED + 5);
        assertEquals(bullet.getSpeed(), SPROUT_BULLET_SPEED + 5);
        bullet = this.bulletFactory.createSproutBullet(position, direction);
        assertEquals(bullet.getDamage(), SPROUT_BULLET_DAMAGE);
        final BoundingBox bb = new BoundingBox(new Point2D(0, 0), 0, 0);
        bullet.setBoundingBox(bb);
        assertEquals(bullet.getBoundingBox(), bb);
        final Room room = new RoomImpl(new RoomManagerImpl());
        room.addDynamicObject(bullet);
        bullet.setRoom(room); //inserisco il bullet nella stanza 
        assertFalse(room.getCurrentGameObjects().isEmpty());
    }

    @org.junit.Test
    public void testBossBullet() {
        final Vector2D direction = new Vector2D(0, 1);
        final Point2D position = new Point2D(0, 0);
        Bullet bullet = this.bulletFactory.createBossBullet(position, direction);
        bullet.setSpeed(BOSS_BULLET_SPEED);
        assertEquals(bullet.getDamage(), BOSS_BULLET_DAMAGE);
        assertEquals(bullet.getSpeed(), BOSS_BULLET_SPEED);
        assertEquals(bullet.getDirection(), direction);
        assertEquals(bullet.getPosition(), position);
        assertEquals(bullet.getGameObjectType(), GameObjectType.BOSS_BULLET);
        bullet.setSpeed(BOSS_BULLET_SPEED + 5);
        assertEquals(bullet.getSpeed(), BOSS_BULLET_SPEED + 5);
        bullet = this.bulletFactory.createBossBullet(position, direction);
        assertEquals(bullet.getDamage(), BOSS_BULLET_DAMAGE);
        final BoundingBox bb = new BoundingBox(new Point2D(0, 0), 0, 0);
        bullet.setBoundingBox(bb);
        assertEquals(bullet.getBoundingBox(), bb);
        final Room room = new RoomImpl(new RoomManagerImpl());
        room.addDynamicObject(bullet);
        bullet.setRoom(room); //inserisco il bullet nella stanza 
        assertFalse(room.getCurrentGameObjects().isEmpty());
    }
}
