package model.gameobject.dynamicobject.character;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.common.VectorDirection;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.AbstractDynamicObject;
import model.gameobject.dynamicobject.bullet.Bullet;
import model.gameobject.dynamicobject.bullet.BulletFactory;
import model.gameobject.dynamicobject.bullet.BulletFactoryImpl;

/**
 * The implementation of Character
 * 
 * It contains different methods to handle all the character's characteristics as life, bulletDamage etc...
 * It contains the costants to inizialize the character at the beginning.
 * It contains methods to handle character state.
 *
 */
public class CharacterImpl extends AbstractDynamicObject implements Character {

    private static final long serialVersionUID = 1L;

    private static final double MAX_LIFE = 100;
    private static final int INITIAL_SPEED = 300; 
    private static final int INITIAL_MONEY = 0;
    private static final int INITIAL_BONUS_SPEED = 0;
    private static final int INITIAL_BULLET_DELAY = 400;

    private double life;
    private int bonusDamage;
    private int bonusSpeed;
    private int money;
    private final BulletFactory bulletFactory;
    private long lastShootTime; 
    private Vector2D shootDirection;
    private boolean shoot;
    private boolean win;

    public CharacterImpl(final Point2D position, final GameObjectType gameObjectType) {
        super(INITIAL_SPEED, position, gameObjectType);
        this.life = MAX_LIFE;
        this.bonusDamage = 0;
        this.bonusSpeed = INITIAL_BONUS_SPEED;
        this.money = INITIAL_MONEY;
        this.lastShootTime = System.currentTimeMillis();
        this.bulletFactory = new BulletFactoryImpl();
        this.shoot = false;
        this.win = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMaxLife() {
        return MAX_LIFE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLife() {
        return this.life;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMoney() {
        return this.money;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLife(final double life) {
        this.life = life;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMoney(final int money) {
        this.money = money;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseDamage(final int damage) {
        this.bonusDamage += damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseSpeed(final int speed) {
        super.setSpeed(super.getSpeed() + speed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseBulletSpeed(final int bulletSpeed) {
        this.bonusSpeed += bulletSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void takesDamage(final int damage) {
        this.life = this.life - damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShoot(final boolean shoot, final VectorDirection shootDirection) {
        if (this.canShoot()) {
            this.shoot = shoot;
            this.shootDirection = shootDirection.getDirection();
        }
    }

    /**
     * shoot a bullet.
     */
    private void shoot() {
        final Bullet bullet = bulletFactory.createCharacterBullet(
                new Point2D(getPosition().getX() + this.getBoundingBox().getWidth() / 2, getPosition().getY() + this.getBoundingBox().getHeight() / 2), this.shootDirection,
                this.bonusDamage, this.bonusSpeed);
        this.getRoom().addDynamicObject(bullet);
        this.shoot = false;
    }

    /**
     * 
     * @return true if the character can shoot
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
     * {@inheritDoc}
     */
    @Override
    public void collideWith(final GameObject oj2) {
       switch (oj2.getGameObjectType().getCollisionType()) {
            case OBSTACLE:
                if (this.getBaseBoundingBox().intersectWith(oj2.getBoundingBox())) {
                    this.setPosition(this.getLastPosition());
                }
                break;
            case ENTITY:
                final AbstractDynamicObject dinamicObject = (AbstractDynamicObject) oj2;
                dinamicObject.setPosition(dinamicObject.getLastPosition());
                break;
            case INTERACTIVE_ELEMENT:
                if (oj2.getGameObjectType().equals(GameObjectType.COIN)) {
                    this.money++;
                    this.getRoom().deleteGameObject(oj2);
                }
                break;
            default:
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double elapsed) { 
        this.move(elapsed);
        if (this.shoot) {
            this.shoot();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {
        return this.life <= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWin() {
        return this.win;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pickedUpFinalArtifact() {
        this.win = true;
    }
}
