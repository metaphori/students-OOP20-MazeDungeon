package model.gameobject.dinamicobject.enemy;

import java.util.Random;

import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.bullet.Bullet;
import model.room.Room;

public class EnemyFactoryImpl implements EnemyFactory {

    private final IdIterator idIterator;

    public EnemyFactoryImpl(final IdIterator idIterator) {
        this.idIterator = idIterator;
    }

    /**
     * @return an enemy of type: Sprout
     */
    @Override
    public Enemy createSprout(final int speed, final Point2D position, final Vector2D direction,  final Room room) {
        return new AbstractEnemy(this.idIterator.next(), speed, position, direction, GameObjectType.ENEMY_SPROUT, room) {

            @Override
            public void updateState(final double elapsed) {
                this.move(elapsed);
            }

            @Override
            public void shoot() {
                // TODO Auto-generated method stub
            }

            @Override
            protected void changeRoutine() {
                // TODO Auto-generated method stub

            }
        };
    }

    /**
     * @return an enemy of type: Soul
     */
    @Override
    public Enemy createSoul(final int speed, final Point2D position, final Vector2D direction, final Room room) {
        return new AbstractEnemy(this.idIterator.next(), speed, position, direction, GameObjectType.ENEMY_SOUL, room) {
            @Override
            public void updateState(final double elapsed) {
                this.move(elapsed);
                if (this.canShoot()) {
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
                        new Vector2D(characterPosition.getX() - this.getPosition().getX(), characterPosition.getY() - this.getPosition().getY()).getNormalized(), room);
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
    public Enemy createSkeletonSeeker(final int speed, final Point2D position, final Vector2D direction, final Room room) {
        return new AbstractEnemy(this.idIterator.next(), speed, position, direction, GameObjectType.ENEMY_SKELETON, room) {

            @Override
            public void updateState(final double elapsed) {
                this.move(elapsed);
                if (this.canShoot()) {
                    this.shoot();
                }
            }

            @Override
            public void shoot() {
                this.setDirection(new Vector2D(0, 0));
                final Point2D newPosition = new Point2D(this.getPosition().getX() + this.getBoundingBox().getWidth() / 2, this.getPosition().getY());
                final Bullet bullet = this.getBulletFactory().createSkeletonBullet(newPosition, new Vector2D(1, 0), room);
                this.getRoom().addDinamicObject(bullet);
            }

            @Override
            protected void changeRoutine() {
                // TODO Auto-generated method stub

            }
        };
    }

    /**
     * @return an enemy of type: Boss
     */
    @Override
    public Enemy createBoss(final int speed, final Point2D position, final Vector2D direction, final Room room) {
        return new Boss(this.idIterator.next(), speed, position, direction, GameObjectType.ENEMY_BOSS, room);
    }

}
