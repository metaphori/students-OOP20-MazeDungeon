package model.shop;

import java.util.HashSet;
import java.util.Set;

public class ShopImpl implements Shop {
    private int actualMoney = 20;
    private int actualLife = 3;

    private static int MAX_LIFE=7;
    private Set<Items> purchasedItems = new HashSet<>();

    private String messageOuput = "";

    private final String msgBought;
    private final String msgNoMoney;
    @Override
    public String getMessageOuput() {
        return messageOuput;
    }
    /**
     * @return Item with features of ArthemideBow
     */
    public ShopImpl(final int actualMoney, final int actualLife) {
        this.actualLife = actualLife;
        this.actualMoney = actualMoney;
        msgBought = "You bought this item! You have: ";
        msgNoMoney = "You don't have enough coins!";
    }
    @Override
    public boolean checkItem(final Items i) {
        if(purchasedItems.contains(i)) {
            messageOuput="You already have this item";
            return false;
        }
        switch (i) {
            case ARTHEMIDEBOW:
                if (this.getArthemideBow().getCost() <= actualMoney) {
                    actualMoney -= this.getArthemideBow().getCost();
                    purchasedItems.add(i);
                    messageOuput = msgBought  + actualMoney;
                    return true;
                }
                break;
            case HERMESBOOTS:
                if (this.getHermesBoots().getCost() <= actualMoney) {
                    actualMoney -= this.getHermesBoots().getCost();
                    purchasedItems.add(i);
                    messageOuput = msgBought + actualMoney;
                    return true;
                }
                break;
            case ZEUSBOLT:
                if (this.getZeusBolt().getCost() <= actualMoney) {
                    actualMoney -= this.getZeusBolt().getCost();
                    purchasedItems.add(i);
                    messageOuput = msgBought + actualMoney;
                    return true;
                }
                break;
            case HEALTH:
                if (this.getHealth().getCost() <= actualMoney) {
                    if (this.actualLife + this.getHealth().getHealth().get() > MAX_LIFE) {
                        messageOuput = "You have too much life!";
                        return false;
                    }
                    actualMoney -= this.getHealth().getCost();
                    messageOuput = msgBought + actualMoney;
                    return true;
                }
                break;
             default:
                 messageOuput = msgNoMoney;
                 return false;

        }
        return false;
    }
    @Override
    public ItemBuilder getArthemideBow() {
        return new ItemBuilder.Builder("ArthemideBow", 2).addDemage(5).build();
    }

    /**
     * @return Item with features of HermesBoots
     */
    @Override
    public ItemBuilder getHermesBoots() {
        return new ItemBuilder.Builder("HermesBoots", 1).addSpeed(3).build();
    }

    /**
     * @return Item with features of ZeusBolt
     */
    @Override
    public ItemBuilder getZeusBolt() {
        return new ItemBuilder.Builder("ZeusBolt", 1).addSpeedAttack(4).build();
    }

    /**
     * @return Item with features more Health
     */
    @Override
    public ItemBuilder getHealth() {
        return new ItemBuilder.Builder("Health", 1).addHelath(2).build();
    }


    
}
