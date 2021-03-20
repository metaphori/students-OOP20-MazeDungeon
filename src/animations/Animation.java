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
     * @param state
     * @param spriteIterator
     */
    public void addState(final State state, final SpriteIterator spriteIterator) {
        this.animations.put(state, spriteIterator);
    }
    
    public void setState(final State state) {
        this.lastState = state;
    }
    /**
     * 
     * @param state
     * @return the next image
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
