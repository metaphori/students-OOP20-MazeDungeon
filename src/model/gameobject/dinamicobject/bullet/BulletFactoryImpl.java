package model.gameobject.dinamicobject.bullet;


import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;

public class BulletFactoryImpl implements BulletFactory {

    /**
     * constant for speed of each bullet
     */
    private final int CHARACTER_BULLET_SPEED = 100;
    private final int SKELETON_BULLET_SPEED = 100;
    private final int SOUL_BULLET_SPEED = 100;
    private final int SPROUT_BULLET_SPEED = 100;
    private final int BOSS_BULLET_SPEED = 100;
    private final int OLDGUARDIAN_BULLET_SPEED = 100;
    /**
     * constant for damage of each bullet
     */
    private final int CHARACTER_BULLET_DAMAGE = 100;
    private final int SKELETON_BULLET_DAMAGE = 100;
    private final int SOUL_BULLET_DAMAGE = 100;
    private final int SPROUT_BULLET_DAMAGE = 100;
    private final int BOSS_BULLET_DAMAGE = 100;
    private final int OLDGUARDIAN_BULLET_DAMAGE = 100;
    /**
     * 
     */

    /*@Override
    public Bullet createBullet(final GameObjectType bulletType, final Point2D initialPosition, final Vector2D direction) {
        
        Bullet bullet;
        switch (bulletType) {
            case CHARACTER_BULLET:
                bullet = new BulletImpl(0, CHARACTER_BULLET_SPEED, initialPosition, direction, bulletType, CHARACTER_BULLET_DAMAGE);
                break;
            case SKELETON_BULLET:
                bullet = new BulletImpl(0, SKELETONSEEKER_BULLET_SPEED, initialPosition, direction, bulletType, SKELETONSEEKER_BULLET_DAMAGE);
                break;
            case SOUL_BULLET:
                bullet = new BulletImpl(0, SOUL_BULLET_SPEED, initialPosition, direction, bulletType, SOUL_BULLET_DAMAGE);
                break;
            case SPROUT_BULLET:
                bullet = new BulletImpl(0, SPROUT_BULLET_SPEED, initialPosition, direction, bulletType, SPROUT_BULLET_DAMAGE);
                break;
            case BOSS_BULLET:
                bullet = new BulletImpl(0, BOSS_BULLET_SPEED, initialPosition, direction, bulletType, BOSS_BULLET_DAMAGE);
                break;
            case OLDGUARDIAN_BULLET:
                bullet = new BulletImpl(0, OLDGUARDIAN_BULLET_SPEED, initialPosition, direction, bulletType, OLDGUARDIAN_BULLET_DAMAGE);
                break;
            default:
                System.out.println("System");
                bullet = new BulletImpl(0, 0, null, null, null, 0);
                break;
        }
        return bullet;
    }*/

    @Override
    public Bullet createCharacterBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final  Vector2D direction) {
        return new BulletImpl(3001, this.CHARACTER_BULLET_SPEED, initialPosition, direction, gameObjectType, this.CHARACTER_BULLET_DAMAGE);
    }
    @Override
    public Bullet createSkeletonBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(3002, this.SKELETON_BULLET_SPEED, initialPosition, direction, gameObjectType, this.SKELETON_BULLET_DAMAGE);
    }
    @Override
    public Bullet createSoulBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(3003, this.SOUL_BULLET_SPEED, initialPosition, direction, gameObjectType, this.SOUL_BULLET_DAMAGE);
    }
    @Override
    public Bullet createSproutBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(3004, this.SPROUT_BULLET_SPEED, initialPosition, direction, gameObjectType, this.SPROUT_BULLET_DAMAGE);
    }
    @Override
    public Bullet createBossBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(3004, this.BOSS_BULLET_SPEED, initialPosition, direction, gameObjectType, this.BOSS_BULLET_DAMAGE);
    }
    @Override
    public Bullet createOldGuardianBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final Vector2D direction) {
        return new BulletImpl(3005, this.OLDGUARDIAN_BULLET_SPEED, initialPosition, direction, gameObjectType, this.OLDGUARDIAN_BULLET_DAMAGE);
    }
}
