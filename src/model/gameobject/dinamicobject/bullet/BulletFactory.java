package model.gameobject.dinamicobject.bullet;


import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;

public interface BulletFactory {

    Bullet createBullet(GameObjectType bulletType, Point2D initialPosition, Vector2D direction);
}
