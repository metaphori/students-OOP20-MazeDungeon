package model.shop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.gameobject.dinamicobject.character.Character;

public class ShopImpl implements Shop {
    private static final double MORE_HEALTH = 25;
    private static final int MORE_DAMAGE = 15;
    private static final int MORE_SPEED = 150;
    private static final int MORE_BULLETSPEED = 3;
    private static final int PRICE_ARTHEMIDEBOW = 0;
    private static final int PRICE_HERMESBOOTS = 3;
    private static final int PRICE_ZEUSBOLT = 3;
    private static final int PRICE_HEALTH = 2;
    private static final int PRICE_ORACLEAMULET = 7;

    private final Set<Items> purchasedItems = new HashSet<>();
    private final Set<Items> cart = new HashSet<>();
    private String messageOuput = "";

    private final String msgBought;
    private final String msgNoMoney;
    private final Character character;
    public ShopImpl(final Character character) {
        this.character = character;
        msgBought = "You bought this item! You have coins: ";
        msgNoMoney = "You don't have enough coins!";
    }
    /**
     * @return .
     */
    private void addSkills(final Item item) {
        this.character.setBonusDamage(this.character.getBonusDamage() + item.getDamage());
        this.character.setSpeed(this.character.getSpeed() + item.getSpeed());
        this.character.setBulletSpeed(this.character.getBulletSpeed() + item.getBulletSpeed());
        if (this.character.getLife() + item.getHealth() > this.character.getMaxLife()) {
            this.character.setLife(this.character.getMaxLife());
        } else {
            this.character.setLife(this.character.getLife() + item.getHealth());
        }
    }
    public Map<Items, Integer> addPrice(){
        final Map<Items, Integer> mapPrice = new HashMap<>();
        mapPrice.put(Items.ARTHEMIDEBOW, PRICE_ARTHEMIDEBOW);
        mapPrice.put(Items.HERMESBOOTS, PRICE_HERMESBOOTS);
        mapPrice.put(Items.ZEUSBOLT, PRICE_ZEUSBOLT);
        mapPrice.put(Items.HEALTH, PRICE_HEALTH);
        mapPrice.put(Items.ORACLEAMULET, PRICE_ORACLEAMULET);
        return mapPrice;
    }
    public boolean checkItem(final Items i) {
        if (this.purchasedItems.contains(i)) {
            this.messageOuput = "You already have this item";
            return false;
        }
        switch (i) {
            case ARTHEMIDEBOW:
                if (this.getArthemideBow().getCost() <= this.character.getMoney()) {
                    this.character.setMoney(this.character.getMoney() - this.getArthemideBow().getCost());
                    this.purchasedItems.add(i);
                    this.cart.add(i);
                    this.addSkills(this.getArthemideBow());
                    this.messageOuput = this.msgBought  + this.character.getMoney();
                    return true;
                }
                this.messageOuput = this.msgNoMoney;
                break;
            case HERMESBOOTS:
                if (this.getHermesBoots().getCost() <= this.character.getMoney()) {
                    this.character.setMoney(this.character.getMoney() - this.getHermesBoots().getCost());
                    this.purchasedItems.add(i);
                    this.cart.add(i);
                    this.addSkills(this.getHermesBoots());
                    this.messageOuput = this.msgBought + this.character.getMoney();
                    return true;
                }
                this.messageOuput = this.msgNoMoney;
                break;
            case ZEUSBOLT:
                if (this.getZeusBolt().getCost() <= this.character.getMoney()) {
                    this.character.setMoney(this.character.getMoney() - this.getZeusBolt().getCost());
                    this.purchasedItems.add(i);
                    this.cart.add(i);
                    this.addSkills(getZeusBolt());
                    this.messageOuput = this.msgBought + this.character.getMoney();
                    return true;
                }
                this.messageOuput = this.msgNoMoney;
                break;
            case HEALTH:
                if (this.getHealth().getCost() <= this.character.getMoney()) {
                    if (this.character.getLife() == this.character.getMaxLife()) {
                        this.messageOuput = "You have too much life!";
                        return false;
                    }
                    this.character.setMoney(this.character.getMoney() - this.getHealth().getCost());
                    this.addSkills(getHealth());
                    this.messageOuput = this.msgBought + this.character.getMoney();
                    return true;
                }
                messageOuput = msgNoMoney;
                break;
            case ORACLEAMULET:
                if (this.getOracleAmulet().getCost() <= this.character.getMoney()) {
                    this.character.setMoney(this.character.getMoney() - this.getOracleAmulet().getCost());
                    this.purchasedItems.add(i);
                    this.cart.add(i);
                    this.addSkills(this.getOracleAmulet());
                    this.messageOuput = this.msgBought + this.character.getMoney();
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
        return new ItemBuilder.Builder(Items.ORACLEAMULET, PRICE_ORACLEAMULET).addDamage(MORE_DAMAGE).addSpeed(MORE_SPEED).addBulletSpeed(MORE_BULLETSPEED).build();
    }
    /**
     * @return .
     */
    public void clearCart() {
        this.cart.clear();
    }
    
    /**
     * 
     */
    @Override
    public Set<Items> getCart() {
        return Set.copyOf(this.cart);
    }

}
