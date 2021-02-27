package model.room;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.gameobject.simpleobject.Obstacle;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.simpleobject.SimpleObjectImpl;
import model.gameobject.simpleobject.Wall;

public class ObstaclesFactory {
    private static final int obstacleForRow = 24;
    private static final int obstacleForCol = 14;
    private static final int freeRows = 3;
    private static final int freeCols = 3;
    private final double obstacleWidth;
    private final double obstacleHeight;
    private final Point2D ulCorner;
    private final Point2D brCorner;
    private final double width;
    private final double height;

    public ObstaclesFactory(final Point2D ulCorner, final Point2D brCorner) {
        this.ulCorner = ulCorner;
        this.brCorner = brCorner;
        this.width = brCorner.getX() - ulCorner.getX();
        this.height = brCorner.getY() - ulCorner.getY();
        this.obstacleWidth = this.width / obstacleForRow;
        this.obstacleHeight = this.height / obstacleForCol;
    }

    /**
     * @return a room that contain only walls
     */
    public List<SimpleObject> getEmptyRoom() {
        return this.getWalls();
    }
    
    /**
     * 
     * @return .
     */
    public List<SimpleObject> createXComposition() {
        final List<SimpleObject> obstacles = new LinkedList<>(getEmptyRoom());
        obstacles.add(getObstacle(getObstaclePosition(4, 9)));
        obstacles.add(getObstacle(getObstaclePosition(4, 16)));
        obstacles.add(getObstacle(getObstaclePosition(5, 10)));
        obstacles.add(getObstacle(getObstaclePosition(5, 15)));
        obstacles.add(getObstacle(getObstaclePosition(6, 11)));
        obstacles.add(getObstacle(getObstaclePosition(6, 14)));
        obstacles.add(getObstacle(getObstaclePosition(7, 12)));
        obstacles.add(getObstacle(getObstaclePosition(7, 13)));
        obstacles.add(getObstacle(getObstaclePosition(8, 12)));
        obstacles.add(getObstacle(getObstaclePosition(8, 13)));
        obstacles.add(getObstacle(getObstaclePosition(9, 11)));
        obstacles.add(getObstacle(getObstaclePosition(9, 14)));
        obstacles.add(getObstacle(getObstaclePosition(10, 10)));
        obstacles.add(getObstacle(getObstaclePosition(10, 15)));
        obstacles.add(getObstacle(getObstaclePosition(11, 9)));
        obstacles.add(getObstacle(getObstaclePosition(11, 16)));
        return obstacles;
    }

    /**
     * 
     * @param n
     * @return .
     */
    public List<SimpleObject> createRandomComposition(final int n) {
        final Set<SimpleObject> obstacles = new HashSet<>();
        final Random rnd = new Random();
        while (obstacles.size() < n) {
            obstacles.add(getObstacle(getObstaclePosition(rnd.nextInt(obstacleForCol - 2 * freeRows) + freeRows, 
                                                          rnd.nextInt(obstacleForRow - 2 * freeCols) + freeCols)));
        }
        obstacles.addAll(getEmptyRoom());
        return new LinkedList<>(obstacles);
    }

    private List<SimpleObject> getWalls() {
        final List<SimpleObject> walls = new LinkedList<>();
        SimpleObject tmp;
        //TOP
        tmp = new Wall(ulCorner, GameObjectType.INVISIBLE_OBJECT);
        tmp.setBoundingBox(new BoundingBox(ulCorner, this.width, 1));
        walls.add(tmp);
        //RIGHT
        tmp = new Wall(new Point2D(brCorner.getX(), ulCorner.getY()), GameObjectType.INVISIBLE_OBJECT);
        tmp.setBoundingBox(new BoundingBox(new Point2D(brCorner.getX(), ulCorner.getY()), 1, this.height));
        walls.add(tmp);
        //BOTTOM
        tmp = new Wall(new Point2D(ulCorner.getX(), brCorner.getY()), GameObjectType.INVISIBLE_OBJECT);
        tmp.setBoundingBox(new BoundingBox(new Point2D(ulCorner.getX(), brCorner.getY()), this.width, 1));
        walls.add(tmp);
        //LEFT
        tmp = new Wall(ulCorner, GameObjectType.INVISIBLE_OBJECT);
        tmp.setBoundingBox(new BoundingBox(ulCorner, 1, this.height));
        walls.add(tmp);
        return walls;
    }

    /**
     * 
     * @param x
     * @param y
     * @return
     */
    private Point2D getObstaclePosition(final int x, final int y) {
        return new Point2D(obstacleWidth * (y - 1) + ulCorner.getX(), obstacleHeight * (x - 1) + ulCorner.getY());
    }

    private Obstacle getObstacle(final Point2D position) {
        return new Obstacle(position, GameObjectType.ROCK);
    }

}
