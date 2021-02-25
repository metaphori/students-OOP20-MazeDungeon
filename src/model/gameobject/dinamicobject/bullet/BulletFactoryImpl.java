package model.gameobject.dinamicobject.bullet;


import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.common.Vector2D;
import model.room.Room;

public class BulletFactoryImpl implements BulletFactory {

    /**
     * constant for speed of each bullet.
     */
    private static final int CHARACTER_BULLET_SPEED = 100;
    private static final int SKELETON_BULLET_SPEED = 200;
    private static final int SOUL_BULLET_SPEED = 200;
    private static final int SPROUT_BULLET_SPEED = 300;
    private static final int BOSS_BULLET_SPEED = 100;
    private static final int OLDGUARDIAN_BULLET_SPEED = 100;
    /**
     * constant for damage of each bullet.
     */
    private static final double CHARACTER_BULLET_DAMAGE = 0.5;
    private static final double SKELETON_BULLET_DAMAGE = 0.25;
    private static final double SOUL_BULLET_DAMAGE = 0.5;
    private static final double SPROUT_BULLET_DAMAGE = 0.1;
    private static final double BOSS_BULLET_DAMAGE = 100;
    private static final double OLDGUARDIAN_BULLET_DAMAGE = 100;

    /**
     * fields.
     **/

    @Override
    public Bullet createCharacterBullet(final Point2D initialPosition, final  Vector2D direction) {
        return new BulletImpl(CHARACTER_BULLET_SPEED, initialPosition, direction, GameObjectType.CHARACTER_BULLET, CHARACTER_BULLET_DAMAGE);
    }
    @Override
    public Bullet createSkeletonBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(SKELETON_BULLET_SPEED, initialPosition, direction, GameObjectType.SKELETON_BULLET, SKELETON_BULLET_DAMAGE);
    }
    @Override
    public Bullet createSoulBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(SOUL_BULLET_SPEED, initialPosition, direction, GameObjectType.SOUL_BULLET, SOUL_BULLET_DAMAGE);
    }
    @Override
    public Bullet createSproutBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(SPROUT_BULLET_SPEED, initialPosition, direction, GameObjectType.SPROUT_BULLET, SPROUT_BULLET_DAMAGE);
    }
    @Override
    public Bullet createBossBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(BOSS_BULLET_SPEED, initialPosition, direction, GameObjectType.BOSS_BULLET, BOSS_BULLET_DAMAGE);
    }
    @Override
    public Bullet createOldGuardianBullet(final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(OLDGUARDIAN_BULLET_SPEED, initialPosition, direction, GameObjectType.OLDGUARDIAN_BULLET, OLDGUARDIAN_BULLET_DAMAGE);
    }
}
