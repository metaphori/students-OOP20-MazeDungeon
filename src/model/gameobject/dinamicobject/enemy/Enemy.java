package model.gameobject.dinamicobject.enemy;

import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.dinamicobject.bullet.Bullet;

public interface Enemy extends DinamicObject {
    void shoot();

    void takesDamage(int damage);

    void notifyDropCoin();

    double getLife();
}
