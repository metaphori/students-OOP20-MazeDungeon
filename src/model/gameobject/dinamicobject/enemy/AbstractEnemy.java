package model.gameobject.dinamicobject.enemy;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.dinamicobject.bullet.BulletFactory;
import model.gameobject.dinamicobject.bullet.BulletFactoryImpl;
import model.gameobject.simpleobject.Coin;
import model.room.Room;

public abstract class AbstractEnemy extends AbstractDinamicObject implements Enemy {

    private long lastShootTime = System.currentTimeMillis();
    private double life;

    public AbstractEnemy(final double life, final int speed, final Point2D position, final GameObjectType gameObjectType) {
        super(speed, position, gameObjectType);
        this.life = life;
        this.changeRoutine();
    }

    /**
     * 
     */
    @Override
    public double getLife() {
        return this.life;
    }

    /**
     * 
     */
    @Override
    public void takesDamage(final double damage) {
        this.life = this.life - damage;
        System.out.println(this.getID() + ") " + this.getGameObjectType() + " Life: " + this.getLife());
        if (this.life <= 0) {
            this.getRoom().addSimpleObject(new Coin(this.getPosition().sum(new Vector2D(this.getBoundingBox().getWidth() / 2,
                                                    this.getBoundingBox().getHeight() / 2))));
            this.getRoom().deleteGameObject(this);
        }
    }

    /**
     * 
     */
    @Override
    public void collideWith(final GameObject obj2) {
        switch (obj2.getGameObjectType().getCollisionType()) {
        case OBSTACLE:
            final int footHeight = 15;
            final Point2D footColliderUL = new Point2D(this.getBoundingBox().getULCorner().getX(), this.getBoundingBox().getBRCorner().getY() - footHeight);
            final BoundingBox footCollider = new BoundingBox(footColliderUL, this.getBoundingBox().getWidth(), footHeight);
            if (footCollider.intersectWith(obj2.getBoundingBox())) {
                this.setPosition(this.getLastPosition());
                this.changeRoutine();
            }
            break;
        case ENTITY:
            final AbstractDinamicObject dinamicObject = (AbstractDinamicObject) obj2;
            dinamicObject.setPosition(dinamicObject.getLastPosition());
            this.changeRoutine();
            break;
        default:
            break;
        }
    }

    /**
     * @param shootFrequency the frequency of shoot
     * @return .
     */
    protected boolean canShoot(final long shootFrequency) {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastShootTime > shootFrequency) {
            this.lastShootTime = currentTime;
            return true;
        }
        return false;
    }

    protected abstract void changeRoutine();

    @Override
    public abstract void shoot();

}
