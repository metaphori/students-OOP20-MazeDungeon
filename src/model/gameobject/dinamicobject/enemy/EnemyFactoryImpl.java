package model.gameobject.dinamicobject.enemy;

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
                // TODO Auto-generated method stub

            }

            @Override
            public Bullet shoot() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void move(final double elapsed) {
                // TODO Auto-generated method stub

            }
        };
    }

    /**
     * @return an enemy of type: Soul
     */
    @Override
    public Enemy createSoul() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return an enemy of type: SkeletonSeeker
     */
    @Override
    public Enemy createSkeletonSeeker() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return an enemy of type: Boss
     */
    @Override
    public Enemy createBoss() {
        // TODO Auto-generated method stub
        return null;
    }

}
