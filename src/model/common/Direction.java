package model.common;

import java.util.Random;

public enum Direction {
    /**
     * direction up.
     */
    UP,
    /**
     * direction left.
     */
    LEFT,
    /**
     * direction left.
     */
    DOWN,
    /**
     * direction right.
     */
    RIGHT;

    private static final int SIZE = 4;

    /**
     * @return a random direction
     */
    public static Direction getRandomDirection() {
        return values()[new Random().nextInt(SIZE)];
    }

    /**
     * @param direction : the direction for search the opposite
     * @return the opposite direction of the parameter
     */
    public static Direction getOppositeDirection(final Direction direction) {
        switch (direction) {
        case UP:
            return DOWN;
        case DOWN:
            return UP;
        case LEFT:
            return RIGHT;
        case RIGHT:
            return LEFT;
        default:
            throw new IllegalStateException("not valid direction");
        }
    }
}
