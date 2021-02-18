package model.gameobject.dinamicobject.bullet;

import model.common.BulletType;
import model.common.Point2D;
import model.common.Vector2D;

public interface BulletFactory {


    Bullet createBullet(BulletType bulletType, Point2D initialPosition, Vector2D direction);
}
