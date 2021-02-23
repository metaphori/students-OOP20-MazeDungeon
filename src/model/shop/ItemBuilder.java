package model.shop;

import java.util.Optional;

public class ItemBuilder {

    private String name;

    private int cost;

    private int demage;

    private int speed;

    private int speedAttack;

    private  Optional<Integer> health;

    private ItemBuilder(final String name, final int cost, final int demage, final int speed, final int speedAttack, final Optional<Integer> health) {
        super();
        this.name = name;
        this.setCost(cost);
        this.demage = demage;
        this.speed = speed;
        this.speedAttack = speedAttack;
        this.health = health;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Optional<Integer> getHealth() {
        return health;
    }

    public static class Builder {

        private final String name;
        private final int cost;
        private int demage;
        private int speed;
        private int speedAttack;
        private  Optional<Integer> health = Optional.empty();

        public Builder(final String name, final int cost) {
            this.name = name;
            this.cost = cost;
            this.demage = 0;
            this.speed = 0;
            this.speedAttack = 0;
        }

        /**
         * @param health
         * @return this
         */
        public Builder addHelath(final int health) {
            this.health = Optional.of(health);
            return this;
        }

        public Builder addDemage(final int demage) {
            this.demage = demage;
            return this;
        }
        public Builder addSpeed(final int speed) {
            this.demage = speed;
            return this;
        }
        public Builder addSpeedAttack(final int speedAttack) {
            this.speedAttack = speedAttack;
            return this;
        }
        /**
         * @return Item
         */
        public ItemBuilder build() {
            return new ItemBuilder(this.name, this.cost, this.demage, this.speed, this.speedAttack, this.health);
        }
    }

}
