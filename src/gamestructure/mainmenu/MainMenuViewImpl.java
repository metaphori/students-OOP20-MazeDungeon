package gamestructure.mainmenu;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenuViewImpl implements MainMenuView {

    private final JFrame frame = new JFrame();

    public MainMenuViewImpl() {
        BufferedImage img;
        JLabel label;
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(750, 750);

        try {
            img = ImageIO.read(new File("res//images//MainMenù//MainMenù1.png"));
            label = new JLabel(new ImageIcon(img));
            this.frame.add(label);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @Override
     */
    public void show() {
        this.frame.setVisible(true);
    }

    /**
     * @Override
     */
    public void hide() {
        this.frame.setVisible(false);
    }

}
