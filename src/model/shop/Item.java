package model.shop;
/**
 * 
 * Interface that declares methods for setting parameters to Item.
 *
 */
public interface Item {
    /**
     * @return returns the value that represents the price
     */
    int getCost();
    /**
     * set the price of the item.
     * @param cost : price value
     */
    void setCost(int cost);
    /**
     * returns the name of the item.
     * @return returns an enumeration representing the item name
     */
    Items getName();
    /**
     * 
     * @return returns the value that represents the life that the item adds
     */
    double getHealth();
    /**
     * 
     * @return returns the value that represents the movement speed that the item adds
     */
    int getSpeed();
    /**
     * 
     * @return returns the value that represents the bullet speed that the item adds
     */
    int getBulletSpeed();
    /**
     * 
     * @return returns the value that represents the damage that the item adds
     */
    int getDamage();
}
