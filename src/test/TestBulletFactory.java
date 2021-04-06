package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.gameobject.dynamicobject.bullet.Bullet;
import model.gameobject.dynamicobject.bullet.BulletFactory;
import model.gameobject.dynamicobject.bullet.BulletFactoryImpl;
import model.room.Room;
import model.room.RoomBuilderImpl;
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
    private static final int CHARACTER_BULLET_SPEED = 400;
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
        Bullet bullet = this.bulletFactory.createMainCharacterBullet(position, direction, bonusDamage, bonusBulletSpeed);
        assertEquals(CHARACTER_BULLET_DAMAGE, bullet.getDamage());
        assertEquals(CHARACTER_BULLET_SPEED, bullet.getSpeed());
        assertEquals(direction, bullet.getDirection());
        assertEquals(position, bullet.getPosition());
        assertEquals(GameObjectType.MAINCHARACTER_BULLET, bullet.getGameObjectType());
        bonusDamage = 10;
        bonusBulletSpeed = 10;
        bullet.setSpeed(CHARACTER_BULLET_SPEED + bonusBulletSpeed);
        bullet.setDamage(CHARACTER_BULLET_DAMAGE + bonusBulletSpeed);
        assertEquals(CHARACTER_BULLET_SPEED + bonusBulletSpeed, bullet.getSpeed());
        assertEquals(CHARACTER_BULLET_DAMAGE + bonusDamage, bullet.getDamage());
        bullet = this.bulletFactory.createMainCharacterBullet(position, direction, bonusDamage, bonusBulletSpeed);
        assertEquals(CHARACTER_BULLET_DAMAGE + bonusDamage, bullet.getDamage());
        final BoundingBox bb = new BoundingBox(new Point2D(0, 0), 0, 0);
        bullet.setBoundingBox(bb);
        assertEquals(bb, bullet.getBoundingBox());
        final Room room = new RoomBuilderImpl(new RoomManagerImpl()).build();
        room.addDynamicObject(bullet);
        bullet.setRoom(room);
        assertFalse(room.getCurrentGameObjects().isEmpty());
        assertTrue(room.getCurrentGameObjects().contains(bullet));
    }

    @org.junit.Test
    public void testSkeletonBullet() {
        final Vector2D direction = new Vector2D(0, 1);
        final Point2D position = new Point2D(0, 0);
        Bullet bullet = this.bulletFactory.createSkeletonBullet(position, direction);
        bullet.setSpeed(SKELETON_BULLET_SPEED);
        assertEquals(SKELETON_BULLET_DAMAGE, bullet.getDamage());
        assertEquals(SKELETON_BULLET_SPEED, bullet.getSpeed());
        assertEquals(direction, bullet.getDirection());
        assertEquals(position, bullet.getPosition());
        assertEquals(GameObjectType.SKELETON_BULLET, bullet.getGameObjectType());
        bullet.setSpeed(SKELETON_BULLET_SPEED + 10);
        assertEquals(SKELETON_BULLET_SPEED + 10, bullet.getSpeed());
        bullet = this.bulletFactory.createSkeletonBullet(position, direction);
        assertEquals(SKELETON_BULLET_DAMAGE, bullet.getDamage());
        final BoundingBox bb = new BoundingBox(new Point2D(0, 0), 0, 0);
        bullet.setBoundingBox(bb);
        assertEquals(bb, bullet.getBoundingBox());
        final Room room = new RoomBuilderImpl(new RoomManagerImpl()).build();
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
        assertEquals(SOUL_BULLET_DAMAGE, bullet.getDamage());
        assertEquals(SOUL_BULLET_SPEED, bullet.getSpeed());
        assertEquals(direction, bullet.getDirection());
        assertEquals(position, bullet.getPosition());
        assertEquals(GameObjectType.SOUL_BULLET, bullet.getGameObjectType());
        bullet.setSpeed(SOUL_BULLET_SPEED + 10);
        assertEquals(SOUL_BULLET_SPEED + 10, bullet.getSpeed());
        bullet = this.bulletFactory.createSoulBullet(position, direction);
        assertEquals(SOUL_BULLET_DAMAGE, bullet.getDamage());
        final BoundingBox bb = new BoundingBox(new Point2D(0, 0), 0, 0);
        bullet.setBoundingBox(bb);
        assertEquals(bb, bullet.getBoundingBox());
        final Room room = new RoomBuilderImpl(new RoomManagerImpl()).build();
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
        assertEquals(SPROUT_BULLET_DAMAGE, bullet.getDamage());
        assertEquals(SPROUT_BULLET_SPEED, bullet.getSpeed());
        assertEquals(direction, bullet.getDirection());
        assertEquals(position, bullet.getPosition());
        assertEquals(GameObjectType.SPROUT_BULLET, bullet.getGameObjectType());
        bullet.setSpeed(SPROUT_BULLET_SPEED + 10);
        assertEquals(SPROUT_BULLET_SPEED + 10, bullet.getSpeed());
        bullet = this.bulletFactory.createSproutBullet(position, direction);
        assertEquals(SPROUT_BULLET_DAMAGE, bullet.getDamage());
        final BoundingBox bb = new BoundingBox(new Point2D(0, 0), 0, 0);
        bullet.setBoundingBox(bb);
        assertEquals(bb, bullet.getBoundingBox());
        final Room room = new RoomBuilderImpl(new RoomManagerImpl()).build();
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
        assertEquals(BOSS_BULLET_DAMAGE, bullet.getDamage());
        assertEquals(BOSS_BULLET_SPEED, bullet.getSpeed());
        assertEquals(direction, bullet.getDirection());
        assertEquals(position, bullet.getPosition());
        assertEquals(GameObjectType.BOSS_BULLET, bullet.getGameObjectType());
        bullet.setSpeed(BOSS_BULLET_SPEED + 10);
        assertEquals(BOSS_BULLET_SPEED + 10, bullet.getSpeed());
        bullet = this.bulletFactory.createBossBullet(position, direction);
        assertEquals(BOSS_BULLET_DAMAGE, bullet.getDamage());
        final BoundingBox bb = new BoundingBox(new Point2D(0, 0), 0, 0);
        bullet.setBoundingBox(bb);
        assertEquals(bb, bullet.getBoundingBox());
        final Room room = new RoomBuilderImpl(new RoomManagerImpl()).build();
        room.addDynamicObject(bullet);
        bullet.setRoom(room); //inserisco il bullet nella stanza 
        assertFalse(room.getCurrentGameObjects().isEmpty());
    }
}
