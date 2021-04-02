package animations;

import java.util.Iterator;
import java.util.List;

/**
 * an infinite iterator of Sprite. 
 */
public class SpriteIterator implements Iterator<Sprite> {

    private final List<Sprite> sprites;
    private int counter;

    /**
     * 
     * @param sprites : list to iterate
     */
    public SpriteIterator(final List<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * it return always true because is an infinite iterator.
     */
    @Override
    public boolean hasNext() {
        return true;
    }

    /**
     * return the next sprite in the given list.
     * if the last returned was the last one, return the first.
     */
    @Override
    public Sprite next() {
        final Sprite sprite = sprites.get(this.counter++);
        if (this.counter == this.sprites.size()) {
            this.counter = 0;
        }
        return sprite;
    }

}
