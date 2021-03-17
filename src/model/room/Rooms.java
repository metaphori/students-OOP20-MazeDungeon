package model.room;

import model.common.Point2D;

public final class Rooms {

    /**
     * upper left corner of the room.
     */
    public static final Point2D UL_CORNER = new Point2D(240, 177);

    /**
     * bottom right corner of the room.
     */
    public static final Point2D BR_CORNER = new Point2D(1025, 633);

    /**
     * center corner of the room.
     */
    public static final Point2D CENTER = new Point2D((BR_CORNER.getX() + UL_CORNER.getX()) / 2, (BR_CORNER.getY() + UL_CORNER.getY()) / 2);

    /**
     * number of rooms per floor.
     */
    public static final int NUMBER_OF_ROOMS = 10;

    private Rooms() {
    }
}
