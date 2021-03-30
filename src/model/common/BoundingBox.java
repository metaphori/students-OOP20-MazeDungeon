package model.common;

/**
 * The BoundingBox is a class that permit to check a collision with an 
 * other BondingBox.
 */
public class BoundingBox {
    private Point2D upperLeft;
    private Point2D bottomRight;
    private final double width;
    private final double height;

    /**
     * @param upperLeft : the upper left corner
     * @param width : the width of the BoundingBox
     * @param height : the height of the BoundingBox
     */
    public BoundingBox(final Point2D upperLeft, final double width, final double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.bottomRight = new Point2D(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * @return the upper left corner
     */
    public Point2D getULCorner() {
            return upperLeft;
    }

    /**
     * @return the bottom right corner
     */
    public Point2D getBRCorner() {
            return bottomRight;
    }

    /**
     * @return the height of the BoundingBox
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the width of the BoundingBox
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @param newUL : the new upper left corner
     */
    public void move(final Point2D newUL) {
        this.upperLeft = newUL;
        this.bottomRight = new Point2D(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * check collisions between two bounding box.
     * @param box : the BoundingBox to check collision with
     * @return true if the two BoundingBox collide, false otherwise
     */
    public boolean intersectWith(final BoundingBox box) {
        if (box == null) {
            return false;
        }
        return !(this.upperLeft.getX() >= box.bottomRight.getX() || box.upperLeft.getX() >= this.bottomRight.getX()
                || this.upperLeft.getY() >= box.bottomRight.getY() || box.upperLeft.getY() >= this.bottomRight.getY());
    }
}
