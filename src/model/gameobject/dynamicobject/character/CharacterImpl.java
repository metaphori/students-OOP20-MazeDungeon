package model.gameobject.dynamicobject.character;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.AbstractDynamicObject;
import model.gameobject.dynamicobject.bullet.Bullet;
import model.gameobject.dynamicobject.bullet.BulletFactory;
import model.gameobject.dynamicobject.bullet.BulletFactoryImpl;


public class CharacterImpl extends AbstractDynamicObject implements Character {

    /**
     * CONSTANTS.
     */

    private static final double MAX_LIFE = 100;
    private static final int INITIAL_SPEED = 400; 
    private static final int INITIAL_MONEY = 0;
    private static final int INITIAL_BULLET_SPEED = 3;
    private static final int INITIAL_BULLET_DELAY = 200; 

    /*
     * CHARACTER CHARACTERISTIC.
     */
    private double life;
    private int bonusDamage;
    private int bulletSpeed;
    private int money;

    /**
     * VARIABLES.
     */
    private final BulletFactory bulletFactory;
    private long lastShootTime; 
    private Vector2D shootDirection;
    private boolean shoot;
    private boolean won;

    public CharacterImpl(final Point2D position, final GameObjectType gameObjectType) {
        super(INITIAL_SPEED, position, gameObjectType);
        this.life = MAX_LIFE;
        this.bonusDamage = 0;
        this.bulletSpeed = INITIAL_BULLET_SPEED;
        this.money = INITIAL_MONEY;
        this.lastShootTime = System.currentTimeMillis();
        this.bulletFactory = new BulletFactoryImpl();
        this.shoot = false;
        this.won = false;
    }

    /**
     * @return the max life
     */
    @Override
    public double getMaxLife() {
        return MAX_LIFE;
    }

    /**
     * @return return the current life.
     */
    @Override
    public double getLife() {
        return this.life;
    }

    /**
     * @return the amount of money.
     */
    @Override
    public int getMoney() {
        return this.money;
    }

    /**
     * @param life to set
     */
    @Override
    public void setLife(final double life) {
        this.life = life;
    }

    /**
     * @param money
     * set the current money amount
     */
    @Override
    public void setMoney(final int money) {
        this.money = money;
    }

    /**
     * @param damage
     * increase the current damage.
     */
    @Override
    public void increaseDamage(final int damage) {
        this.bonusDamage += damage;
    }

    /**
     * @param speed 
     * increase the current character speed.
     */
    @Override
    public void increaseSpeed(final int speed) {
        super.setSpeed(super.getSpeed() + speed);
    }

    /**
     * @param bulletSpeed
     * increase bullet speed.
     */
    @Override
    public void increaseBulletSpeed(final int bulletSpeed) {
        this.bulletSpeed += bulletSpeed;
    }

    /**
     * Takes damage to the character.
     */
    @Override
    public void takesDamage(final int damage) {
        this.life = this.life - damage;
        System.out.println(this.getID() + ") " + this.getGameObjectType() + " Life: " + this.life);
    }

    /**
     * @param shootDirection     * 
     * set a shoot and his direction.
     */
    @Override
    public void setShoot(final boolean shoot, final Vector2D shootDirection) {
        if (this.canShoot()) {
            this.shoot = shoot;
            this.shootDirection = shootDirection;
        }
    }

    /**
     * shoot a bullet.
     */
    private void shoot() {
        final Bullet bullet = bulletFactory.createCharacterBullet(
                new Point2D(getPosition().getX() + this.getBoundingBox().getWidth() / 2, getPosition().getY() + this.getBoundingBox().getHeight() / 2),
                this.shootDirection.mul(this.bulletSpeed), this.bonusDamage);
        bullet.setSafeZone(this.getBoundingBox());
        this.getRoom().addDynamicObject(bullet);
        this.shoot = false;
    }

    /**
     * 
     * @return if the character can shoot
     */
    private boolean canShoot() {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastShootTime > INITIAL_BULLET_DELAY) {
            this.lastShootTime = currentTime;
            return true;
        }
        return false;
    }

    /**
     * @param obj2 
     * the object the character is collide with.
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
                }
                break;
            case ENTITY:
                final AbstractDynamicObject dinamicObject = (AbstractDynamicObject) obj2;
                dinamicObject.setPosition(dinamicObject.getLastPosition());
                break;
            case INTERACTIVE_ELEMENT:
                if (obj2.getGameObjectType().equals(GameObjectType.COIN)) {
                    System.out.println("COLLECT A COIN");
                    this.money++;
                    System.out.println("CURRENT MONEY BADGE: " + this.money);
                    this.getRoom().deleteGameObject(obj2);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Update state.
     */
    @Override
    public void updateState(final double elapsed) { 
        this.move(elapsed);
        if (this.shoot) {
            this.shoot();
        }
    }

    /**
     * @return true if the character is death.
     */
    @Override
    public boolean isDead() {
        return this.life <= 0;
    }

    /**
     * @return true if the character is won.
     */
    @Override
    public boolean isWon() {
        return this.won;
    }

    /**
     * set the winning. 
     */
    @Override
    public void pickedUpFinalArtefact() {
        this.won = true;
    }
}

