package animations;

import java.awt.Image;

/**
 * represent an image with an height and a width.
 */
public class Sprite {
    private final Image img;
    private final int width;
    private final int height;

    /**
     * 
     * @param img : Image of the Sprite
     * @param width : width of the Sprite
     * @param height : height of the Sprite
     */
    public Sprite(final Image img, final int width, final int height) {
        this.img = img;
        this.width = width;
        this.height = height;
    }

    /**
     * @return the image linked at the sprite
     */
    public Image getImg() {
        return img;
    }

    /**
     * 
     * @return width of the image
     */
    public int getWidth() {
        return width;
    }

    /**
     * 
     * @return height of the image
     */
    public int getHeight() {
        return height;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return this.getHeight() + " ";
    }

}
