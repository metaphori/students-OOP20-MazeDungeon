package model.gameobject.dinamicobject.character;

import model.gameobject.dinamicobject.DinamicObject;

public interface Character extends DinamicObject {
    void shoot();

    void takeDamage(int damage);
}
