package model.gameobject.dinamicobject.bullet;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.room.Room;

public class BulletImpl extends AbstractDinamicObject implements Bullet {

    private double damage;

    public BulletImpl(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final double damage, final Room room) {
        super(id, speed, position, direction, gameObjectType, room);
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
        // TODO Auto-generated method stub
    }

}
