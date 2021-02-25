package model.shop;

import java.util.HashSet;
import java.util.Set;

public class ShopImpl implements Shop {
    //private int actualMoney = 20;
    private int actualLife = 7;

    private static double MAX_LIFE=7;
    private final Set<Items> purchasedItems = new HashSet<>();

    private String messageOuput = "";

    private final String msgBought;
    private final String msgNoMoney;
    private final int actualMoney;
    /**
     * @return .
     */
    public String getMessageOuput() {
        return messageOuput;
    }

    public ShopImpl(final int actualMoney, final int actualLife) {
        this.actualLife = actualLife;
        this.actualMoney = actualMoney;
        //this.actualMoney = actualMoney;
        msgBought = "You bought this item! You have: ";
        msgNoMoney = "You don't have enough coins!";
    }
    /**
     * @return .
     */
    public boolean checkItem(final Items i, int actualMoney) {
        if (purchasedItems.contains(i)) {
            messageOuput = "You already have this item";
            return false;
        }
        if (actualMoney == 0) {
            messageOuput = msgNoMoney;
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
                    if (this.actualLife + this.getHealth().getHealth() > MAX_LIFE) {
                        messageOuput = "You have too much life!";
                        return false;
                    }
                    actualMoney -= this.getHealth().getCost();
                    messageOuput = msgBought + actualMoney;
                    return true;
                }
                break;
            case ORACLEAMULET:
                if (this.getHealth().getCost() <= actualMoney) {
                    if (this.actualLife + this.getHealth().getHealth() > MAX_LIFE) {
                        messageOuput = "You have too much life!";
                        return false;
                    }
                    actualMoney -= this.getHealth().getCost();
                    purchasedItems.add(i);
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
    public int moneyLeft() {
        return actualMoney;
    }

    /**
     * @return .
     */
    public Item getArthemideBow() {
        return new ItemBuilder.Builder(Items.ARTHEMIDEBOW, 2).addDemage(5).build();
    }

    /**
     * @return Item with features of HermesBoots
     */
    public Item getHermesBoots() {
        return new ItemBuilder.Builder(Items.HERMESBOOTS, 1).addSpeed(3).build();
    }

    /**
     * @return Item with features of ZeusBolt
     */
    public Item getZeusBolt() {
        return new ItemBuilder.Builder(Items.ZEUSBOLT, 1).addSpeedAttack(4).build();
    }

    /**
     * @return Item with features more Health
     */
    public Item getHealth() {
        return new ItemBuilder.Builder(Items.HEALTH, 1).addHelath(2).build();
    }
    /**
     * 
     * @return .
     */
    public Item getOracleAmulet() {
        return new ItemBuilder.Builder(Items.ORACLEAMULET, 6).addDemage(1).addSpeed(1).addSpeed(1).addSpeedAttack(1).addHelath(2).build();
    }

}
