package model.gameobject.dinamicobject.enemy;

import model.common.Point2D;
import model.common.Vector2D;
import model.room.Room;

public interface EnemyFactory {

    Enemy createSprout(Point2D position, Vector2D direction, Room room);

    Enemy createSoul(Point2D position, Vector2D direction, Room room);

    Enemy createSkeletonSeeker(Point2D position, Vector2D direction, Room room);

    Enemy createBoss(Point2D position, Vector2D direction, Room room);
}
