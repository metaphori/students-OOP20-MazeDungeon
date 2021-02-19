package model.gameobject.dinamicobject.bullet;

import model.gameobject.dinamicobject.DinamicObject;

public interface Bullet extends DinamicObject {

    double getDamage();

    void setDamage(double damage);
    
    
}
