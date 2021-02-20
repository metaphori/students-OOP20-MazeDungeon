package model.gameobject.dinamicobject.enemy;

import model.common.Point2D;
import model.common.Vector2D;
import model.room.Room;

public interface EnemyFactory {

    Enemy createSprout(int id, int speed, Point2D position, Vector2D direction, Room room);

    Enemy createSoul(int id, int speed, Point2D position, Vector2D direction, Room room);

    Enemy createSkeletonSeeker(int id, int speed, Point2D position, Vector2D direction, Room room);

    Enemy createBoss(int id, int speed, Point2D position, Vector2D direction, Room room);
}
