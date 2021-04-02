package viewutilities;

import java.awt.Rectangle;

/**
 * A ResizableRectangle is an extension of Rectangle that permit 
 * to multiply values of height and width for a number n.
 */
public class ResizableRectangle extends Rectangle {

    private static final long serialVersionUID = 1L;

    /**
     * @param x : x coordinate of position 
     * @param y : y coordinate of position 
     * @param width : width of the rectangle
     * @param height : height of the rectangle
     */
    public ResizableRectangle(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
    }

    /**
     * Multiply the rectangle's height and width for the parameter.
     * @param n : multiplier
     */
    public void mul(final double n) {
        this.x  = (int) (this.getX() * n);
        this.y = (int) (this.getY() * n);
        this.width = (int) (this.getWidth() * n);
        this.height = (int) (this.getHeight() * n);
    }
}
