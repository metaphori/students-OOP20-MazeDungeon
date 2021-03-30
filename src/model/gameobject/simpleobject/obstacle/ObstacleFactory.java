package model.gameobject.simpleobject.obstacle;

import java.util.List;

import model.gameobject.simpleobject.SimpleObject;

/**
 * an ObstacleFactory permit to create obstacle dispositions.
 */
public interface ObstacleFactory {

    /**
     * Create an empty room containing only walls.
     * @return a list containing walls
     */
    List<SimpleObject> createWalls();

    /**
     * Create n squares each one of random size.
     * @param n : number of squares
     * @return a list containing obstacles
     */
    List<SimpleObject> createSquare(int n);

}
