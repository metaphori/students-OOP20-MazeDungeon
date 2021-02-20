package model.gameobject.dinamicobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.room.Room;

public class Coin extends AbstractDinamicObject {

    public Coin(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType) {
        super(id, speed, position, direction, gameObjectType);
    }

    /**
     * 
     */
    @Override
    public void updateState(final double elapsed) {
        this.move(elapsed);
    }

    @Override
    public void collideWith(final GameObject obj2) {
        //this.setPosition(new Point2D(obj2.getPosition().getX() - this.getBoundingBox().getWidth(), this.getPosition().getY()));
        this.setDirection(new Vector2D(0, 0));
        this.setPosition(this.getLastPosition());
    }

}
