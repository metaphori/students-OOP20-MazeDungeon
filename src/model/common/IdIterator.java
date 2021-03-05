package model.common;

import java.util.Iterator;

public class IdIterator implements Iterator<Integer> {

    private int value;

    /**
     * Has always a next value.
     * @return true
     */
    @Override
    public boolean hasNext() {
        return true;
    }

    /**
     * @Override
     * @return the next value
     */
    @Override
    public Integer next() {
        return this.value++;
    }
}
