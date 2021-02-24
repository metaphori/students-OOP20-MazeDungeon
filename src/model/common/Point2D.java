package model.common;

import java.util.LinkedList;
import java.util.List;

public class Point2D implements java.io.Serializable {
    private static final long serialVersionUID = 4674087395785183468L;
    private final double x;
    private final double y;

    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param v
     * @return the resultant Point
     */
    public Point2D sum(final Vector2D v) {
        return new Point2D(this.x + v.getX(), this.y + v.getY());
    }

    /**
     * @param v
     * @return the resultant Point
     */
    public Vector2D sub(final Point2D v) {
        return new Vector2D(this.x - v.getX(), this.y - v.getY());
    }

    /**
     * @return the X of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the Y of the point
     */
    public double getY() {
        return this.y;
    }

}
