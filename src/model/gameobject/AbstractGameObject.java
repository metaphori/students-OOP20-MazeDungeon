package model.gameobject;

import model.common.Point2D;

public abstract class AbstractGameObject implements GameObject {

    private int id;
    private Point2D position;

    public AbstractGameObject(final int id, final Point2D position) {
        this.id = id;
        this.position = position;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }
}
