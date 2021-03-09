package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.common.BoundingBox;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.simpleobject.Obstacle;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.simpleobject.Wall;

public class ObstaclesFactory {
    private static final int OBSTACLE_FOR_ROW = 24;
    private static final int OBSTACLE_FOR_COL = 14;
    private static final int FREE_ROWS = 4;
    private static final int FREE_COLS = 4;
    private final double obstacleWidth;
    private final double obstacleHeight;
    private final Point2D ulCorner;
    private final Point2D brCorner;
    private final double width;
    private final double height;
    private static final int WALL_DEPTH = 100;

    /**
     * @param ulCorner : upper left corner of the room
     * @param brCorner : : bottom right corner of the room
     */
    public ObstaclesFactory(final Point2D ulCorner, final Point2D brCorner) {
        this.ulCorner = ulCorner;
        this.brCorner = brCorner;
        this.width = brCorner.getX() - ulCorner.getX();
        this.height = brCorner.getY() - ulCorner.getY();
        this.obstacleWidth = this.width / OBSTACLE_FOR_ROW;
        this.obstacleHeight = this.height / OBSTACLE_FOR_COL;
    }

    /**
     * @return a list contains walls
     */
    public List<SimpleObject> getEmptyRoom() {
        return this.getWalls();
    }

    /**
     * 
     * @param squaresNumber : the number of squares to create
     * @return a list of obstacles
     */
    public List<SimpleObject> createSquare(final int squaresNumber) {
        final List<SimpleObject> obstacles = new LinkedList<>();
        final Random rnd = new Random();

        for (int n = 0; n < squaresNumber; n++) {
            final int size = rnd.nextInt(OBSTACLE_FOR_COL / 2); //7 max
            final int ulX = rnd.nextInt(OBSTACLE_FOR_COL - 2 * (FREE_ROWS - 1) - size);
            final int ulY = rnd.nextInt(OBSTACLE_FOR_ROW - 2 * (FREE_COLS - 1) - size);

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                        obstacles.add(getObstacle(getObstaclePosition(ulX + i, ulY + j)));
                    }
                }
            }
        }
        return new LinkedList<>(obstacles);
    }

    private List<SimpleObject> getWalls() {
        final List<SimpleObject> walls = new LinkedList<>();
        SimpleObject tmp;
        //TOP
        tmp = new Wall(ulCorner);
        tmp.setBoundingBox(new BoundingBox(ulCorner.sum(new Vector2D(0, -WALL_DEPTH)), this.width, WALL_DEPTH));
        walls.add(tmp);
        //RIGHT
        tmp = new Wall(new Point2D(brCorner.getX(), ulCorner.getY()));
        tmp.setBoundingBox(new BoundingBox(new Point2D(brCorner.getX(), ulCorner.getY()), WALL_DEPTH, this.height));
        walls.add(tmp);
        //BOTTOM
        tmp = new Wall(new Point2D(ulCorner.getX(), brCorner.getY()));
        tmp.setBoundingBox(new BoundingBox(new Point2D(ulCorner.getX(), brCorner.getY()), this.width, WALL_DEPTH));
        walls.add(tmp);
        //LEFT
        tmp = new Wall(ulCorner);
        tmp.setBoundingBox(new BoundingBox(ulCorner.sum(new Vector2D(-WALL_DEPTH, 0)), WALL_DEPTH, this.height));
        walls.add(tmp);
        return walls;
    }

    private Point2D getObstaclePosition(final int x, final int y) {
        return new Point2D(obstacleWidth * (y - 1 + FREE_COLS) + ulCorner.getX(), 
                           obstacleHeight * (x - 1 + FREE_ROWS) + ulCorner.getY());
    }

    private Obstacle getObstacle(final Point2D position) {
        return new Obstacle(position);
    }

}
