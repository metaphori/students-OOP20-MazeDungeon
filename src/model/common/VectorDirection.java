package model.common;

public enum VectorDirection {
    UP(new Vector2D(0, -1)),
    DOWN(new Vector2D(0, 1)),
    LEFT(new Vector2D(-1, 0)),
    RIGHT(new Vector2D(1, 0));

    private final Vector2D vectorDirection;

    VectorDirection (final Vector2D vector2d) {
        this.vectorDirection = vector2d;
    }

    public Vector2D getDirection() {
        return this.vectorDirection;
    }

}
