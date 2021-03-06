package model.gameobject.dinamicobject.enemy;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.bullet.Bullet;
import model.gameobject.dinamicobject.bullet.BulletFactory;
import model.gameobject.dinamicobject.bullet.BulletFactoryImpl;
import model.gameobject.simpleobject.Coin;

public class Boss extends AbstractEnemy {
    private final BulletFactory bulletFactory = new BulletFactoryImpl();
    private static final long BOSS_SHOOT_DELAY = 500;
    private final double maxLife;
    private static final int MAX_SPEED = 300;
    private static final int ALIGN_SHOOT_X = 110;
    private static final int ALIGN_SHOOT_Y = 140;
    private static final int ALIGN_MONEY = 40;
    private static final int MODIFY_ALIGN_MONEY = 10;
    public Boss(final double life, final int speed, final Point2D position, final GameObjectType gameObjectType) {
        super(life, speed, position, gameObjectType);
        this.maxLife = life;
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
        final int incrementSpeed = 5;
        if (this.getSpeed() < MAX_SPEED) {
            this.setSpeed(this.getSpeed() + incrementSpeed);
        }
        if ((int) this.getDirection().getX() == -1 && (int) this.getDirection().getY() == 0) {
            this.setDirection(new Vector2D(1, 0));
            this.setPosition(new Point2D(this.getPosition().getX() + 1, this.getPosition().getY()));
        } else {
            this.setDirection(new Vector2D(-1, 0));
            this.setPosition(new Point2D(this.getPosition().getX() - 1, this.getPosition().getY()));
        }
    }
    /**
     * @Override
     */
    protected void changeRoutine() {
        if (this.getLife() >=  this.maxLife / 2) {
            moveUpWall();
        } else {
            moveAroundWall();
            if (this.getSpeed() < Boss.MAX_SPEED) {
                this.setSpeed(this.getSpeed() + 10);
            }
        }

    }

    /**
     * @Override
     */
    public void shoot() {
        final Bullet bullet;
        if (this.getLife() >=  this.maxLife / 2) {
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
        this.getRoom().addDinamicObject(bullet);
    }

    /**
     * @Override
     */
    public void updateState(final double elapsed) {
        this.move(elapsed);
        if (this.canShoot(BOSS_SHOOT_DELAY)) {
            this.shoot();
        }

    }
    /**
     * @Override
     */
    protected void spawnCoin() {
        this.getRoom().addSimpleObject(new Coin(this.getPosition().sum(new Vector2D(this.getBoundingBox().getWidth() / 2 - ALIGN_MONEY,
                this.getBoundingBox().getHeight() / 2 + ALIGN_MONEY))));
        this.getRoom().addSimpleObject(new Coin(this.getPosition().sum(new Vector2D(this.getBoundingBox().getWidth() / 2 + ALIGN_MONEY,
                this.getBoundingBox().getHeight() / 2 + ALIGN_MONEY))));
        this.getRoom().addSimpleObject(new Coin(this.getPosition().sum(new Vector2D(this.getBoundingBox().getWidth() / 2,
                this.getBoundingBox().getHeight() / 2 + ALIGN_MONEY))));
    }
}
