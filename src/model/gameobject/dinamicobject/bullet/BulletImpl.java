package model.gameobject.dinamicobject.bullet;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;

public class BulletImpl extends AbstractDinamicObject implements Bullet {

    private double damage;

    public BulletImpl(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final double damage) {
        super(id, speed, position, direction, gameObjectType);
        this.damage = damage;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

  /*  @Override
    public void setDamage(double damage) {
        this.damage = damage;
    } */

    @Override
    public void updateState(final double elapsed) {
        // TODO Auto-generated method stub

    }

    @Override
    public void move(final double elapsed) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setDamage(final double damage) {
        this.damage = damage;
    }

    @Override
    public void collideWith(final GameObject obj2) {
        // TODO Auto-generated method stub
    }

}
