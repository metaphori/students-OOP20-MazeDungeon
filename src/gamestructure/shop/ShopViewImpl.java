package gamestructure.shop;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class ShopViewImpl  implements ShopView {

    private static final long serialVersionUID = 1L;
    private final JFrame frame = new JFrame();
    private static int WIDTH_RATIO = 1;
    private static int HEIGHT_RATIO = 1;
    private String sep = File.separator;


    public ShopViewImpl() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), 
                                         (int) (screen.getHeight() * HEIGHT_RATIO)));
        
        System.out.println(sep);
        final JPanel mainPanel = new JPanel(new GridBagLayout());
        this.frame.setContentPane(mainPanel);

        try {
            final BufferedImage myPicture = ImageIO.read(new File("resources"+sep+"images"+sep+"MainMenu"+sep+"MainMenu3.png"));
            final JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            this.frame.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final JButton btn = new JButton("PROVA");

        

    }

    /**
     * 
     */
    public void show() {
        this.frame.pack();
        this.frame.setVisible(true);

    }

    /**
     * 
     */
    public void hide() {
        this.frame.setVisible(false);
    }

}
