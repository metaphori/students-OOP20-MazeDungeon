package model.gameobject.dinamicobject.character;

import java.util.Set;

import model.gameobject.dinamicobject.DinamicObject;
import model.shop.Item;

public interface Character extends DinamicObject {
    void shoot();

    void takeDamage(int damage);

    void addItem(Item item);

    Set<Item> getItems();

    double getLife();

    void setLife(int life);

    void moveUp();

    void moveDown();

    void moveRight();

    void moveLeft();

    void tick();

    void stopVertical();

    void stopHorizontal();



}
