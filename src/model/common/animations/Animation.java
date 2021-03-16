package model.common.animations;

import java.util.HashMap;
import java.util.Map;

public class Animation {

    private final Map<State, SpriteIterator> animations = new HashMap<>();

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


}
