package model.gameobject.dinamicobject.bullet;


import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.room.Room;

public interface BulletFactory {

   // Bullet createBullet(GameObjectType bulletType, Point2D initialPosition, Vector2D direction);

    Bullet createCharacterBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction, Room room);

    Bullet createSkeletonBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction, Room room);

    Bullet createSoulBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction, Room room);

    Bullet createSproutBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction, Room room);

    Bullet createBossBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction, Room room);

    Bullet createOldGuardianBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction, Room room);

}
