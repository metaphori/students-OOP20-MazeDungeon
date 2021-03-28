package model.gameobject.dynamicobject.bullet;

import model.gameobject.dynamicobject.DynamicObject;

/**
 * An interface for modeling a bullet as extension of DynamicObject.
 * 
 */
public interface Bullet extends DynamicObject {
    /**
     * 
     * @return the current bullet damage.
     */
    int getDamage();

    /**
     * Set the current damage of current bullet.
     * @param damage the damage to set.
     */
    void setDamage(int damage);

}
