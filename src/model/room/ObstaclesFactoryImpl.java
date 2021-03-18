package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.common.BoundingBox;
import model.common.CardinalPoint;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.simpleobject.Obstacle;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.simpleobject.Wall;

public class ObstaclesFactoryImpl implements ObstacleFactory {
    private static final int OBSTACLE_FOR_ROW = 24;
    private static final int OBSTACLE_FOR_COL = 14;
    private static final int FREE_ROWS = 4;
    private static final int FREE_COLS = 4;
    private final double obstacleWidth;
    private final double obstacleHeight;
    private final double width;
    private final double height;
    private static final int PERSPECTIVE_WALL_OFFSET = 50;
    private static final int WALL_DEPTH = 1;

    public ObstaclesFactoryImpl() {
        this.width = Rooms.BR_CORNER.getX() - Rooms.UL_CORNER.getX();
        this.height = Rooms.BR_CORNER.getY() - Rooms.UL_CORNER.getY();
        this.obstacleWidth = this.width / OBSTACLE_FOR_ROW;
        this.obstacleHeight = this.height / OBSTACLE_FOR_COL;
    }

    /**
     * @return a list contains walls
     */
    @Override
    public List<SimpleObject> createEmptyRoom() {
        return this.getWalls();
    }

    /**
     * 
     * @param squares : the number of squares to create
     * @return a list of obstacles
     */
    @Override
    public List<SimpleObject> createSquare(final int squares) {
        final List<SimpleObject> obstacles = new LinkedList<>();
        final Random rnd = new Random();

        for (int n = 0; n < squares; n++) {
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
        tmp = new Wall(Rooms.UL_CORNER.sum(new Vector2D(0, -WALL_DEPTH)), CardinalPoint.NORTH);
        tmp.setBoundingBox(new BoundingBox(Rooms.UL_CORNER.sum(new Vector2D(0, -WALL_DEPTH)), this.width, WALL_DEPTH));
        walls.add(tmp);
        //RIGHT
        tmp = new Wall(new Point2D(Rooms.BR_CORNER.getX(), Rooms.UL_CORNER.getY() - PERSPECTIVE_WALL_OFFSET), CardinalPoint.EAST);
        tmp.setBoundingBox(new BoundingBox(new Point2D(Rooms.BR_CORNER.getX(), Rooms.UL_CORNER.getY() - PERSPECTIVE_WALL_OFFSET), WALL_DEPTH, this.height + PERSPECTIVE_WALL_OFFSET));
        walls.add(tmp);
        //BOTTOM
        tmp = new Wall(new Point2D(Rooms.UL_CORNER.getX(), Rooms.BR_CORNER.getY()), CardinalPoint.SOUTH);
        tmp.setBoundingBox(new BoundingBox(new Point2D(Rooms.UL_CORNER.getX(), Rooms.BR_CORNER.getY()), this.width, WALL_DEPTH));
        walls.add(tmp);
        //LEFT
        tmp = new Wall(Rooms.UL_CORNER.sum(new Vector2D(-WALL_DEPTH, -PERSPECTIVE_WALL_OFFSET)), CardinalPoint.WEST);
        tmp.setBoundingBox(new BoundingBox(Rooms.UL_CORNER.sum(new Vector2D(-WALL_DEPTH, -PERSPECTIVE_WALL_OFFSET)), WALL_DEPTH, this.height + PERSPECTIVE_WALL_OFFSET));
        walls.add(tmp);
        //PERSPECTIVE
        tmp = new Wall(Rooms.UL_CORNER.sum(new Vector2D(0, -WALL_DEPTH)), CardinalPoint.NORTH);
        tmp.setBoundingBox(new BoundingBox(Rooms.UL_CORNER.sum(new Vector2D(0, -WALL_DEPTH - PERSPECTIVE_WALL_OFFSET)), this.width, WALL_DEPTH));
        walls.add(tmp);
        return walls;
    }

    private Point2D getObstaclePosition(final int x, final int y) {
        return new Point2D(obstacleWidth * (y - 1 + FREE_COLS) + Rooms.UL_CORNER.getX(), 
                           obstacleHeight * (x - 1 + FREE_ROWS) + Rooms.UL_CORNER.getY());
    }

    private Obstacle getObstacle(final Point2D position) {
        return new Obstacle(position);
    }

}
