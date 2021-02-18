package model.gameobject.dinamicobject.bullet;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.AbstractDinamicObject;

public class BulletImpl extends AbstractDinamicObject implements Bullet {

    private double damage;

    public BulletImpl(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType) {
        super(id, speed, position, direction, gameObjectType);
        this.damage = damage; 
    }


    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public void updateState() {
        // TODO Auto-generated method stub
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
    }

    @Override
    public void setDamage(double damage) {
        // TODO Auto-generated method stub
    }

}
