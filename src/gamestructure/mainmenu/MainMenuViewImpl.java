package gamestructure.mainmenu;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuViewImpl implements MainMenuView {

    private final JFrame frame = new JFrame();
    private static double WIDTH_RATIO = 0.67; 
    private static double HEIGHT_RATIO = 0.736;

    public MainMenuViewImpl() {
        BufferedImage img;
        JLabel label;
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), 
                (int) (screen.getHeight() * HEIGHT_RATIO)));
        final JPanel mainPanel = new JPanel(new GridBagLayout());
        this.frame.setContentPane(mainPanel);

        try {
            img = ImageIO.read(new File("resources//images//MainMenu//MainMenu3.png"));
            label = new JLabel(new ImageIcon(img));
            this.frame.add(label);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Override
     */
    public void show() {
        this.frame.pack();
        this.frame.setVisible(true);
    }

    /**
     * @Override
     */
    public void hide() {
        this.frame.setVisible(false);
    }

}
