package model.common;

public class BoundingBox {
    private Point2D upperLeft;
    private Point2D bottomRight;
    private final double width;
    private final double height;

    public BoundingBox(final Point2D upperLeft, final double width, final double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.bottomRight = new Point2D(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * @return .
     */
    public Point2D getULCorner() {
            return upperLeft;
    }

    /**
     * @return .
     */
    public Point2D getBRCorner() {
            return bottomRight;
    }

    public double getHeight() {
        return this.height;
    }
    
    public double getWidth() {
        return this.width;
    }

    /**
     * @param upperLeft
     */
    public void move(final Point2D upperLeft) {
        this.upperLeft = upperLeft;
        this.bottomRight = new Point2D(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * @param box
     * @return .
     */
    public boolean intersectWith(final BoundingBox box) {
        if(box == null) {
            return false;
        }
        if (this.upperLeft.getX() >= box.bottomRight.getX() || box.upperLeft.getX() >= this.bottomRight.getX()) { 
            return false; 
        } 
        if (this.upperLeft.getY() >= box.bottomRight.getY() || box.upperLeft.getY() >= this.bottomRight.getY()) { 
            return false; 
        }
        return true;
    }
}
