package model.gameobject.dinamicobject.enemy;

import java.util.Random;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.bullet.Bullet;

public class EnemyFactoryImpl implements EnemyFactory {

    /**
     * @return an enemy of type: Sprout
     */
    @Override
    public Enemy createSprout(final int id, final int speed, final Point2D position, final Vector2D direction) {
        return new AbstractEnemy(id, speed, position, direction, GameObjectType.ENEMY_SPROUT) {

            @Override
            public void updateState(final double elapsed) {
                this.move(elapsed);
            }

            @Override
            public Bullet shoot() {
                // TODO Auto-generated method stub
                return null;
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
    public Enemy createSoul(final int id, final int speed, final Point2D position, final Vector2D direction) {
        return new AbstractEnemy(id, speed, position, direction, GameObjectType.ENEMY_SOUL) {
            @Override
            public void updateState(final double elapsed) {
                this.move(elapsed);
            }

            @Override
            public Bullet shoot() {
                // TODO Auto-generated method stub
                return null;
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
    public Enemy createSkeletonSeeker(final int id, final int speed, final Point2D position, final Vector2D direction) {
        return new AbstractEnemy(id, speed, position, direction, GameObjectType.ENEMY_SKELETON) {

            @Override
            public void updateState(final double elapsed) {
                this.move(elapsed);
            }

            @Override
            public Bullet shoot() {
                // TODO Auto-generated method stub
                return null;
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
    public Enemy createBoss(final int id, final int speed, final Point2D position, final Vector2D direction) {
        // TODO Auto-generated method stub
        return null;
    }

}
