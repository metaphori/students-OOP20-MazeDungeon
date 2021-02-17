package model.shop;

import java.util.Optional;

public final class Item {

    private String name;

    private int cost;

    private int demage;

    private int speed;

    private int speedAttack;

    private  Optional<Integer> health;

    private Item(final String name, final int cost, final int demage, final int speed, final int speedAttack, final Optional<Integer> health) {
        super();
        this.name = name;
        this.cost = cost;
        this.demage = demage;
        this.speed = speed;
        this.speedAttack = speedAttack;
        this.health = health;
    }

    public class Builder {

        private final String name;
        private final int cost;
        private final int demage;
        private final int speed;
        private final int speedAttack;
        private  Optional<Integer> health = Optional.empty();

        public Builder(final String name, final int cost, final int demage, final int speed, final int speedAttack) {
            this.name = name;
            this.cost = cost;
            this.demage = demage;
            this.speed = speed;
            this.speedAttack = speedAttack;
        }

        /**
         * @param health
         * @return this
         */
        public Builder addHelath(final int health) {
            this.health = Optional.of(health);
            return this;
        }

        /**
         * @return Item
         */
        public Item build() {
            return new Item(this.name, this.cost, this.demage, this.speed, this.speedAttack, this.health);
        }
    }

}
