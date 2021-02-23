package model.gameobject.dinamicobject.character;

import java.util.Set;

import model.gameobject.dinamicobject.DinamicObject;
import model.shop.ItemBuilder;

public interface Character extends DinamicObject {
    void shoot();

    void takeDamage(int damage);

    void addItem(ItemBuilder item);

    Set<ItemBuilder> getItems();

    double getLife();

    void setLife(int life);

    /**
     * Methods for movements.
     */
    void moveUp();

    void moveDown();

    void moveRight();

    void moveLeft();

    void stopVertical();

    void stopHorizontal();

    void setShoot(boolean shoot);



}
