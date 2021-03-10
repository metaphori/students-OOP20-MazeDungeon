package gamestructure;

import java.awt.Dimension;
import java.awt.Toolkit;

public class WindowUtilities {
    /**
     * 
     */
    public static final int NATIVE_WIDTH = 1920;
    /**
     * 
     */
    public static final int NATIVE_HEIGHT = 1080;
    /**
     * 
     */
    public static final double WIDTH_RATIO = 0.666_667; 
    /**
     * 
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
     * @return the screen ratio
     */
    public double getScreenRatio() {
        return screenRatio;
    }
}
