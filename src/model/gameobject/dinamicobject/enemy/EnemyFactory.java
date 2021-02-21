package model.gameobject.dinamicobject.enemy;

import model.common.Point2D;
import model.common.Vector2D;
import model.room.Room;

public interface EnemyFactory {

    Enemy createSprout(int speed, Point2D position, Vector2D direction);

    Enemy createSoul(int speed, Point2D position, Vector2D direction);

    Enemy createSkeletonSeeker(int speed, Point2D position, Vector2D direction);

    Enemy createBoss(int speed, Point2D position, Vector2D direction);
}
