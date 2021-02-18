package model.gameobject.simpleobject;

import model.common.Point2D;

public class SimpleObjectImpl implements SimpleObject {
    private int id;
    private Point2D position;

    public SimpleObjectImpl(final int id, final Point2D position) {
        this.id = id;
        this.position = position;
    }

    /**
     * @return the ID of the object
     */
    @Override
    public int getID() {
        return this.id;
    }

    /**
     * @return the position of the object
     */
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * @param position : the position where the object will be setted
     */
    @Override
    public void setPosition(final Point2D position) {
        this.position = position;
    }

}
