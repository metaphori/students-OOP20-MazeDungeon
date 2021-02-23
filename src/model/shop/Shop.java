package model.shop;

public interface Shop {

    String getMessageOuput();

    /**
     * @return Item with features of ArthemideBow
     */

    boolean checkItem(Items i);

    ItemBuilder getArthemideBow();

    /**
     * @return Item with features of HermesBoots
     */
    ItemBuilder getHermesBoots();

    /**
     * @return Item with features of ZeusBolt
     */
    ItemBuilder getZeusBolt();

    /**
     * @return Item with features more Health
     */
    ItemBuilder getHealth();

}