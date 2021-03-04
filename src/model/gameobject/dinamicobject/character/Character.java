package model.gameobject.dinamicobject.character;

import java.util.Set;

import model.common.Vector2D;
import model.gameobject.dinamicobject.DinamicObject;
import model.shop.Item;
import model.shop.ItemBuilder;
import model.shop.Items;


public interface Character extends DinamicObject {

    void takesDamage(int damage);

    double getLife();

    void setLife(double life);


    void setShoot(boolean shoot, Vector2D shootDirection);

    double getMaxLife();

    int getMoney();

    void setMoney(int money);

    void setBonusDamage(int damage);

    void setBulletSpeed(int bulletSpeed);

    int getBonusDamage();

    int getBulletSpeed();

    boolean isDead();

    void pickedUpFinalArtefact();

    boolean isWon();

}
