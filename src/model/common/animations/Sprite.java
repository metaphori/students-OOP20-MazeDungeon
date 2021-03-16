package model.common.animations;

import java.awt.Image;

import model.common.Point2D;

public class Sprite {
    private final Image img;
    private final int width;
    private final int height;
    private Point2D position;

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

    /**
     * 
     * @param position
     */
    public void setPosition(final Point2D position) {
        this.position = position;
    }

    /**
     * 
     * @return position of the sprite
     */
    public Point2D getPosition() {
        return position;
    }

}
