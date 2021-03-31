package model.gameobject.dynamicobject.bullet;

import model.common.Point2D;
import model.common.Vector2D;

/**
 * An interface for modeling a factory of bullet.
 */

public interface BulletFactory {

    /**
     * @param initialPosition : the position where the bullet will be spawned. 
     * @param direction : the direction where the bullet will go.
     * @param bonusDamage : the bonus damage of the bullet.
     * @param bonusBulletSpeed : the bonus speed of the bullet.
     * @return a main character bullet
     */
    Bullet createMainCharacterBullet(Point2D initialPosition, Vector2D direction, int bonusDamage, int bonusBulletSpeed);

    /**
     * @param initialPosition the position where the bullet will be spawned. 
     * @param direction the direction where the bullet will go.
     * @return a skeleton bullet
     */
    Bullet createSkeletonBullet(Point2D initialPosition, Vector2D direction);

    /**
     * @param initialPosition : the position where the bullet will be spawned. 
     * @param direction : the direction where the bullet will go.
     * @return a soul Bullet.
     */
    Bullet createSoulBullet(Point2D initialPosition, Vector2D direction);

    /**
     * @param initialPosition : the position where the bullet will be spawned. 
     * @param direction : the direction where the bullet will go.
     * @return a sprout Bullet.
     */
    Bullet createSproutBullet(Point2D initialPosition, Vector2D direction);

    /**
     * @param initialPosition : the position where the bullet will be spawned. 
     * @param direction : the direction where the bullet will go.
     * @return a boss bullet
     */
    Bullet createBossBullet(Point2D initialPosition, Vector2D direction);
}
