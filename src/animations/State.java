package animations;

/**
 * This enumeration represent the state where a GameObject is, referring to it's movement.
 *
 */
public enum State {
    /**
     * Stopped, not in movement.
     */
    IDLE,

    /**
     * Moving left.
     */
    MOVE_LEFT,

    /**
     * Moving right.
     */
    MOVE_RIGHT,

    /**
     * Moving down.
     */
    MOVE_DOWN,

    /**
     * Moving up.
     */
    MOVE_UP;
}
