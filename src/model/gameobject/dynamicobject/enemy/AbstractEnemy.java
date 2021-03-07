package model.gameobject.dynamicobject.enemy;

import java.util.Random;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.AbstractDynamicObject;
import model.gameobject.simpleobject.Coin;

public abstract class AbstractEnemy extends AbstractDynamicObject implements Enemy {

    private long lastShootTime = System.currentTimeMillis();
    private double life;

    /**
     * @param life : the default life of the enemy
     * @param speed : the default speed of the enemy
     * @param position : the starting position of the enemy
     * @param gameObjectType : the type of the gameObject, in particular the type of the enemy
     */
    public AbstractEnemy(final double life, final int speed, final Point2D position, final GameObjectType gameObjectType) {
        super(speed, position, gameObjectType);
        this.life = life;
        this.changeRoutine();
    }

    /**
     * @return the current life of the enemy.
     */
    @Override
    public double getLife() {
        return this.life;
    }

    /**
     * @param damage : the damage to be taken by the enemy.
     */
    @Override
    public void takesDamage(final double damage) {
        this.life = this.life - damage;
        if (this.life <= 0) {
            this.spawnCoin();
            this.getRoom().deleteGameObject(this);
        }
    }

    /**
     * Spawn a new coin in the room. 
     */
    protected void spawnCoin() {
        this.getRoom().addSimpleObject(new Coin(this.getPosition().sum(new Vector2D(this.getBoundingBox().getWidth() / 2,
                this.getBoundingBox().getHeight() / 2))));
    }

    /**
     * @param obj2 : the object with the enemy is colliding with.
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
            final AbstractDynamicObject dinamicObject = (AbstractDynamicObject) obj2;
            dinamicObject.setPosition(dinamicObject.getLastPosition());
            this.changeRoutine();
            break;
        default:
            break;
        }
    }

    /**
     * @param shootFrequency the frequency of shoot
     * @return true if the enemy can shoot.
     */
    protected boolean canShoot(final long shootFrequency) {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastShootTime > shootFrequency) {
            this.lastShootTime = currentTime;
            return true;
        }
        return false;
    }

    /**
     * @return a random direction.
     */
    protected Vector2D getRndDirection() {
        final Random rndFlipDirection = new Random();
        final double newX = rndFlipDirection.nextBoolean() ? -1 : 1;
        final double newY = rndFlipDirection.nextBoolean() ? -1 : 1;
        return new Vector2D(newX, newY);
    }

    @Override
    public abstract void shoot();

    protected abstract void changeRoutine();
}
