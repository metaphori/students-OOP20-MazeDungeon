package model.gameobject.dynamicobject.bullet;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.AbstractDynamicObject;
import model.gameobject.dynamicobject.enemy.Enemy;
import model.gameobject.dynamicobject.maincharacter.MainCharacter;
import model.gameobject.simpleobject.obstacle.Wall;

public class BulletImpl extends AbstractDynamicObject implements Bullet {

    private int damage;

    public BulletImpl(final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final int damage, final int speed) {
        super(speed, position, gameObjectType);
        this.damage = damage;
        this.setDirection(direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double elapsed) {
        this.move(elapsed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDamage(final int damage) {
        this.damage = damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(final GameObject obj2) {
        switch (obj2.getGameObjectType().getCollisionType()) {
        case OBSTACLE:
            if (obj2 instanceof Wall && ((Wall) obj2).getCardinalPoint() == CardinalPoint.NORTH 
                    && (this.getDirection().getY() > 0 || !((Wall) obj2).isPerspective())) {
                    return;
            }
            this.getRoom().deleteGameObject(this);
            break;
        case ENTITY:
            if (obj2.getGameObjectType().equals(GameObjectType.MAINCHARACTER) && !this.getGameObjectType().equals(GameObjectType.MAINCHARACTER_BULLET)) {
                final MainCharacter mainCharacter = (MainCharacter) obj2;
                mainCharacter.takesDamage(this.getDamage());
                this.getRoom().deleteGameObject(this);
            } else if (!obj2.getGameObjectType().equals(GameObjectType.MAINCHARACTER) && this.getGameObjectType().equals(GameObjectType.MAINCHARACTER_BULLET)) {
                final Enemy enemy = (Enemy) obj2;
                enemy.takesDamage(this.getDamage());
                this.getRoom().deleteGameObject(this);
            }
        default:
            break;
        }
    }
}
