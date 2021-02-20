package model.gameobject.dinamicobject.enemy;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.bullet.Bullet;
import model.room.Room;

public class EnemyFactoryImpl implements EnemyFactory {

    /**
     * @return an enemy of type: Sprout
     */
    @Override
    public Enemy createSprout(final int id, final int speed, final Point2D position, final Vector2D direction, final Room room) {
        return new AbstractEnemy(id, speed, position, direction, GameObjectType.ENEMY_SPROUT, room) {

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
            public void collideWith(final GameObject obj2) {
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
            public void collideWith(final GameObject obj2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void updateState(final double elapsed) {
                this.move(elapsed);
            }

            @Override
            public Bullet shoot() {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }

    /**
     * @return an enemy of type: SkeletonSeeker
     */
    @Override
    public Enemy createSkeletonSeeker(final int id, final int speed, final Point2D position, final Vector2D direction) {
        return new AbstractEnemy(id, speed, position, direction, GameObjectType.ENEMY_SKULL_SEEKER) {

            @Override
            public void collideWith(final GameObject obj2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void updateState(final double elapsed) {
                this.move(elapsed);
            }

            @Override
            public Bullet shoot() {
                // TODO Auto-generated method stub
                return null;
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
