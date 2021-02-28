package model.gameobject.dinamicobject.bullet;

import model.gameobject.dinamicobject.DinamicObject;

public interface Bullet extends DinamicObject {

    int getDamage();

    void setDamage(int damage);
    
    
}
