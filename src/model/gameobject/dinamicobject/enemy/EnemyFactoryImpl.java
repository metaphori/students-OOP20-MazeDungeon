package model.gameobject.dinamicobject.enemy;

import java.util.Random;

import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.bullet.Bullet;
import model.room.Room;

public class EnemyFactoryImpl implements EnemyFactory {

    private static final int SOUL_LIFE = 2;
    private static final int SPROUT_LIFE = 2;
    private static final int SKELETON_LIFE = 3;
    private static final int BOSS_LIFE = 90;

    private static final int SOUL_SPEED = 90;
    private static final int SPROUT_SPEED = 50;
    private static final int SKELETON_SPEED = 90;
    private static final int BOSS_SPEED = 90;

    private static final long SOUL_SHOOT_DELAY = 1500;
    private static final long SPROUT_SHOOT_DELAY = 2000;
    private static final long SKELETON_SHOOT_DELAY = 1000;
    private static final long BOSS_SHOOT_DELAY = 1000;

    /**
     * @return an enemy of type: Sprout
     */
    @Override
    public Enemy createSprout(final Point2D position, final Vector2D direction) {
        return new AbstractEnemy(SPROUT_LIFE, SPROUT_SPEED, position, direction, GameObjectType.ENEMY_SPROUT) {

            private long lastHitTime;

            @Override
            public void updateState(final double elapsed) {
                final long currentTime = System.currentTimeMillis();
                if (currentTime - this.lastHitTime > 1000) {
                    this.lastHitTime = currentTime;
                    this.followCharacter();
                }
                this.move(elapsed);
                if (canShoot(SPROUT_SHOOT_DELAY)) {
                    this.shoot();
                }
            }

            @Override
            public void shoot() {
                final Point2D newPosition = new Point2D(this.getPosition().getX() + this.getBoundingBox().getWidth() / 2, this.getPosition().getY());
                final Bullet bullet = this.getBulletFactory().createSproutBullet(newPosition, this.getDirection().getNormalized());
                this.getRoom().addDinamicObject(bullet);
            }

            @Override
            protected void changeRoutine() {
                this.lastHitTime = System.currentTimeMillis();
                final Random rndFlipDirection = new Random();
                final double newX = this.getDirection().getX() * (rndFlipDirection.nextBoolean() ? -1 : 1);
                final double newY = this.getDirection().getY() * (rndFlipDirection.nextBoolean() ? -1 : 1);
                this.setDirection(new Vector2D(newX, newY));
            }

            private void followCharacter() {
                if (this.getRoom().getCharacterPosition().isEmpty()) {
                    return;
                }
                final Point2D characterPosition = this.getRoom().getCharacterPosition().get();
                this.setDirection(new Vector2D(characterPosition.getX() - this.getPosition().getX(), 
                        characterPosition.getY() - this.getPosition().getY()).getNormalized());
            }
        };
    }

    /**
     * @return an enemy of type: Soul
     */
    @Override
    public Enemy createSoul(final Point2D position, final Vector2D direction) {
        return new AbstractEnemy(SOUL_LIFE, SOUL_SPEED, position, direction, GameObjectType.ENEMY_SOUL) {
            @Override
            public void updateState(final double elapsed) {
                this.move(elapsed);
                if (this.canShoot(SOUL_SHOOT_DELAY)) {
                    this.shoot();
                }
            }

            @Override
            public void shoot() {
                if (this.getRoom().getCharacterPosition().isEmpty()) {
                    return;
                }
                final Point2D characterPosition = this.getRoom().getCharacterPosition().get();
                final Bullet bullet = this.getBulletFactory().createSoulBullet(this.getPosition(),
                        new Vector2D(characterPosition.getX() - this.getPosition().getX(), characterPosition.getY() - this.getPosition().getY()).getNormalized());
                this.getRoom().addDinamicObject(bullet);
            }

            @Override
            protected void changeRoutine() {
                final Random rndFlipDirection = new Random();
                final double newX = this.getDirection().getX() * (rndFlipDirection.nextBoolean() ? -1 : 1);
                final double newY = this.getDirection().getY() * (rndFlipDirection.nextBoolean() ? -1 : 1);
                this.setDirection(new Vector2D(newX, newY));
            }
        };
    }

    /**
     * @return an enemy of type: SkeletonSeeker
     */
    @Override
    public Enemy createSkeletonSeeker(final Point2D position, final Vector2D direction) {
        return new AbstractEnemy(SKELETON_LIFE, SKELETON_SPEED, position, direction, GameObjectType.ENEMY_SKELETON) {

            private long lastChangeTime = System.currentTimeMillis();
            private boolean inMovement = true;

            @Override
            public void updateState(final double elapsed) {
                final long currentTime = System.currentTimeMillis();
                if (currentTime - this.lastChangeTime > 5000) {
                    this.lastChangeTime = currentTime;
                    if (inMovement) {
                        this.stopMovement();
                        this.inMovement = false;
                    } else {
                        this.changeRoutine();
                        this.inMovement = true;
                    }
                }
                if (this.canShoot(SKELETON_SHOOT_DELAY) && !this.inMovement) {
                    this.shoot();
                }
                this.move(elapsed);
            }

            @Override
            public void shoot() {
                final Point2D newPosition = new Point2D(this.getPosition().getX() + this.getBoundingBox().getWidth() / 2, this.getPosition().getY());
                final Bullet bullet = this.getBulletFactory().createSkeletonBullet(newPosition, new Vector2D(1, 0));
                this.getRoom().addDinamicObject(bullet);
            }

            @Override
            protected void changeRoutine() {
                final Random rndFlipDirection = new Random();
                final double newX = rndFlipDirection.nextBoolean() ? -1 : 1;
                final double newY = rndFlipDirection.nextBoolean() ? -1 : 1;
                this.setDirection(new Vector2D(newX, newY));
            }

            private void stopMovement() {
                this.setDirection(new Vector2D(0, 0));
            }
        };
    }

    /**
     * @return an enemy of type: Boss
     */
    @Override
    public Enemy createBoss(final Point2D position, final Vector2D direction) {
        return new Boss(BOSS_LIFE, BOSS_SPEED, position, direction, GameObjectType.ENEMY_BOSS);
    }

}
