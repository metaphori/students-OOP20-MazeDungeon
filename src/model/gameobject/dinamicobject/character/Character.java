package model.gameobject.dinamicobject.character;

import java.util.Set;

import model.common.Vector2D;
import model.gameobject.dinamicobject.DinamicObject;
import model.shop.Item;
import model.shop.ItemBuilder;
import model.shop.Items;


public interface Character extends DinamicObject {
    void shoot();

    void takesDamage(int damage);

    void addItem(Item item);

    Set<Item> getItems();

    double getLife();

    /**
     * Methods for movements and shoots.
     */
    void moveUp();

    void moveDown();

    void moveRight();

    void moveLeft();

    void stopVertical();

    void stopHorizontal();

    void setShoot(boolean shoot, Vector2D shootDirection);

    double getMaxLife();

    int getMoney();

    void setMoney(int money);

    void setDamage(int damage);

    void setBulletSpeed(long bulletSpeed);
    
    int getDamage();
    
    long getBulletSpeed();

    void setLife(double life);
}
