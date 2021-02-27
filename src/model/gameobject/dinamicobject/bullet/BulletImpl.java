package model.gameobject.dinamicobject.bullet;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.enemy.Enemy;
import model.gameobject.dinamicobject.character.Character;

public class BulletImpl extends AbstractDinamicObject implements Bullet {

    private double damage;

    public BulletImpl(final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final double damage) {
        super(speed, position, gameObjectType);
        this.damage = damage;
        this.setDirection(direction);
    }

    /**
     * 
     */
    @Override
    public double getDamage() {
        return this.damage;
    }

    /**
     * 
     */
    @Override
    public void updateState(final double elapsed) {
        this.move(elapsed);
    }

    /**
     * 
     */
    @Override
    public void setDamage(final double damage) {
        this.damage = damage;
    }

    /**
     * 
     */
    @Override
    public void collideWith(final GameObject obj2) {
        switch (obj2.getGameObjectType().getCollisionType()) {
        case OBSTACLE:
            this.getRoom().deleteGameObject(this);
            break;
        case ENTITY:
            if (obj2.getGameObjectType().equals(GameObjectType.CHARACTER) && !this.getGameObjectType().equals(GameObjectType.CHARACTER_BULLET)) {
                final Character character = (Character) obj2;
                character.takesDamage(this.getDamage());
                this.getRoom().deleteGameObject(this);
            } else if (!obj2.getGameObjectType().equals(GameObjectType.CHARACTER) && this.getGameObjectType().equals(GameObjectType.CHARACTER_BULLET)) {
                final Enemy enemy = (Enemy) obj2;
                enemy.takesDamage(this.getDamage());
                this.getRoom().deleteGameObject(this);
            }
        default:
            break;
        }
    }
}
