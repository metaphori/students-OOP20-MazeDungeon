package model.common;

/**
 * A basic class that implements the concept of Point in two dimensions.
 * 
 */
public class Point2D {
    private final double x;
    private final double y;

    /**
     * @param x : the x coordinate of the point.
     * @param y : the y coordinate of the point.
     */
    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param v : the vector to be added.
     * @return the resultant Point
     */
    public Point2D sum(final Vector2D v) {
        return new Point2D(this.x + v.getX(), this.y + v.getY());
    }

    /**
     * @param v : the vector to be multiplied.
     * @return the resultant Point
     */
    public Point2D mul(final double v) {
        return new Point2D(this.x * v, this.y * v);
    }

    /**
     * @param v : the vector to be subtracted.
     * @return the resultant Point
     */
    public Vector2D sub(final Point2D v) {
        return new Vector2D(this.x - v.getX(), this.y - v.getY());
    }

    /**
     * @return the x of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Generated, helpful for compare two Point2D.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Generated, helpful for compare two Point2D.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point2D other = (Point2D) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        return Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
    }

    /**
     * @return the string representation of a Point2D
     */
    @Override
    public String toString() {
        return "Point2D [x=" + x + ", y=" + y + "]";
    }
}
