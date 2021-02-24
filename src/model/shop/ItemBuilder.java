package model.shop;

import java.util.Optional;

public class ItemBuilder implements Item {

    private String name;

    private int cost;

    private int demage;

    private int speed;

    private int speedAttack;

    //private  Optional<Integer> health;
    private  int health;

    private ItemBuilder(final String name, final int cost, final int demage, final int speed, final int speedAttack, final int health) {
        super();
        this.name = name;
        this.setCost(cost);
        this.demage = demage;
        this.speed = speed;
        this.speedAttack = speedAttack;
        this.health = health;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public int getSpeed() {
        return this.speed;
    }
    @Override
    public int getSpeedHattack() {
        return this.speedAttack;
    }
    @Override
    public int getDemage() {
        return this.demage;
    }

    public static class Builder {

        private final String name;
        private final int cost;
        private int demage;
        private int speed;
        private int speedAttack;
        private int health;

        public Builder(final String name, final int cost) {
            this.name = name;
            this.cost = cost;
            this.demage = 0;
            this.speed = 0;
            this.speedAttack = 0;
            this.health = 0;
        }

        /**
         * @param health
         * @return this
         */
        public Builder addHelath(final int health) {
            this.health = health;
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
        public Item build() {
            return new ItemBuilder(this.name, this.cost, this.demage, this.speed, this.speedAttack, this.health);
        }
    }

}
