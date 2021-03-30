package model.common;

import java.util.Random;

/**
 * A basic class that implements the concept of cardinal point.
 */
public enum CardinalPoint {
    /**
     * cardinal point north.
     */
    NORTH,
    /**
     * cardinal point west.
     */
    WEST,
    /**
     * cardinal point south.
     */
    SOUTH,
    /**
     * cardinal point east.
     */
    EAST;

    private static final int SIZE = 4;

    /**
     * @return a random cardinal point
     */
    public static CardinalPoint getAny() {
        return values()[new Random().nextInt(SIZE)];
    }

    /**
     * @param cardinalPoint : the cardinal point for search the opposite
     * @return the opposite cardinal point of the parameter
     */
    public static CardinalPoint getOpposite(final CardinalPoint cardinalPoint) {
        switch (cardinalPoint) {
        case NORTH:
            return SOUTH;
        case SOUTH:
            return NORTH;
        case WEST:
            return EAST;
        case EAST:
            return WEST;
        default:
            throw new IllegalStateException("not valid cardinal point");
        }
    }
}
