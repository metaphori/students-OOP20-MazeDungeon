package model.common;
/**
 * This enumeration contains the vectorDirection specified by a Vector2D.
 */
public enum VectorDirection {
    /**
     * Direction up for movement.
     */
    UP(new Vector2D(0, -1)),
    /**
     * Direction down for movement.
     */
    DOWN(new Vector2D(0, 1)),
    /**
     * Direction left for movement.
     */
    LEFT(new Vector2D(-1, 0)),
    /**
     * Direction right for movement.
     */
    RIGHT(new Vector2D(1, 0));

    private final Vector2D vectorDirection;

    VectorDirection(final Vector2D vector2d) {
        this.vectorDirection = vector2d;
    }

    public Vector2D getDirection() {
        return this.vectorDirection;
    }

}
