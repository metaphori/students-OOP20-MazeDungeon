package model.gameobject.dynamicobject.bullet;


import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;

/**
 * The implementation of Bullet Factory.
 *
 */
public class BulletFactoryImpl implements BulletFactory {

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

    /**
     * @return {@inheritDoc}
     */
    @Override
    public Bullet createCharacterBullet(final Point2D initialPosition, final  Vector2D direction, final int bonusDamage, final int bonusBulletSpeed) {
        return new BulletImpl(initialPosition, direction, GameObjectType.CHARACTER_BULLET, bonusDamage + CHARACTER_BULLET_DAMAGE, CHARACTER_BULLET_SPEED + bonusBulletSpeed);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet createSkeletonBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(initialPosition, direction, GameObjectType.SKELETON_BULLET, SKELETON_BULLET_DAMAGE, SKELETON_BULLET_SPEED);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet createSoulBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(initialPosition, direction, GameObjectType.SOUL_BULLET, SOUL_BULLET_DAMAGE, SOUL_BULLET_SPEED);
    }
    /**
     * @return {@inheritDoc}
     */
    @Override
    public Bullet createSproutBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(initialPosition, direction, GameObjectType.SPROUT_BULLET, SPROUT_BULLET_DAMAGE, SPROUT_BULLET_SPEED);
    }
    /**
     * @return {@inheritDoc}
     */
    @Override
    public Bullet createBossBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(initialPosition, direction, GameObjectType.BOSS_BULLET, BOSS_BULLET_DAMAGE, BOSS_BULLET_SPEED);
    }
}
