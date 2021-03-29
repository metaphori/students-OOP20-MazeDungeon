package model.gameobject.dynamicobject.character;

import model.common.VectorDirection;
import model.gameobject.dynamicobject.DynamicObject;

/**
 * An interface for modeling the Character as extension of DinamycObject.
 */
public interface Character extends DynamicObject {

    /**
     * @return the max life of character
     */
    double getMaxLife();

    /**
     * 
     * @return the current life.
     */
    double getLife();

    /**
     * @return the amount of money.
     */
    int getMoney();

    /**
     * @param life to set
     */
    void setLife(double life);

    /**
     * @param money
     * set the current money amount
     */
    void setMoney(int money);

    /**
     * @param damage
     * increase the current damage.
     */
    void increaseDamage(int damage);

    /**
     * @param speed 
     * increase the current character speed.
     */
    void increaseSpeed(int speed);

    /**
     * @param bulletSpeed
     * increase bullet speed.
     */
    void increaseBulletSpeed(int bulletSpeed);

    /**
     * @param damage
     * Takes damage to the character.
     */
    void takesDamage(int damage);

    /**
     * @param shoot
     * @param vectorDirection
     * set a shoot and his direction.
     */
    void setShoot(boolean shoot, VectorDirection vectorDirection);

    /**
     * set the winning when the character pick up the final artifact.
     */
    void pickedUpFinalArtifact();

    /**
     * @return true if the character is death.
     */
    boolean isDead();

    /**
     * @return true if the character is won.
     */
    boolean isWin();

}
