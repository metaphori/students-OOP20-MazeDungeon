package model.shop;

import java.util.HashSet;
import java.util.Set;

import model.gameobject.dinamicobject.character.Character;
import mvc.Model;


public class ShopImpl implements Shop {
    private static final double MORE_HEALTH = 0.5;
    private static final double MORE_DAMAGE = 0.5;
    private static final int MORE_SPEED = 50;
    private static final int MORE_BULLETSPEED = 40;
    private static final int PRICE_ARTHEMIDEBOW = 3;
    private static final int PRICE_HERMESBOOTS = 2;
    private static final int PRICE_ZEUSBOLT = 4;
    private static final int PRICE_HEALTH = 2;
    private static final int PRICE_ORACLEAMULET = 7;

    private final Set<Items> purchasedItems = new HashSet<>();
    private String messageOuput = "";

    private final String msgBought;
    private final String msgNoMoney;
    private int actualMoney;
    private final double actualLife;
    private final double maxLife;

    private final Model model;

    public ShopImpl(final Model model, final double maxLife, final double actualLife, final int actualMoney) {
        this.model = model;
        this.maxLife = maxLife;
        //this.actualMoney = actualMoney;
        msgBought = "You bought this item! You have: " + actualMoney + " money";
        msgNoMoney = "You don't have enough coins!";
        this.actualLife = actualLife;
        this.actualMoney = actualMoney;
    }
    /**
     * @return .
     */
    private void addSkills(Item item) {
        final Character c = this.model.getRoomManager().getCurrentRoom().getCharacter().get();
        c.setDamage((int)c.getDamage() + (int)item.getDamage());///////////
        c.setSpeed(c.getSpeed() + item.getSpeed());
        c.setBulletSpeed(c.getBulletSpeed() + item.getBulletSpeed());
        //c.setLife(item.getHealth())
        c.setMoney(actualMoney);
        //c.addItem(item);

    }

    public boolean checkItem(final Items i) {
        if (this.purchasedItems.contains(i)) {
            this.messageOuput = "You already have this item";
            return false;
        }
        /*if (actualMoney == 0) {
            messageOuput = msgNoMoney;
            return false;
        }*/
        switch (i) {
            case ARTHEMIDEBOW:
                if (this.getArthemideBow().getCost() <= this.actualMoney) {
                    this.actualMoney -= this.getArthemideBow().getCost();
                    this.purchasedItems.add(i);
                    this.addSkills(this.getArthemideBow());
                    this.messageOuput = this.msgBought  + this.actualMoney;
                    return true;
                }
                this.messageOuput = this.msgNoMoney;
                break;
            case HERMESBOOTS:
                if (this.getHermesBoots().getCost() <= this.actualMoney) {
                    this.actualMoney -= this.getHermesBoots().getCost();
                    this.purchasedItems.add(i);
                    this.addSkills(this.getHermesBoots());
                    this.messageOuput = this.msgBought + this.actualMoney;
                    return true;
                }
                this.messageOuput = this.msgNoMoney;
                break;
            case ZEUSBOLT:
                if (this.getZeusBolt().getCost() <= this.actualMoney) {
                    this.actualMoney -= this.getZeusBolt().getCost();
                    this.purchasedItems.add(i);
                    this.addSkills(getZeusBolt());
                    this.messageOuput = this.msgBought + this.actualMoney;
                    return true;
                }
                this.messageOuput = this.msgNoMoney;
                break;
            case HEALTH:
                if (this.getHealth().getCost() <= actualMoney) {
                    if (this.actualLife + this.getHealth().getHealth() > this.maxLife) {
                        this.messageOuput = "You have too much life!";
                        return false;
                    }
                    this.actualMoney -= this.getHealth().getCost();
                    this.addSkills(getHealth());
                    this.messageOuput = this.msgBought + this.actualMoney;
                    return true;
                }
                messageOuput = msgNoMoney;
                break;
            case ORACLEAMULET:
                if (this.getHealth().getCost() <= this.actualMoney) {
                    if (this.actualLife + this.getHealth().getHealth() > maxLife) {
                        this.messageOuput = "You have too much life!";
                        return false;
                    }
                    this.actualMoney -= this.getHealth().getCost();
                    this.purchasedItems.add(i);
                    this.addSkills(this.getOracleAmulet());
                    this.messageOuput = this.msgBought + this.actualMoney;
                    return true;
                }
                this.messageOuput = this.msgNoMoney;
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
    /*public int moneyLeft() {
        return actualMoney;
    }*/

    /**
     * @return .
     */
    public Item getArthemideBow() {
        return new ItemBuilder.Builder(Items.ARTHEMIDEBOW, PRICE_ARTHEMIDEBOW).addDamage(MORE_DAMAGE).build();
    }

    /**
     * @return Item with features of HermesBoots
     */
    public Item getHermesBoots() {
        return new ItemBuilder.Builder(Items.HERMESBOOTS, PRICE_HERMESBOOTS).addSpeed(MORE_SPEED).build();
    }

    /**
     * @return Item with features of ZeusBolt
     */
    public Item getZeusBolt() {
        return new ItemBuilder.Builder(Items.ZEUSBOLT, PRICE_ZEUSBOLT).addBulletSpeed(MORE_BULLETSPEED).build();
    }

    /**
     * @return Item with features more Health
     */
    public Item getHealth() {
        return new ItemBuilder.Builder(Items.HEALTH, PRICE_HEALTH).addHelath(MORE_HEALTH).build();
    }
    /**
     * 
     * @return .
     */
    public Item getOracleAmulet() {
        return new ItemBuilder.Builder(Items.ORACLEAMULET, PRICE_ORACLEAMULET).addDamage(MORE_DAMAGE).addSpeed(MORE_SPEED).addBulletSpeed(MORE_BULLETSPEED).addHelath(MORE_HEALTH).build();
    }

}
