package gamestructure;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import model.common.ResizableRectangle;

public class WindowUtilities {

    /**
     * default screen width.
     */
    public static final int NATIVE_WIDTH = 1920;

    /**
     * default screen height.
     */
    public static final int NATIVE_HEIGHT = 1080;

    /**
     * width percentage used from the game window.
     */
    public static final double WIDTH_RATIO = 0.666_667; 

    /**
     * height percentage used from the game window.
     */
    public static final double HEIGHT_RATIO = 0.740_740;
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final double screenRatio = screen.getWidth() / NATIVE_WIDTH;

    /**
     * @return the screen size
     */
    public Dimension getScreen() {
        return screen;
    }

    /**
     * @return the ratio between the screen width and the native width
     */
    public double getScreenRatio() {
        return screenRatio;
    }

    /**
     * @param startImgIcon the start image to resize
     * @param rectangle the dimension for resize the image
     * @return the resized image
     */
    public ImageIcon resizeImage(final ImageIcon startImgIcon, final ResizableRectangle rectangle) {
        final Image img = startImgIcon.getImage().getScaledInstance((int) rectangle.getWidth(), (int) rectangle.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
