package model.gameobject.dynamicobject.bullet;

import model.gameobject.dynamicobject.DynamicObject;

public interface Bullet extends DynamicObject {

    int getDamage();

    void setDamage(int damage);

}
