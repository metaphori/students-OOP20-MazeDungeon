package model.gameobject.dynamicobject.bullet;

import model.common.Point2D;
import model.common.Vector2D;

public interface BulletFactory {

    Bullet createCharacterBullet(Point2D initialPosition, Vector2D direction, int bonusDamage, int bonusBulletSpeed);

    Bullet createSkeletonBullet(Point2D initialPosition, Vector2D direction);

    Bullet createSoulBullet(Point2D initialPosition, Vector2D direction);

    Bullet createSproutBullet(Point2D initialPosition, Vector2D direction);

    Bullet createBossBullet(Point2D initialPosition, Vector2D direction);
}
