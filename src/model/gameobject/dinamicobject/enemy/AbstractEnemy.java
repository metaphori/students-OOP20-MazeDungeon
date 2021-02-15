package model.gameobject.dinamicobject.enemy;

import model.common.Point2D;
import model.gameobject.AbstractGameObject;

public abstract class AbstractEnemy extends AbstractGameObject implements Enemy {
    private double life;

    public AbstractEnemy(final int id, final Point2D position) {
        super(id, position);
    }

    /**
     * @return the life of the enemy
     */
    public double getLife() {
        return this.life;
    }

    public void takeDamage(final double damage) {

    }

    public void notifyDropCoin() {

    }

    public abstract void shoot();

    public abstract void move();
}
