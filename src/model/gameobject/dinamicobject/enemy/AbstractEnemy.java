package model.gameobject.dinamicobject.enemy;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.bullet.Bullet;
import model.room.Room;

public abstract class AbstractEnemy extends AbstractDinamicObject implements Enemy {

    private double life;

    public AbstractEnemy(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, Room room) {
        super(id, speed, position, direction, gameObjectType, room);
    }

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

    /**
     * 
     */
    @Override
    public void collideWith(final GameObject obj2) {
        switch (obj2.getGameObjectType().getCollisionType()) {
        case OBSTACLE:
        case ENTITY:
            this.setPosition(this.getLastPosition());
            this.changeRoutine();
            break;

        default:
            break;
        }
    }

    protected abstract void changeRoutine();

    @Override
    public abstract Bullet shoot();
}
