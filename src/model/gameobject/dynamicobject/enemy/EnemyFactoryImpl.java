package model.gameobject.dynamicobject.enemy;

import java.util.ArrayList;
import java.util.List;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dynamicobject.bullet.Bullet;
import model.gameobject.dynamicobject.bullet.BulletFactory;
import model.gameobject.dynamicobject.bullet.BulletFactoryImpl;

public class EnemyFactoryImpl implements EnemyFactory {

    private static final int SOUL_LIFE = 50;
    private static final int SPROUT_LIFE = 50;
    private static final int SKELETON_LIFE = 75;

    private static final int SOUL_SPEED = 90;
    private static final int SPROUT_SPEED = 50;
    private static final int SKELETON_SPEED = 90;
    private static final int BOSS_SPEED = 150;

    private static final long SOUL_SHOOT_DELAY = 1500;
    private static final long SPROUT_SHOOT_DELAY = 2000;
    private static final long SKELETON_SHOOT_DELAY = 1000;

    private final BulletFactory bulletFactory = new BulletFactoryImpl();

    /**
     * @return an enemy of type: Sprout
     */
    @Override
    public Enemy createSprout(final Point2D position) {
        return new AbstractEnemy(SPROUT_LIFE, SPROUT_SPEED, position, GameObjectType.ENEMY_SPROUT, SPROUT_SHOOT_DELAY) {

            private static final int ROUTINE_CHANGE_TIME = 1000;
            private long lastHitTime;

            @Override
            public void updateState(final double elapsed) {
                this.tryToShoot();
                final long currentTime = System.currentTimeMillis();
                if (currentTime - this.lastHitTime > ROUTINE_CHANGE_TIME) {
                    this.lastHitTime = currentTime;
                    this.followCharacter();
                }
                this.move(elapsed);
            }

            @Override
            protected void shoot() {
                final Point2D newPosition = new Point2D(this.getPosition().getX() + this.getBoundingBox().getWidth() / 2, this.getPosition().getY());
                final Bullet bullet = bulletFactory.createSproutBullet(newPosition, this.getDirection().getNormalized());
                this.getRoom().addDynamicObject(bullet);
            }

            @Override
            protected void changeRoutine() {
                this.lastHitTime = System.currentTimeMillis();
                this.setDirection(this.getRndDirection());
            }

            private void followCharacter() {
                final Point2D characterPosition = this.getRoom().getRoomManager().getCharacter().getPosition();
                this.setDirection(new Vector2D(characterPosition.getX() - this.getPosition().getX(), 
                        characterPosition.getY() - this.getPosition().getY()).getNormalized());
            }
        };
    }

    /**
     * @return an enemy of type: Soul
     */
    @Override
    public Enemy createSoul(final Point2D position) {
        return new AbstractEnemy(SOUL_LIFE, SOUL_SPEED, position, GameObjectType.ENEMY_SOUL, SOUL_SHOOT_DELAY) {
            @Override
            public void updateState(final double elapsed) {
                this.tryToShoot();
                this.move(elapsed);
            }

            @Override
            protected void shoot() {
                final Point2D characterPosition = this.getRoom().getRoomManager().getCharacter().getPosition();
                final Vector2D bulletDIrection = new Vector2D(characterPosition.getX() - this.getPosition().getX(), 
                                                              characterPosition.getY() - this.getPosition().getY()).getNormalized();
                final Bullet bullet = bulletFactory.createSoulBullet(this.getPosition(), bulletDIrection);
                this.getRoom().addDynamicObject(bullet);
            }

            @Override
            protected void changeRoutine() {
                this.setDirection(this.getRndDirection());
            }
        };
    }

    /**
     * @return an enemy of type: SkeletonSeeker
     */
    @Override
    public Enemy createSkeletonSeeker(final Point2D position) {
        return new AbstractEnemy(SKELETON_LIFE, SKELETON_SPEED, position, GameObjectType.ENEMY_SKELETON, SKELETON_SHOOT_DELAY) {

            private long lastChangeTime;
            private static final int ROUTINE_CHANGE_TIME = 5000;
            private boolean inMovement = true;

            @Override
            public void updateState(final double elapsed) {
                final long currentTime = System.currentTimeMillis();
                if (currentTime - this.lastChangeTime > ROUTINE_CHANGE_TIME) {
                    this.lastChangeTime = currentTime;
                    if (inMovement) {
                        this.stopMovement();
                        this.inMovement = false;
                    } else {
                        this.changeRoutine();
                    }
                }
                if (!this.inMovement) {
                    this.tryToShoot();
                }
                this.move(elapsed);
            }

            @Override
            protected void shoot() {
                final Point2D newPosition = new Point2D(this.getPosition().getX() + this.getBoundingBox().getWidth() / 2, this.getPosition().getY());
                final List<Bullet> bullets = new ArrayList<>();
                bullets.add(bulletFactory.createSkeletonBullet(newPosition, new Vector2D(0, -1)));
                bullets.add(bulletFactory.createSkeletonBullet(newPosition, new Vector2D(0, 1)));
                bullets.add(bulletFactory.createSkeletonBullet(newPosition, new Vector2D(1, 0)));
                bullets.add(bulletFactory.createSkeletonBullet(newPosition, new Vector2D(-1, 0)));
                bullets.forEach(b -> {
                    this.getRoom().addDynamicObject(b);
                });
            }

            @Override
            protected void changeRoutine() {
                this.inMovement = true;
                this.setDirection(this.getRndDirection());
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
    public Enemy createBoss(final Point2D position) {
        return new Boss(BOSS_SPEED, position, GameObjectType.ENEMY_BOSS);
    }
}
