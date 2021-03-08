package test;

import static org.junit.Assert.assertEquals;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.dynamicobject.character.Character;
import model.gameobject.dynamicobject.character.CharacterImpl;
import model.shop.Item;
import model.shop.ItemBuilder;
import model.shop.Items;
import model.shop.Shop;
import model.shop.ShopImpl;

public class TestItem {
    private Item item;
    private Character c;
    private int beforeMoney;
    private int beforeSpeed;
    private int beforeBulletSpeed;
    private double beforeHealth;
    private int beforeDamage;
    private static final double MORE_HEALTH = 25;
    private static final int MORE_DAMAGE = 15;
    private static final int MORE_SPEED = 150;
    private static final int MORE_BULLETSPEED = 3;
    private static final int PRICE_ARTHEMIDEBOW = 4;
    private static final int PRICE_HERMESBOOTS = 3;
    private static final int PRICE_ZEUSBOLT = 4;
    private static final int PRICE_HEALTH = 2;
    private static final int PRICE_ORACLEAMULET = 7;

    private void setValue(final Character c) {
        c.setMoney(10);
        c.setLife(c.getLife() - c.getLife() / 2);
        this.beforeMoney = c.getMoney();
        this.beforeBulletSpeed = c.getBulletSpeed();
        this.beforeDamage = c.getBonusDamage();
        this.beforeSpeed = c.getSpeed();
        this.beforeHealth = c.getLife();
    }
/////////////////////////////////////////////////////// controllare assertEquals(double, double)
    @org.junit.Before
    public void newCharacter() {
        c = new CharacterImpl(new Point2D(100, 100), GameObjectType.CHARACTER);
        setValue(c);
    }
    @org.junit.Test
    public void testArthemideBow() {
        item = new ItemBuilder.Builder(Items.ARTHEMIDEBOW, PRICE_ARTHEMIDEBOW).addDamage(MORE_DAMAGE).build();
        final Shop shop = new ShopImpl(c);
        shop.checkItem(Items.ARTHEMIDEBOW);
        assertEquals(c.getMoney(), this.beforeMoney - item.getCost());
        assertEquals(c.getBonusDamage(), this.beforeDamage + item.getDamage());
        assertEquals(c.getSpeed(), this.beforeSpeed + item.getSpeed());
        assertEquals(c.getBulletSpeed(), this.beforeBulletSpeed + item.getBulletSpeed());
        //assertEquals(c.getLife(), this.beforeHealth + item.getHealth());
    }

    @org.junit.Test
    public void testHermesBoots() {
        item = new ItemBuilder.Builder(Items.HERMESBOOTS, PRICE_HERMESBOOTS).addSpeed(MORE_SPEED).build();
        final Shop shop = new ShopImpl(c);
        shop.checkItem(Items.HERMESBOOTS);
        assertEquals(c.getMoney(), this.beforeMoney - item.getCost());
        assertEquals(c.getBonusDamage(), this.beforeDamage + item.getDamage());
        assertEquals(c.getSpeed(), this.beforeSpeed + item.getSpeed());
        assertEquals(c.getBulletSpeed(), this.beforeBulletSpeed + item.getBulletSpeed());
        //assertEquals(c.getLife(), this.beforeHealth + item.getHealth());
    }
    @org.junit.Test
    public void testZeusBolt() {
        item = new ItemBuilder.Builder(Items.ZEUSBOLT, PRICE_ZEUSBOLT).addBulletSpeed(MORE_BULLETSPEED).build();
        final Shop shop = new ShopImpl(c);
        shop.checkItem(Items.ZEUSBOLT);
        assertEquals(c.getMoney(), this.beforeMoney - item.getCost());
        assertEquals(c.getBonusDamage(), this.beforeDamage + item.getDamage());
        assertEquals(c.getSpeed(), this.beforeSpeed + item.getSpeed());
        assertEquals(c.getBulletSpeed(), this.beforeBulletSpeed + item.getBulletSpeed());
        //assertEquals(c.getLife(), this.beforeHealth + item.getHealth());
    }
    @org.junit.Test
    public void testHealth() {
        item = new ItemBuilder.Builder(Items.HEALTH, PRICE_HEALTH).addHelath(MORE_HEALTH).build();
        final Shop shop = new ShopImpl(c);
        shop.checkItem(Items.HEALTH);
        assertEquals(c.getMoney(), this.beforeMoney - item.getCost());
        assertEquals(c.getBonusDamage(), this.beforeDamage + item.getDamage());
        assertEquals(c.getSpeed(), this.beforeSpeed + item.getSpeed());
        assertEquals(c.getBulletSpeed(), this.beforeBulletSpeed + item.getBulletSpeed());
        //assertEquals(c.getLife(), this.beforeHealth + item.getHealth());
    }
    @org.junit.Test
    public void testOracleAmulet() {
        item = new ItemBuilder.Builder(Items.ORACLEAMULET, PRICE_ORACLEAMULET).addDamage(MORE_DAMAGE).addSpeed(MORE_SPEED).addBulletSpeed(MORE_BULLETSPEED).build();
        final Shop shop = new ShopImpl(c);
        shop.checkItem(Items.ORACLEAMULET);
        assertEquals(c.getMoney(), this.beforeMoney - item.getCost());
        assertEquals(c.getBonusDamage(), this.beforeDamage + item.getDamage());
        assertEquals(c.getSpeed(), this.beforeSpeed + item.getSpeed());
        assertEquals(c.getBulletSpeed(), this.beforeBulletSpeed + item.getBulletSpeed());
        //assertEquals(c.getLife(), this.beforeHealth + item.getHealth());
    }

}
