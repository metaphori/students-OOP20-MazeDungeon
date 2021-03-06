package model.gameobject.dinamicobject.bullet;

import model.common.BoundingBox;
import model.gameobject.dinamicobject.DinamicObject;

public interface Bullet extends DinamicObject {

    int getDamage();

    void setDamage(int damage);
  
    void setSafeZone(BoundingBox safeZone);
}
