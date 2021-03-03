package model.common;

import java.util.Random;

public enum Direction {
    UP,
    LEFT,
    DOWN,
    RIGHT;

    private static final int SIZE = 4;

    public static Direction getRandomDirection() {
        return values()[new Random().nextInt(SIZE)];
    }

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
