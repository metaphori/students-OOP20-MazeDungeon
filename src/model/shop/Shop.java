package model.shop;

public interface Shop {

    String getMessageOuput();

    /**
     * @return Item with features of ArthemideBow
     */

    boolean checkItem(Items i);

    Item getArthemideBow();

    /**
     * @return Item with features of HermesBoots
     */
    Item getHermesBoots();

    /**
     * @return Item with features of ZeusBolt
     */
    Item getZeusBolt();

    /**
     * @return Item with features more Health
     */
    Item getHealth();
    /**
     * 
     * @return .
     */
    int moneyLeft();

}