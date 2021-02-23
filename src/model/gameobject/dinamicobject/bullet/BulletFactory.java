package model.gameobject.dinamicobject.bullet;

import model.common.Point2D;
import model.common.Vector2D;
import model.room.Room;

public interface BulletFactory {

   // Bullet createBullet(GameObjectType bulletType, Point2D initialPosition, Vector2D direction);

    Bullet createCharacterBullet(Point2D initialPosition, Vector2D direction, Room room);

    Bullet createSkeletonBullet(Point2D initialPosition, Vector2D direction, Room room);

    Bullet createSoulBullet(Point2D initialPosition, Vector2D direction, Room room);

    Bullet createSproutBullet(Point2D initialPosition, Vector2D direction, Room room);

    Bullet createBossBullet(Point2D initialPosition, Vector2D direction, Room room);

    Bullet createOldGuardianBullet(Point2D initialPosition, Vector2D direction, Room room);

}
