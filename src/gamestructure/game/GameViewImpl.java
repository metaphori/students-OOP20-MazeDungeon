package gamestructure.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.common.Point2D;
import model.common.Vector2D;

public class GameViewImpl implements GameView {

    private GameController controller;
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final JFrame frame;
    private static int NATIVE_WIDTH = 1920;
    private static int NATIVE_HEIGHT = 1080;
    private static double WIDTH_RATIO = 0.67; 
    private static double HEIGHT_RATIO = 0.74; 
    private static double ASPECT_RATIO = 1.6;
    private static final int PERIOD = 75;

    private Timer timer;

    public GameViewImpl() {
        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setTitle("MazeDungeon");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final GamePanel gamePanel = new GamePanel();
        gamePanel.setSize(this.frame.getSize());
        this.frame.add(gamePanel);
        timer = new Timer(PERIOD, gamePanel);
        timer.start();
    }

    @Override
    public void show() {
        this.frame.setVisible(true);
        this.frame.setSize(new Dimension((int) (NATIVE_WIDTH * WIDTH_RATIO), (int) (NATIVE_HEIGHT * HEIGHT_RATIO) + this.frame.getInsets().top));
        System.out.println("frame: w: " + frame.getWidth() + " h: " + frame.getHeight() + " insets h: " + frame.getInsets().top);
        //this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), (int) (screen.getHeight() * HEIGHT_RATIO)));
        this.frame.setLocation(screen.width / 2 - this.frame.getSize().width / 2,
                               screen.height / 2 - this.frame.getSize().height / 2);
    }

    @Override
    public void hide() {
        this.frame.setVisible(false);
    }

    private class GamePanel extends JPanel implements ActionListener {
        private static final long serialVersionUID = 1L;
        private final Image room;
        private Point2D position = new Point2D(800, 300);
        private final Vector2D vector = new Vector2D(0, 0);
        private final List<Image> images = new LinkedList<>();
        private int index = 0;

        GamePanel() {
            images.add(adaptImage(new ImageIcon("resources//images//Objects//Coin//coin1.png")));
            images.add(adaptImage(new ImageIcon("resources//images//Objects//Coin//coin2.png")));
            images.add(adaptImage(new ImageIcon("resources//images//Objects//Coin//coin3.png")));
            images.add(adaptImage(new ImageIcon("resources//images//Objects//Coin//coin4.png")));
            images.add(adaptImage(new ImageIcon("resources//images//Objects//Coin//coin5.png")));
            images.add(adaptImage(new ImageIcon("resources//images//Objects//Coin//coin6.png")));
            images.add(adaptImage(new ImageIcon("resources//images//Objects//Coin//coin7.png")));
            room = adaptImage(new ImageIcon("resources//images//Room//room.png"));
            System.out.println("room: w: " + new ImageIcon("resources//images//Room//room.png").getIconWidth()
                                    + " h: " + new ImageIcon("resources//images//Room//room.png").getIconHeight());
        }

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            g.drawImage(room, 0, 0, null);
            g.drawImage(images.get(index), position.getX(), position.getY(), null); 
            Toolkit.getDefaultToolkit().sync();
            position = position.sum(vector);
            index++;
            if (index == images.size() - 1) {
                index = 0;
            }
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            repaint();
        }
    }

    @Override
    public void setController(final GameController controller) {
       this.controller = controller;
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
    }

    private int getNewWidth(int width) {
        //return width * (int) screen.getWidth() / NATIVE_WIDTH;
        return width;
    }

    private int getNewHeight(int height) {
        //return height * (int) screen.getHeight() / NATIVE_HEIGHT;
        return height;
    }

    private Image adaptImage(ImageIcon img) {
        return img.getImage().getScaledInstance(getNewWidth(img.getIconWidth()), getNewHeight(img.getIconHeight()), Image.SCALE_SMOOTH);
    }

}


