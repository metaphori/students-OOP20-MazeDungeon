package model.common;

import java.awt.Image;

public class Sprite {
    private final Image img;
    private final int width;
    private final int height;

    public Sprite(final Image img, final int width, final int height) {
        this.img = img;
        this.width = width;
        this.height = height;
    }

    /**
     * 
     * @return image
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
     * @return heigth of the image
     */
    public int getHeight() {
        return height;
    }

}
