package model.gameobject.dinamicobject.enemy;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.bullet.Bullet;
import model.gameobject.dinamicobject.bullet.BulletFactory;
import model.gameobject.dinamicobject.bullet.BulletFactoryImpl;
import model.room.Room;

public abstract class AbstractEnemy extends AbstractDinamicObject implements Enemy {

    private long lastShootTime = System.currentTimeMillis();
    private double life;
    private final BulletFactory bulletFactory;

    public AbstractEnemy(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final Room room) {
        super(id, speed, position, direction, gameObjectType, room);
        this.bulletFactory = new BulletFactoryImpl(room.getRoomManager().getIdIterator());
    }

    /**
     * 
     */
    @Override
    public double getLife() {
        return this.life;
    }

    /**
     * @return the factory of bullet
     */
    protected BulletFactory getBulletFactory() {
        return this.bulletFactory;
    }

    @Override
    public void takesDamage(final int damage) {
        //TODO
    }

    @Override
    public void notifyDropCoin() {
      //TODO
    }

    /**
     * 
     */
    @Override
    public void collideWith(final GameObject obj2) {
        switch (obj2.getGameObjectType().getCollisionType()) {
        case OBSTACLE:
        case ENTITY:
            this.setPosition(this.getLastPosition());
            this.changeRoutine();
            break;


        default:
            break;
        }
    }

    /**
     * 
     * @return .
     */
    protected boolean canShoot() {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastShootTime > 1000) {
            this.lastShootTime = currentTime;
            return true;
        }
        return false;
    }

    protected abstract void changeRoutine();

    @Override
    public abstract void shoot();

}
