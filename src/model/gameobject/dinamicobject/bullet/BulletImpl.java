package model.gameobject.dinamicobject.bullet;

import model.gameobject.dinamicobject.AbstractDinamicObject;

public class BulletImpl extends AbstractDinamicObject implements Bullet {

    /**
     * 
     */
    private double damage;

    public BulletImpl(double damage) {
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
    public void setSpeed() {
        // TODO Auto-generated method stub

    }

}
