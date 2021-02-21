package model.common;

import java.util.Iterator;

public class IdIterator implements Iterator<Integer> {

    private int value = 0;

    /**
     * Has always a next value.
     * @Override
     * @return true
     */
    public boolean hasNext() {
        return true;
    }

    /**
     * @Override
     * @return the next value
     */
    public Integer next() {
        return this.value++;
    }

}
