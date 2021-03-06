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
    private static final int PRICE_ARTHEMIDEBOW = 1;
    private static final int PRICE_HERMESBOOTS = 1;
    private static final int PRICE_ZEUSBOLT = 1;
    private static final int PRICE_HEALTH = 1;
    private static final int PRICE_ORACLEAMULET = 1;

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

    private void setItem(final Item item) {
        this.character.setMoney(this.character.getMoney() - item.getCost());
        this.purchasedItems.add(item.getName());
        this.cart.add(item.getName());
        this.messageOuput = this.msgBought  + this.character.getMoney();
    }

    /** 
     * @return a map: every Item with its price 
     */
    public Map<Items, Integer> addPrice() {
        final Map<Items, Integer> mapPrice = new HashMap<>();
        mapPrice.put(Items.ARTHEMIDEBOW, PRICE_ARTHEMIDEBOW);
        mapPrice.put(Items.HERMESBOOTS, PRICE_HERMESBOOTS);
        mapPrice.put(Items.ZEUSBOLT, PRICE_ZEUSBOLT);
        mapPrice.put(Items.HEALTH, PRICE_HEALTH);
        mapPrice.put(Items.ORACLEAMULET, PRICE_ORACLEAMULET);
        return mapPrice;
    }
    /**
     * @param i : item selected, to check if the item is affordable
     */
    public void checkItem(final Items i) {
        if (this.purchasedItems.contains(i)) {
            this.messageOuput = "You already have this item";
        } else {
            switch (i) {
                case ARTHEMIDEBOW:
                    if (this.getArthemideBow().getCost() > this.character.getMoney()) {
                        this.messageOuput = this.msgNoMoney;
                        break;
                    }
                    this.setItem(this.getArthemideBow());
                    this.addSkills(this.getArthemideBow());
                    break;
                case HERMESBOOTS:
                    if (this.getHermesBoots().getCost() > this.character.getMoney()) {
                        this.messageOuput = this.msgNoMoney;
                        break;
                    }
                    this.setItem(this.getHermesBoots());
                    this.addSkills(this.getHermesBoots());
                    break;
                case ZEUSBOLT:
                    if (this.getZeusBolt().getCost() > this.character.getMoney()) {
                        this.messageOuput = this.msgNoMoney;
                        break;
                    }
                    this.setItem(this.getZeusBolt());
                    this.addSkills(getZeusBolt());
                    break;
                case HEALTH:
                    if (this.getHealth().getCost() > this.character.getMoney()) {
                        messageOuput = msgNoMoney;
                        break;
                    }
                    if (this.character.getLife() == this.character.getMaxLife()) {
                        this.messageOuput = "You have too much life!";
                        break;
                    }
                    this.character.setMoney(this.character.getMoney() - this.getHealth().getCost());
                    this.addSkills(getHealth());
                    this.messageOuput = this.msgBought + this.character.getMoney();
                    break;
                case ORACLEAMULET:
                    if (this.getOracleAmulet().getCost() > this.character.getMoney()) {
                        this.messageOuput = this.msgNoMoney;
                        break;
                    }
                    this.setItem(this.getOracleAmulet());
                    this.addSkills(this.getOracleAmulet());
                    break;
                 default:
                     messageOuput = "ERROR!";
            }
        }
    }

    /**
     * @return string for output message
     */
    public String getMessageOuput() {
        return messageOuput;
    }

    /**
     * @return ArthemideBow Item
     */
    public Item getArthemideBow() {
        return new ItemBuilder.Builder(Items.ARTHEMIDEBOW, PRICE_ARTHEMIDEBOW).addDamage(MORE_DAMAGE).build();
    }

    /**
     * @return HermesBoots Item
     */
    public Item getHermesBoots() {
        return new ItemBuilder.Builder(Items.HERMESBOOTS, PRICE_HERMESBOOTS).addSpeed(MORE_SPEED).build();
    }

    /**
     * @return ZeusBolt Item
     */
    public Item getZeusBolt() {
        return new ItemBuilder.Builder(Items.ZEUSBOLT, PRICE_ZEUSBOLT).addBulletSpeed(MORE_BULLETSPEED).build();
    }

    /**
     * @return Health Item
     */
    public Item getHealth() {
        return new ItemBuilder.Builder(Items.HEALTH, PRICE_HEALTH).addHelath(MORE_HEALTH).build();
    }
    /**
     * 
     * @return Oracle Amulet Item
     */
    public Item getOracleAmulet() {
        return new ItemBuilder.Builder(Items.ORACLEAMULET, PRICE_ORACLEAMULET).addDamage(MORE_DAMAGE).addSpeed(MORE_SPEED).addBulletSpeed(MORE_BULLETSPEED).build();
    }
    /**
     * empty the current cart.
     */
    public void clearCart() {
        this.cart.clear();
    }

    /**
     * 
     * @return a copy of current cart
     */
    public Set<Items> getCart() {
        return Set.copyOf(this.cart);
    }

}
