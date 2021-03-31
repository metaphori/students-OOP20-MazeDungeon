package model.shop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.gameobject.dynamicobject.maincharacter.MainCharacter;
/**
 * 
 * Create a Shop items.
 * Manages the purchases made and takes care of adding skills to the mainCharacter.
 *
 */
public class ShopImpl implements Shop {
    private static final double MORE_HEALTH = 20;
    private static final int MORE_DAMAGE = 5;
    private static final int MORE_SPEED = 40;
    private static final int MORE_BULLETSPEED = 40;
    private static final int PRICE_ARTHEMIDEBOW = 4;
    private static final int PRICE_HERMESBOOTS = 3;
    private static final int PRICE_ZEUSBOLT = 4;
    private static final int PRICE_HEALTH = 2;
    private static final int PRICE_ORACLEAMULET = 7;

    private final Set<Items> purchasedItems = new HashSet<>();
    private final Set<Items> cart = new HashSet<>();
    private String messageOuput = "";
    private final String msgBought;
    private final String msgNoMoney;
    private final MainCharacter mainCharacter;

    /**
     * this will save the mainCharacter object to then add abilities.
     * Set messages for output.
     * @param mainCharacter
     */
    public ShopImpl(final MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
        msgBought = "You bought this item! You have coins: ";
        msgNoMoney = "You don't have enough coins!";
    }

    private void addSkills(final Item item) {
        this.mainCharacter.increaseDamage(item.getDamage());
        this.mainCharacter.increaseSpeed(item.getSpeed());
        this.mainCharacter.increaseBulletSpeed(item.getBulletSpeed());
        if (this.mainCharacter.getLife() + item.getHealth() > this.mainCharacter.getMaxLife()) {
            this.mainCharacter.setLife(this.mainCharacter.getMaxLife());
        } else {
            this.mainCharacter.setLife(this.mainCharacter.getLife() + item.getHealth());
        }
    }

    private void setItem(final Item item) {
        this.mainCharacter.setMoney(this.mainCharacter.getMoney() - item.getCost());
        this.purchasedItems.add(item.getName());
        this.cart.add(item.getName());
        this.messageOuput = this.msgBought  + this.mainCharacter.getMoney();
    }

    /** 
     *
     * {@inheritDoc}
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
     *
     * {@inheritDoc}
     */
    public void checkItem(final Items i) {
        if (this.purchasedItems.contains(i)) {
            this.messageOuput = "You already have this item";
        } else {
            switch (i) {
                case ARTHEMIDEBOW:
                    if (this.getArthemideBow().getCost() > this.mainCharacter.getMoney()) {
                        this.messageOuput = this.msgNoMoney;
                        break;
                    }
                    this.setItem(this.getArthemideBow());
                    this.addSkills(this.getArthemideBow());
                    break;
                case HERMESBOOTS:
                    if (this.getHermesBoots().getCost() > this.mainCharacter.getMoney()) {
                        this.messageOuput = this.msgNoMoney;
                        break;
                    }
                    this.setItem(this.getHermesBoots());
                    this.addSkills(this.getHermesBoots());
                    break;
                case ZEUSBOLT:
                    if (this.getZeusBolt().getCost() > this.mainCharacter.getMoney()) {
                        this.messageOuput = this.msgNoMoney;
                        break;
                    }
                    this.setItem(this.getZeusBolt());
                    this.addSkills(getZeusBolt());
                    break;
                case HEALTH:
                    if (this.getHealth().getCost() > this.mainCharacter.getMoney()) {
                        messageOuput = msgNoMoney;
                        break;
                    }
                    if (this.mainCharacter.getLife() == this.mainCharacter.getMaxLife()) {
                        this.messageOuput = "You have too much life!";
                        break;
                    }
                    this.mainCharacter.setMoney(this.mainCharacter.getMoney() - this.getHealth().getCost());
                    this.addSkills(getHealth());
                    this.messageOuput = this.msgBought + this.mainCharacter.getMoney();
                    break;
                case ORACLEAMULET:
                    if (this.getOracleAmulet().getCost() > this.mainCharacter.getMoney()) {
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
     *
     * {@inheritDoc}
     */
    public String getMessageOuput() {
        return messageOuput;
    }

    /**
     *
     * {@inheritDoc}
     */
    public Item getArthemideBow() {
        return new ItemBuilder.Builder(Items.ARTHEMIDEBOW, PRICE_ARTHEMIDEBOW).addDamage(MORE_DAMAGE).build();
    }

    /**
     *
     * {@inheritDoc}
     */
    public Item getHermesBoots() {
        return new ItemBuilder.Builder(Items.HERMESBOOTS, PRICE_HERMESBOOTS).addSpeed(MORE_SPEED).build();
    }

    /**
     *
     * {@inheritDoc}
     */
    public Item getZeusBolt() {
        return new ItemBuilder.Builder(Items.ZEUSBOLT, PRICE_ZEUSBOLT).addBulletSpeed(MORE_BULLETSPEED).build();
    }

    /**
     *
     * {@inheritDoc}
     */
    public Item getHealth() {
        return new ItemBuilder.Builder(Items.HEALTH, PRICE_HEALTH).addHelath(MORE_HEALTH).build();
    }

    /**
     *
     * {@inheritDoc}
     */
    public Item getOracleAmulet() {
        return new ItemBuilder.Builder(Items.ORACLEAMULET, PRICE_ORACLEAMULET).addDamage(MORE_DAMAGE).addSpeed(MORE_SPEED).addBulletSpeed(MORE_BULLETSPEED).build();
    }

    /**
     * {@inheritDoc}
     */
    public void clearCart() {
        this.cart.clear();
    }

    /**
     *
     * {@inheritDoc}
     */
    public Set<Items> getCart() {
        return Set.copyOf(this.cart);
    }

}
