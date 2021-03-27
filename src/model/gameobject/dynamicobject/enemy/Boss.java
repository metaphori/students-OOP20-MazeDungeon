package model.gameobject.dynamicobject.enemy;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.bullet.Bullet;
import model.gameobject.dynamicobject.bullet.BulletFactory;
import model.gameobject.dynamicobject.bullet.BulletFactoryImpl;
import model.gameobject.dynamicobject.character.Character;
import model.gameobject.simpleobject.Coin;

public class Boss extends AbstractEnemy {
    private final BulletFactory bulletFactory = new BulletFactoryImpl();
    private static final long BOSS_SHOOT_DELAY = 500;
    private static final int MAX_SPEED = 400;
    private static final int ALIGN_SHOOT_X = 110;
    private static final int ALIGN_SHOOT_Y = 140;
    private static final int ALIGN_MONEY = 40;
    private static final int MODIFY_ALIGN_MONEY = 10;
    private static final int BOSS_LIFE = 400;

    private long lastHitTime;

    public Boss(final int speed, final Point2D position, final GameObjectType gameObjectType) {
        super(BOSS_LIFE, speed, position, gameObjectType, BOSS_SHOOT_DELAY);
    }

    private void moveAroundWall() {
        if ((int) this.getDirection().getX() == 0 && (int) this.getDirection().getY() == -1) {
            this.setDirection(new Vector2D(1, 0));
            this.setPosition(new Point2D(this.getPosition().getX(), this.getPosition().getY() + 1));
        } else if ((int) this.getDirection().getX() == 1 && (int) this.getDirection().getY() == 0) {
            this.setDirection(new Vector2D(0, 1));
            this.setPosition(new Point2D(this.getPosition().getX() - 1, this.getPosition().getY()));
        } else if ((int) this.getDirection().getX() == 0 && (int) this.getDirection().getY() == 1) {
            this.setDirection(new Vector2D(-1, 0));
            this.setPosition(new Point2D(this.getPosition().getX(), this.getPosition().getY() - 1));
        } else if ((int) this.getDirection().getX() == -1 && (int) this.getDirection().getY() == 0) {
            this.setDirection(new Vector2D(0, -1));
            this.setPosition(new Point2D(this.getPosition().getX() + 1, this.getPosition().getY()));
        }
    }
    private void moveUpWall() {
        if ((int) this.getDirection().getX() == -1 && (int) this.getDirection().getY() == 0) {
            this.setDirection(new Vector2D(1, 0));
            this.setPosition(new Point2D(this.getPosition().getX() + 1, this.getPosition().getY()));
        } else {
            this.setDirection(new Vector2D(-1, 0));
            this.setPosition(new Point2D(this.getPosition().getX() - 1, this.getPosition().getY()));
        }
    }
    /**
     * change Boss Routine.
     */
    @Override
    protected void changeRoutine() {
        if (this.getSpeed() < MAX_SPEED) {
            final int incrementSpeed = 10;
            this.setSpeed(this.getSpeed() + incrementSpeed);
        }
        if (this.getLife() >=  BOSS_LIFE / 2) {
            moveUpWall();
        } else {
            moveAroundWall();
        }

    }
    /**
     * when Boss collide.
     */
    @Override
    public void collideWith(final GameObject obj2) {
        final int takeDamage = 10;
        final int hitDelay = 1000;
        super.collideWith(obj2);
        if (obj2.getGameObjectType() == GameObjectType.CHARACTER) {
            final long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastHitTime > hitDelay) {
                ((Character) obj2).takesDamage(takeDamage);
                this.lastHitTime = currentTime;
            }
        }
    }
    /**
     * set property Boss Shoot.
     */
    @Override
    protected void shoot() {
        final Bullet bullet;
        if (this.getLife() >=  BOSS_LIFE / 2) {
            bullet = bulletFactory.createBossBullet(this.getPosition().sum(new Vector2D(ALIGN_SHOOT_X, ALIGN_SHOOT_Y)),
                    new Vector2D(0, 1).getNormalized());
        } else {
            int modifyAlignShootY = 0;
            if (this.getDirection().getX() == -1 && this.getDirection().getY() == 0) {
                modifyAlignShootY = MODIFY_ALIGN_MONEY;
            }
            final Point2D characterPosition = this.getRoom().getRoomManager().getCharacter().getPosition();
            bullet = bulletFactory.createBossBullet(this.getPosition().sum(new Vector2D(ALIGN_SHOOT_X, ALIGN_SHOOT_Y - modifyAlignShootY)),
                    new Vector2D(characterPosition.getX() - ALIGN_SHOOT_X - this.getPosition().getX(), characterPosition.getY() - ALIGN_SHOOT_Y - this.getPosition().getY()).getNormalized());
        }
        this.getRoom().addDynamicObject(bullet);
    }

    /**
     * @param elapsed
     * @Override
     */
    public void updateState(final double elapsed) {
        this.move(elapsed);
        this.tryToShoot();
    }
    /**
     * spawn coin when the boss dies.
     */
    @Override
    protected void spawnCoin() {
        this.getRoom().addSimpleObject(new Coin(this.getPosition().sum(new Vector2D(this.getBoundingBox().getWidth() / 2 - ALIGN_MONEY,
                this.getBoundingBox().getHeight() / 2 + ALIGN_MONEY))));
        this.getRoom().addSimpleObject(new Coin(this.getPosition().sum(new Vector2D(this.getBoundingBox().getWidth() / 2 + ALIGN_MONEY,
                this.getBoundingBox().getHeight() / 2 + ALIGN_MONEY))));
        this.getRoom().addSimpleObject(new Coin(this.getPosition().sum(new Vector2D(this.getBoundingBox().getWidth() / 2,
                this.getBoundingBox().getHeight() / 2 + ALIGN_MONEY))));
    }
}
