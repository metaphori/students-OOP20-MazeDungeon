package animations;

import java.util.Iterator;
import java.util.List;

public class SpriteIterator implements Iterator<Sprite> {

    private final List<Sprite> sprites;
    private int counter;

    public SpriteIterator(final List<Sprite> sprites) {
        this.sprites = sprites;
    }
    /**
     * 
     */
    @Override
    public boolean hasNext() {
        return true;
    }

    /**
     * 
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
