package model.gameobject.dinamicobject.enemy;

import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.bullet.Bullet;

public abstract class AbstractEnemy extends AbstractDinamicObject implements Enemy {
    private double life;

    @Override
    public double getLife() {
        return this.life;
    }

    @Override
    public void takesDamage(final int damage) {
        //TODO
    }

    @Override
    public void notifyDropCoin() {
      //TODO
    }

    @Override
    public abstract void move();

    @Override
    public abstract Bullet shoot();
}
