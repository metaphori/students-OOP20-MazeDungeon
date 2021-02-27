package model.shop;

import java.util.HashSet;
import java.util.Set;

public class ShopImpl implements Shop {

    private final Set<Items> purchasedItems = new HashSet<>();

    private String messageOuput = "";

    private final String msgBought;
    private final String msgNoMoney;
    private int actualMoney;
    private double actualLife;
    private final double maxLife;

    public ShopImpl(final double maxLife, final double actualLife, int actualMoney) {
        this.maxLife = maxLife;
        //this.actualMoney = actualMoney;
        msgBought = "You bought this item! You have: ";
        msgNoMoney = "You don't have enough coins!";
        this.actualLife = actualLife;
        this.actualMoney = actualMoney;
    }
    /**
     * @return .
     */
    public boolean checkItem(final Items i) {
        if (purchasedItems.contains(i)) {
            messageOuput = "You already have this item";
            return false;
        }
        /*if (actualMoney == 0) {
            messageOuput = msgNoMoney;
            return false;
        }*/
        switch (i) {
            case ARTHEMIDEBOW:
                if (this.getArthemideBow().getCost() <= actualMoney) {
                    actualMoney -= this.getArthemideBow().getCost();
                    purchasedItems.add(i);
                    messageOuput = msgBought  + actualMoney;
                    return true;
                }
                messageOuput = msgNoMoney;
                break;
            case HERMESBOOTS:
                if (this.getHermesBoots().getCost() <= actualMoney) {
                    actualMoney -= this.getHermesBoots().getCost();
                    purchasedItems.add(i);
                    messageOuput = msgBought + actualMoney;
                    return true;
                }
                messageOuput = msgNoMoney;
                break;
            case ZEUSBOLT:
                if (this.getZeusBolt().getCost() <= actualMoney) {
                    actualMoney -= this.getZeusBolt().getCost();
                    purchasedItems.add(i);
                    messageOuput = msgBought + actualMoney;
                    return true;
                }
                messageOuput = msgNoMoney;
                break;
            case HEALTH:
                if (this.getHealth().getCost() <= actualMoney) {
                    if (this.actualLife + this.getHealth().getHealth() > maxLife) {
                        messageOuput = "You have too much life!";
                        return false;
                    }
                    actualMoney -= this.getHealth().getCost();
                    messageOuput = msgBought + actualMoney;
                    return true;
                }
                messageOuput = msgNoMoney;
                break;
            case ORACLEAMULET:
                if (this.getHealth().getCost() <= actualMoney) {
                    if (this.actualLife + this.getHealth().getHealth() > maxLife) {
                        messageOuput = "You have too much life!";
                        return false;
                    }
                    actualMoney -= this.getHealth().getCost();
                    purchasedItems.add(i);
                    messageOuput = msgBought + actualMoney;
                    return true;
                }
                messageOuput = msgNoMoney;
                break;
             default:
                 messageOuput = "ERROR!";
                 return false;

        }
        return false;
    }

    /**
     * @return .
     */
    public String getMessageOuput() {
        return messageOuput;
    }
    /**
     * @return .
     */
    public int moneyLeft() {
        return actualMoney;
    }

    /**
     * @return .
     */
    public Item getArthemideBow() {
        return new ItemBuilder.Builder(Items.ARTHEMIDEBOW, 1).addDemage(5).build();
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
