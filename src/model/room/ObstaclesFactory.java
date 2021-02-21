package model.room;

import java.util.LinkedList;
import java.util.List;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.simpleobject.SimpleObjectImpl;

public class ObstaclesFactory {
    private static final Point2D UL_CORNER = new Point2D(240, 177); //TODO in caso di resize della finestra vanno cambiati!!!!
    private static final Point2D BR_CORNER = new Point2D(1025, 633);
    //TODO id dispenser

    /**
     * 
     * @return .
     */
    public List<SimpleObject> getEmptyRoom() {
        return this.getWalls();
    }

    private List<SimpleObject> getWalls() {
        final List<SimpleObject> walls = new LinkedList<>();
        SimpleObject tmp;
        //TOP
        tmp = new SimpleObjectImpl(100, UL_CORNER, GameObjectType.INVISIBLE_OBJECT);
        tmp.setBoundingBox(new BoundingBox(UL_CORNER, BR_CORNER.getX() - UL_CORNER.getX(), 1));
        walls.add(tmp);
        //RIGHT
        tmp = new SimpleObjectImpl(101, new Point2D(BR_CORNER.getX(), UL_CORNER.getY()), GameObjectType.INVISIBLE_OBJECT);
        tmp.setBoundingBox(new BoundingBox(new Point2D(BR_CORNER.getX(), UL_CORNER.getY()), 1, BR_CORNER.getY() - UL_CORNER.getY()));
        walls.add(tmp);
        //BOTTOM
        tmp = new SimpleObjectImpl(102, new Point2D(UL_CORNER.getX(), BR_CORNER.getY()), GameObjectType.INVISIBLE_OBJECT);
        tmp.setBoundingBox(new BoundingBox(new Point2D(UL_CORNER.getX(), BR_CORNER.getY()), BR_CORNER.getX() - UL_CORNER.getX(), 1));
        walls.add(tmp);
        //LEFT
        tmp = new SimpleObjectImpl(101, UL_CORNER, GameObjectType.INVISIBLE_OBJECT);
        tmp.setBoundingBox(new BoundingBox(UL_CORNER, 1, BR_CORNER.getY() - UL_CORNER.getY()));
        walls.add(tmp);
        return walls;
    }
}
