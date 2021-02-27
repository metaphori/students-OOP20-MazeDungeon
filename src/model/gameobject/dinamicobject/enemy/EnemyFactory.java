package model.gameobject.dinamicobject.enemy;

import model.common.Point2D;
import model.common.Vector2D;
import model.room.Room;

public interface EnemyFactory {

    Enemy createSprout(Point2D position);

    Enemy createSoul(Point2D position);

    Enemy createSkeletonSeeker(Point2D position);

    Enemy createBoss(Point2D position);
}
