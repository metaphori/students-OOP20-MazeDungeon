package model.gameobject.simpleobject;

import java.awt.Rectangle;

import model.common.GameObjectType;
import model.common.Point2D;

public class SimpleObjectImpl implements SimpleObject {
    private final int id;
    private Point2D position;
    private final GameObjectType gameObjectType;
    private Rectangle boundingBox;

    public SimpleObjectImpl(final int id, final Point2D position, final GameObjectType gameObjectType) {
        this.id = id;
        this.position = position;
        this.gameObjectType = gameObjectType;
    }

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

    /**
     * 
     */
    @Override
    public GameObjectType getGameObjectType() {
        return this.gameObjectType;
    }

    /**
     * 
     */
    @Override
    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    /**
     * 
     */
    @Override
    public void setBoundingBox(final Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * 
     */
    private void setBoundingBoxPosition(final Point2D position) {
        this.boundingBox.x = position.getX();
        this.boundingBox.y = position.getY();
    }

}
