package model.gameobject.dynamicobject.character;

import model.common.Vector2D;
import model.gameobject.dynamicobject.DynamicObject;

public interface Character extends DynamicObject {

    double getMaxLife();

    double getLife();

    int getMoney();

    void setLife(double life);

    void setMoney(int money);

    void increaseDamage(int damage);

    void increaseSpeed(int speed);

    void increaseBulletSpeed(int bulletSpeed);

    void takesDamage(int damage);

    void setShoot(boolean shoot, Vector2D shootDirection);

    void pickedUpFinalArtefact();

    boolean isWon();

    boolean isDead();

}
