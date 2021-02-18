package model.gameobject.dinamicobject.bullet;

import model.common.BulletType;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.AbstractDinamicObject;

public class BulletFactoryImpl implements BulletFactory {

    /*@Override
    public Bullet createBullet(final BulletType bulletType, final Point2D initialPosition, final Vector2D direction) {
        double damage = 0;
        int speed = 0;

        switch (bulletType) {
            case CHARACTER_BULLET:
                damage = 100; // un proiettile sprato contro nemico, toglie tutta la vita del nemico
                break;
            case ENEMY_BULLET:
                damage = 25; // un bullet di un nemico ha un danno di 25
                break;
            default:
                break;
        }
        return new BulletImpl(0, speed, initialPosition, direction, GameObjectType.BULLET);
    }


    private class BulletImpl extends AbstractDinamicObject implements Bullet {

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
        public void setDamage(double damage) {
            this.damage = damage;
        }

        @Override
        public void updateState() {
            // TODO Auto-generated method stub
        }

        @Override
        public void move() {
            // TODO Auto-generated method stub
        }

    }*/
}
