package gamestructure.shop;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class ShopViewImpl  implements ShopView {

    private static final long serialVersionUID = 1L;
    private final JFrame frame = new JFrame();
    private static int WIDTH_RATIO = 1;
    private static int HEIGHT_RATIO = 1;


    public ShopViewImpl() {
        //this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setSize(screen);
        this.frame.setResizable(false);

        try {
            BufferedImage myPicture = ImageIO.read(new File("res\\images\\MainMenù\\MainMenù.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            this.frame.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     */
    public void show() {
        this.frame.setVisible(true);

    }

    /**
     * 
     */
    public void hide() {
        this.frame.setVisible(false);
    }

}
