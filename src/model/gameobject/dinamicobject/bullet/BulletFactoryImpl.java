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
    private final int CHARACTER_BULLET_SPEED = 100;
    private final int SKELETON_BULLET_SPEED = 100;
    private final int SOUL_BULLET_SPEED = 100;
    private final int SPROUT_BULLET_SPEED = 100;
    private final int BOSS_BULLET_SPEED = 100;
    private final int OLDGUARDIAN_BULLET_SPEED = 100;
    /**
     * constant for damage of each bullet.
     */
    private final int CHARACTER_BULLET_DAMAGE = 100;
    private final int SKELETON_BULLET_DAMAGE = 100;
    private final int SOUL_BULLET_DAMAGE = 100;
    private final int SPROUT_BULLET_DAMAGE = 100;
    private final int BOSS_BULLET_DAMAGE = 100;
    private final int OLDGUARDIAN_BULLET_DAMAGE = 100;

    /**
     * fields.
     **/
    private final IdIterator idIterator;

    /**
     * 
     * @param idIterator
     * @param  
     */
    public BulletFactoryImpl(final IdIterator idIterator) {
        this.idIterator = idIterator;
    }

    @Override
    public Bullet createCharacterBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final  Vector2D direction, Room room) {
        return new BulletImpl(this.idIterator.next(), this.CHARACTER_BULLET_SPEED, initialPosition, direction, gameObjectType, this.CHARACTER_BULLET_DAMAGE, room);
    }
    @Override
    public Bullet createSkeletonBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final Vector2D direction, Room room) {
        return new BulletImpl(this.idIterator.next(), this.SKELETON_BULLET_SPEED, initialPosition, direction, gameObjectType, this.SKELETON_BULLET_DAMAGE, room);
    }
    @Override
    public Bullet createSoulBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final Vector2D direction, Room room) {
        return new BulletImpl(this.idIterator.next(), this.SOUL_BULLET_SPEED, initialPosition, direction, gameObjectType, this.SOUL_BULLET_DAMAGE, room);
    }
    @Override
    public Bullet createSproutBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final Vector2D direction, Room room) {
        return new BulletImpl(this.idIterator.next(), this.SPROUT_BULLET_SPEED, initialPosition, direction, gameObjectType, this.SPROUT_BULLET_DAMAGE,room);
    }
    @Override
    public Bullet createBossBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final Vector2D direction, Room room) {
        return new BulletImpl(this.idIterator.next(), this.BOSS_BULLET_SPEED, initialPosition, direction, gameObjectType, this.BOSS_BULLET_DAMAGE, room);
    }
    @Override
    public Bullet createOldGuardianBullet(final GameObjectType gameObjectType, final Point2D initialPosition, final Vector2D direction, Room room) {
        return new BulletImpl(this.idIterator.next(), this.OLDGUARDIAN_BULLET_SPEED, initialPosition, direction, gameObjectType, this.OLDGUARDIAN_BULLET_DAMAGE, room);
    }
}
