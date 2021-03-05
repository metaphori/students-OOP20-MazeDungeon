package model.gameobject.dinamicobject.enemy;

import model.common.Point2D;

public interface EnemyFactory {

    Enemy createSprout(Point2D position);

    Enemy createSoul(Point2D position);

    Enemy createSkeletonSeeker(Point2D position);

    Enemy createBoss(Point2D position);
}
