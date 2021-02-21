package model.gameobject.dinamicobject.bullet;


import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;

public interface BulletFactory {

   // Bullet createBullet(GameObjectType bulletType, Point2D initialPosition, Vector2D direction);

    Bullet createCharacterBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction);

    Bullet createSkeletonBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction);

    Bullet createSoulBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction);

    Bullet createSproutBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction);

    Bullet createBossBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction);

    Bullet createOldGuardianBullet(GameObjectType gameObjectType, Point2D initialPosition, Vector2D direction);

}
