package model.shop;
/**
 * 
 * Create the Item and set the parameters that are passed. 
 * Uses the Pattern Builder.
 *
 */
public final class ItemBuilder implements Item {
    private final Items name;
    private int cost;
    private final int damage;
    private final int speed;
    private final int bulletSpeed;
    private final double health;

    private ItemBuilder(final Items name, final int cost, final int damage, final int speed, final int bulletSpeed, final double health) {
        this.name = name;
        this.setCost(cost);
        this.damage = damage;
        this.speed = speed;
        this.bulletSpeed = bulletSpeed;
        this.health = health;
    }
    /**
     * {@inheritDoc}
     *
     */
    public int getCost() {
        return cost;
    }
    /**
     * {@inheritDoc}
     *
     */
    public void setCost(final int cost) {
        this.cost = cost;
    }
    /**
     * {@inheritDoc}
     *
     */
    public Items getName() {
        return this.name;
    }
    /**
     * {@inheritDoc}
     * 
     */
    public double getHealth() {
        return health;
    }
    /**
     * {@inheritDoc}
     *
     */
    public int getSpeed() {
        return this.speed;
    }
    /**
     * {@inheritDoc}
     *
     */
    public int getBulletSpeed() {
        return this.bulletSpeed;
    }
    /**
     * {@inheritDoc}
     *
     */
    public int getDamage() {
        return this.damage;
    }

    public static class Builder {

        private final Items name;
        private final int cost;
        private int speed;
        private int damage;
        private int bulletSpeed;
        private double health;
        /**
         * set the required parameters of the Items.
         * While the remaining values with a default value.
         * @param name
         * @param cost
         */
        public Builder(final Items name, final int cost) {
            this.name = name;
            this.cost = cost;
            this.damage = 0;
            this.speed = 0;
            this.bulletSpeed = 0;
            this.health = 0;
        }

        /**
         * @param health : value that increase the health
         * @return this, for create Item
         */
        public Builder addHelath(final double health) {
            this.health = health;
            return this;
        }
        /**
         * 
         * @param damage : value that increase the damage
         * @return this, for create Item
         */
        public Builder addDamage(final int damage) {
            this.damage = damage;
            return this;
        }
        /**
         * 
         * @param speed : value that increase the Speed
         * @return this, for create Item
         */
        public Builder addSpeed(final int speed) {
            this.speed = speed;
            return this;
        }
        /**
         * @param bulletSpeed : value that increase the Bullet Speed
         * @return this, for create Item
         */
        public Builder addBulletSpeed(final int bulletSpeed) {
            this.bulletSpeed = bulletSpeed;
            return this;
        }
        /**
         * Complete and create Item.
         * @return Item
         */
        public Item build() {
            return new ItemBuilder(this.name, this.cost, this.damage, this.speed, this.bulletSpeed, this.health);
        }
    }

}
