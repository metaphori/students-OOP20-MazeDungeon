package model.gameobject.dynamicobject.enemy;

import model.common.Point2D;

/**
 * An interface for modeling a factory of Enemy. 
 */

public interface EnemyFactory {

    /**
     * @param position : the position where the enemy has to be spawned. 
     * @return an enemy of type: Sprout.
     */
    Enemy createSprout(Point2D position);

    /**
     * @param position : the position where the enemy has to be spawned. 
     * @return an enemy of type: Soul.
     */
    Enemy createSoul(Point2D position);

    /**
     * @param position : the position where the enemy has to be spawned. 
     * @return an enemy of type: SkeletonSeeker.
     */
    Enemy createSkeletonSeeker(Point2D position);

    /**
     * @param position : the position where the enemy has to be spawned. 
     * @return an enemy of type: Boss.
     */
    Enemy createBoss(Point2D position);
}
