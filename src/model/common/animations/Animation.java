package model.common.animations;

import java.util.HashMap;
import java.util.Map;

import model.common.Point2D;

public class Animation {

    private final Map<State, SpriteIterator> animations = new HashMap<>();
    private Point2D position;

    /**
     * 
     * @param state
     * @param spriteIterator
     */
    public void addState(final State state, final SpriteIterator spriteIterator) {
        this.animations.put(state, spriteIterator);
    }
    /**
     * 
     * @param state
     * @return the next image
     */
    public Sprite getNext(final State state) {
        return this.animations.get(state).next();
    }

    /**
     * 
     * @param position
     */
    public void setPosition(final Point2D position) {
        this.position = position;
    }

    /**
     * 
     * @return position of the sprite
     */
    public Point2D getPosition() {
        return position;
    }


}
