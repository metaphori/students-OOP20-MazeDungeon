package model.gameobject.dinamicobject.enemy;

import model.gameobject.dinamicobject.DinamicObject;

public interface Enemy extends DinamicObject {

    void shoot();

    void takesDamage(double damage);

    double getLife();
}
