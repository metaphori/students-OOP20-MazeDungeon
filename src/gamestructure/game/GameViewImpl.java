package gamestructure.game;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameViewImpl implements GameView {

    private static final long serialVersionUID = 1L;
    private final JFrame frame;
    private static double WIDTH_RATIO = 0.67; 
    private static double HEIGHT_RATIO = 0.736; 

    public GameViewImpl() {
        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setTitle("MazeDungeon");
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel backgroundPanel = new JPanel(new GridBagLayout());
        this.frame.setContentPane(backgroundPanel);
        try {
            final int height = (int) (screen.getHeight() * HEIGHT_RATIO);
            final int width = (int) (screen.getWidth() * WIDTH_RATIO);
            final BufferedImage room = ImageIO.read(new File("resources//images//Room//room.png"));
            final JLabel picLabel = new JLabel(new ImageIcon(room.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
            backgroundPanel.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void show() {
        this.frame.pack();
        this.frame.setVisible(true);
    }

    @Override
    public void hide() {
        this.frame.setVisible(false);
    }

}
