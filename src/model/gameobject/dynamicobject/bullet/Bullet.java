package model.gameobject.dynamicobject.bullet;

import model.common.BoundingBox;
import model.gameobject.dynamicobject.DynamicObject;

public interface Bullet extends DynamicObject {

    int getDamage();

    void setDamage(int damage);

    void setSafeZone(BoundingBox safeZone);
}
