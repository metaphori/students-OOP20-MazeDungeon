package model.gameobject.dynamicobject.bullet;

import model.common.BoundingBox;
import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.AbstractDynamicObject;
import model.gameobject.dynamicobject.character.Character;
import model.gameobject.dynamicobject.enemy.Enemy;
import model.gameobject.simpleobject.Wall;

public class BulletImpl extends AbstractDynamicObject implements Bullet {

    private BoundingBox safeZone;
    private int damage;

    public BulletImpl(final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final int damage, final int speed) {
        super(speed, position, gameObjectType);
        this.damage = damage;
        this.setDirection(direction);
    }

    /**
     * 
     */
    @Override
    public int getDamage() {
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
    public void setDamage(final int damage) {
        this.damage = damage;
    }

    /**
     * @param safeZone
     */
    public void setSafeZone(final BoundingBox safeZone) {
        this.safeZone = safeZone;
    }

    /**
     * 
     */
    @Override
    public void collideWith(final GameObject obj2) {
        /*if (this.getBoundingBox().intersectWith(this.safeZone) && this.getDirection().getY() > 0) { 
            return;
        }*/
        switch (obj2.getGameObjectType().getCollisionType()) {
        case OBSTACLE:
            if (obj2 instanceof Wall) {
                if (((Wall) obj2).getCardinalPoint() == CardinalPoint.NORTH && this.getDirection().getY() > 0) {
                    return;
                }
            }
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
