package model.gameobject.dynamicobject.bullet;


import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;

public class BulletFactoryImpl implements BulletFactory {

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

    /**
     * @return characterBullet.
     */
    @Override
    public Bullet createCharacterBullet(final Point2D initialPosition, final  Vector2D direction, final int bonusDamage, final int bonusBulletSpeed) {
        return new BulletImpl(CHARACTER_BULLET_SPEED + bonusBulletSpeed, initialPosition, direction, GameObjectType.CHARACTER_BULLET, bonusDamage + CHARACTER_BULLET_DAMAGE);
    }
    /**
     * @return skeletonBullet.
     */
    @Override
    public Bullet createSkeletonBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(SKELETON_BULLET_SPEED, initialPosition, direction, GameObjectType.SKELETON_BULLET, SKELETON_BULLET_DAMAGE);
    }
    /**
     * @return soulBullet.
     */
    @Override
    public Bullet createSoulBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(SOUL_BULLET_SPEED, initialPosition, direction, GameObjectType.SOUL_BULLET, SOUL_BULLET_DAMAGE);
    }
    /**
     * @return sproutBullet.
     */
    @Override
    public Bullet createSproutBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(SPROUT_BULLET_SPEED, initialPosition, direction, GameObjectType.SPROUT_BULLET, SPROUT_BULLET_DAMAGE);
    }
    /**
     * @return bossBullet.
     */
    @Override
    public Bullet createBossBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(BOSS_BULLET_SPEED, initialPosition, direction, GameObjectType.BOSS_BULLET, BOSS_BULLET_DAMAGE);
    }
}
