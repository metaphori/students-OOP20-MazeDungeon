package model.gameobject.dynamicobject.enemy;

import model.gameobject.dynamicobject.DynamicObject;

public interface Enemy extends DynamicObject {

    void shoot();

    void takesDamage(double damage);

    double getLife();
}
