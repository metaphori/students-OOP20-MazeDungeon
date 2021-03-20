package animations;

import java.util.HashMap;
import java.util.Map;

import model.common.Point2D;

public class Animation {

    private final Map<State, SpriteIterator> animations = new HashMap<>();
    private Point2D position;
    private Sprite actualImage;
    private long lastUpdate;
    private State lastState = State.IDLE;
    private static final int UPDATE_DELAY = 60;

    /**
     * 
     * @param state : state to add at the animation
     * @param spriteIterator : iterator paired to the state
     */
    public void addState(final State state, final SpriteIterator spriteIterator) {
        this.animations.put(state, spriteIterator);
    }

    /**
     * 
     * @param state : state to set as active
     */
    public void setState(final State state) {
        this.lastState = state;
    }

    /**
     * 
     * @return the next image of the animation
     */
    public Sprite getNext() {
        final long currentUpdate = System.currentTimeMillis();
        if (currentUpdate - this.lastUpdate >= UPDATE_DELAY) {
            this.actualImage = this.animations.get(this.lastState).next();
            this.lastUpdate = currentUpdate;
        }
        return this.actualImage;
    }

    /**
     * 
     * @param position : the position of animation
     */
    public void setPosition(final Point2D position) {
        this.position = position;
    }

    /**
     * 
     * @return position of the animation
     */
    public Point2D getPosition() {
        return position;
    }

}
