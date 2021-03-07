package model.gameobject.dynamicobject.bullet;

import model.common.Point2D;
import model.common.Vector2D;
import model.room.Room;

public interface BulletFactory {

   // Bullet createBullet(GameObjectType bulletType, Point2D initialPosition, Vector2D direction);

    Bullet createCharacterBullet(Point2D initialPosition, Vector2D direction, int bonusDamage);

    Bullet createSkeletonBullet(Point2D initialPosition, Vector2D direction);

    Bullet createSoulBullet(Point2D initialPosition, Vector2D direction);

    Bullet createSproutBullet(Point2D initialPosition, Vector2D direction);

    Bullet createBossBullet(Point2D initialPosition, Vector2D direction);

    Bullet createOldGuardianBullet(Point2D initialPosition, Vector2D direction);

}
