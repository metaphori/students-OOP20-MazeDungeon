package viewutilities;

import java.awt.Rectangle;

public class ResizableRectangle extends Rectangle {

    private static final long serialVersionUID = 1L;

    public ResizableRectangle(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
    }

    /**
     * @param n
     */
    public void mul(final double n) {
        this.x  = (int) (this.getX() * n);
        this.y = (int) (this.getY() * n);
        this.width = (int) (this.getWidth() * n);
        this.height = (int) (this.getHeight() * n);
    }
}
