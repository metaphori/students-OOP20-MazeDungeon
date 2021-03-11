package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import model.gameobject.dynamicobject.bullet.Bullet;
import model.gameobject.dynamicobject.bullet.BulletFactory;
import model.gameobject.dynamicobject.bullet.BulletFactoryImpl;
import model.gameobject.dynamicobject.bullet.BulletImpl;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;


public class TestBulletFactory {

    private BulletFactory bulletFactory;
    /**
     * constant for speed of each bullet.
     */
    private static final int CHARACTER_BULLET_SPEED = 100;
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
        final int bonusDamage = 10;
        Bullet bullet = this.bulletFactory.createCharacterBullet(position, direction, 0);
        assertEquals(bullet.getDamage(), this.CHARACTER_BULLET_DAMAGE);
        assertEquals(bullet.getSpeed(), this.CHARACTER_BULLET_SPEED);
        assertEquals(bullet.getDirection(), direction);
        assertEquals(bullet.getPosition(), position);
        assertEquals(bullet.getGameObjectType(), GameObjectType.CHARACTER_BULLET);
        bullet = this.bulletFactory.createCharacterBullet(position, direction, bonusDamage);
        assertEquals(bullet.getDamage(), this.CHARACTER_BULLET_DAMAGE + bonusDamage);
    }

    @org.junit.Test
    public void testSkeletonBullet() {

    }

    @org.junit.Test
    public void testSoulBullet() {
       
    }
    
    @org.junit.Test
    public void testSproutBullet() {
       
    }
    
    @org.junit.Test
    public void testBossBullet() {
       
    }
}
