package model.shop;

public final class ItemBuilder implements Item {

    private final Items name;

    private int cost;

    private final double damage;

    private final int speed;

    private final int shootDelay;

    private final double health;

    private ItemBuilder(final Items name, final int cost, final double damage, final int speed, final int shootDelay, final double health) {
        super();
        this.name = name;
        this.setCost(cost);
        this.damage = damage;
        this.speed = speed;
        this.shootDelay = shootDelay;
        this.health = health;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void setCost(final int cost) {
        this.cost = cost;
    }
    @Override
    public Items getName() {
        return this.name;
    }
    @Override
    public double getHealth() {
        return health;
    }
    @Override
    public int getSpeed() {
        return this.speed;
    }
    @Override
    public int getSpeedHattack() {
        return this.shootDelay;
    }
    @Override
    public double getDamage() {
        return this.damage;
    }

    public static class Builder {

        private final Items name;
        private final int cost;
        private final int speed;
        private double damage;
        private int shootDelay;
        private double health;

        public Builder(final Items name, final int cost) {
            this.name = name;
            this.cost = cost;
            this.damage = 0;
            this.speed = 0;
            this.shootDelay = 0;
            this.health = 0;
        }

        /**
         * @param health
         * @return this
         */
        public Builder addHelath(final double health) {
            this.health = health;
            return this;
        }
        /**
         * 
         * @param damage
         * @return .
         */
        public Builder addDemage(final double damage) {
            this.damage = damage;
            return this;
        }
        /**
         * 
         * @param speed
         * @return .
         */
        public Builder addSpeed(final int speed) {
            this.damage = speed;
            return this;
        }
        /**
         * 
         * @param speedAttack
         * @return .
         */
        public Builder addSpeedAttack(final int shootDelay) {
            this.shootDelay = shootDelay;
            return this;
        }
        /**
         * @return Item
         */
        public Item build() {
            return new ItemBuilder(this.name, this.cost, this.damage, this.speed, this.shootDelay, this.health);
        }
    }

}
