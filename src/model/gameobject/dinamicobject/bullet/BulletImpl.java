package model.gameobject.dinamicobject.bullet;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.room.Room;

public class BulletImpl extends AbstractDinamicObject implements Bullet {

    private double damage;

    public BulletImpl(final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final double damage) {
        super(speed, position, direction, gameObjectType);
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
        this.move(elapsed);
    }

    @Override
    public void setDamage(final double damage) {
        this.damage = damage;
    }

    @Override
    public void collideWith(final GameObject obj2) {
        switch (obj2.getGameObjectType().getCollisionType()) {
        case OBSTACLE:
            this.getRoom().deleteGameObject(this);
            break;
        default:
            break;

        }
    }

}
