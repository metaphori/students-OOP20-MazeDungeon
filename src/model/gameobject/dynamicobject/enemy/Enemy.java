package model.gameobject.dynamicobject.enemy;

import model.gameobject.dynamicobject.DynamicObject;

/**
 * An interface representing the Enemy as an extension of a DynamicObject.
 * 
 * It add to the DynamicObject's features, the concept of life, damage and the possibility of shoot.
 */
public interface Enemy extends DynamicObject {

    /**
     * @param damage : the damage to be taken by the enemy.
     */
    void takesDamage(double damage);

    /**
     * @return the current life of the enemy.
     */
    double getLife();

    /**
     * The enemy shoot if he can.
     */
    void tryToShoot();
}
