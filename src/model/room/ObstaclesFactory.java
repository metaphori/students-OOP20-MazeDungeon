package model.room;

import java.util.LinkedList;
import java.util.List;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.simpleobject.SimpleObjectImpl;
import model.gameobject.simpleobject.Wall;

public class ObstaclesFactory {
    private static final Point2D UL_CORNER = new Point2D(240, 177); //TODO in caso di resize della finestra vanno cambiati!!!!
    private static final Point2D BR_CORNER = new Point2D(1025, 633);
    private final IdIterator idIterator;

    public ObstaclesFactory(final IdIterator idIterator) {
        this.idIterator = idIterator;
    }

    /**
     * @param room where to add obstacles
     * @return .
     */
    public List<SimpleObject> getEmptyRoom(final Room room) {
        return this.getWalls(room);
    }

    private List<SimpleObject> getWalls(final Room room) {
        final List<SimpleObject> walls = new LinkedList<>();
        SimpleObject tmp;
        //TOP
        tmp = new Wall(this.idIterator.next(), UL_CORNER, GameObjectType.INVISIBLE_OBJECT, room);
        tmp.setBoundingBox(new BoundingBox(UL_CORNER, BR_CORNER.getX() - UL_CORNER.getX(), 1));
        walls.add(tmp);
        //RIGHT
        tmp = new Wall(this.idIterator.next(), new Point2D(BR_CORNER.getX(), UL_CORNER.getY()), GameObjectType.INVISIBLE_OBJECT, room);
        tmp.setBoundingBox(new BoundingBox(new Point2D(BR_CORNER.getX(), UL_CORNER.getY()), 1, BR_CORNER.getY() - UL_CORNER.getY()));
        walls.add(tmp);
        //BOTTOM
        tmp = new Wall(this.idIterator.next(), new Point2D(UL_CORNER.getX(), BR_CORNER.getY()), GameObjectType.INVISIBLE_OBJECT, room);
        tmp.setBoundingBox(new BoundingBox(new Point2D(UL_CORNER.getX(), BR_CORNER.getY()), BR_CORNER.getX() - UL_CORNER.getX(), 1));
        walls.add(tmp);
        //LEFT
        tmp = new Wall(this.idIterator.next(), UL_CORNER, GameObjectType.INVISIBLE_OBJECT, room);
        tmp.setBoundingBox(new BoundingBox(UL_CORNER, 1, BR_CORNER.getY() - UL_CORNER.getY()));
        walls.add(tmp);
        return walls;
    }

}
