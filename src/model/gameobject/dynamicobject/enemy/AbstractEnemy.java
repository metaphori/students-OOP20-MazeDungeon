package model.gameobject.dynamicobject.enemy;

import java.util.Random;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.AbstractDynamicObject;
import model.gameobject.simpleobject.Coin;

/**
 * An abstract class for represent the common features to all type of enemies.
 * 
 * An AbstractEnemy can manage it's life and it's shoot is timed with delay, 
 * it can also collide correctly with others GameObjects.
 * 
 * It provides two abstract methods to be implemented, one for implements the shoot,
 * and the other for implements the changing of it's routine.
 */
public abstract class AbstractEnemy extends AbstractDynamicObject implements Enemy {

    private long lastShootTime;
    private double life;
    private final long shootDelay;

    /**
     * Build a new AbstractEnemy by setting it's life, speed, type and position in the room. 
     * 
     * @param life : the default life of the enemy
     * @param speed : the default speed of the enemy
     * @param position : the starting position of the enemy
     * @param gameObjectType : the type of the gameObject, in particular the type of the enemy
     * @param shootDelay : the specific delay of the enemy shoot.
     */
    public AbstractEnemy(final double life, final int speed, final Point2D position, final GameObjectType gameObjectType, final long shootDelay) {
        super(speed, position, gameObjectType);
        this.shootDelay = shootDelay;
        this.life = life;
        this.changeRoutine();
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public double getLife() {
        return this.life;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void tryToShoot() {
        if (canShoot(this.shootDelay)) {
            this.shoot();
        }
    }

    /**
     * @{inheritDoc}
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
     * Provide to implement the collision of a generic enemy.
     * If it's colliding with a obstacle or an entity, the enemy's position is set to the last position,
     * and the routine of the enemy will change.
     * 
     * @param obj2 : the object with the enemy is colliding with.
     */
    @Override
    public void collideWith(final GameObject obj2) {
        switch (obj2.getGameObjectType().getCollisionType()) {
        case OBSTACLE:
            if (this.getBaseBoundingBox().intersectWith(obj2.getBoundingBox())) {
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
     * @return a random direction.
     */
    protected Vector2D getRndDirection() {
        final Random rndFlipDirection = new Random();
        final double newX = rndFlipDirection.nextBoolean() ? -1 : 1;
        final double newY = rndFlipDirection.nextBoolean() ? -1 : 1;
        return new Vector2D(newX, newY);
    }

    /**
     * method that all Enemy must implements for shoot.
     */
    protected abstract void shoot();

    /**
     * method that all Enemy must implements for change their routine.
     */
    protected abstract void changeRoutine();
}
