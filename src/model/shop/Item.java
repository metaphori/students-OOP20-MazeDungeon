package model.shop;
/**
 * 
 * Interface that declares methods for setting parameters to Item.
 *
 */
public interface Item {

    int getCost();

    void setCost(int cost);

    Items getName();

    double getHealth();

    int getSpeed();

    int getBulletSpeed();

    int getDamage();
}
