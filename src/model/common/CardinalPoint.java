package model.common;

import java.util.Random;

public enum CardinalPoint {
    /**
     * 
     */
    NORTH,
    /**
     *
     */
    WEST,
    /**
     *
     */
    SOUTH,
    /**
     *
     */
    EAST;

    private static final int SIZE = 4;

    /**
     * @return a random direction
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
