package model.gameobject.dinamicobject.enemy;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.bullet.Bullet;

public abstract class AbstractEnemy extends AbstractDinamicObject implements Enemy {

    public AbstractEnemy(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType) {
        super(id, speed, position, direction, gameObjectType);
    }

    private double life;

    /**
     * 
     */
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
