package model.gameobject.dinamicobject.enemy;

import model.common.Point2D;
import model.common.Vector2D;

public interface EnemyFactory {

    Enemy createSprout(int id, int speed, Point2D position, Vector2D direction);

    Enemy createSoul();

    Enemy createSkeletonSeeker();

    Enemy createBoss();
}
