package gamestructure.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.ResourceLoader;
import model.common.Sprite;
import model.common.Vector2D;

public class GameViewImpl implements GameView {

    private GameController controller;
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final JFrame frame;
    private static int NATIVE_WIDTH = 1920;
    private static int NATIVE_HEIGHT = 1080;
    private static double WIDTH_RATIO = 0.666667; 
    private static double HEIGHT_RATIO = 0.740740; 
    private static double ASPECT_RATIO = 1.6;
    private static final int PERIOD = 75;
    private final GamePanel gamePanel;
    private final Map<Integer, Sprite> sprites = new HashMap<>();
    private final ResourceLoader resourceLoader = new ResourceLoader();

    public GameViewImpl() {
        final Timer timer;
        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setTitle("MazeDungeon");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        //gamePanel.setSize(this.frame.getSize());
        this.frame.add(gamePanel);
        timer = new Timer(PERIOD, gamePanel);
        timer.start();
    }

    @Override
    public void show() {
        this.frame.setVisible(true);

        this.frame.setSize(new Dimension((int) (NATIVE_WIDTH * WIDTH_RATIO + this.frame.getInsets().right + this.frame.getInsets().left),
                (int) (NATIVE_HEIGHT * HEIGHT_RATIO) + this.frame.getInsets().top + this.frame.getInsets().bottom));
        gamePanel.setSize(this.frame.getSize());
        //this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), (int) (screen.getHeight() * HEIGHT_RATIO)));
        this.frame.setLocation(screen.width / 2 - this.frame.getSize().width / 2,
                               screen.height / 2 - this.frame.getSize().height / 2);

    }

    /**
     * 
     */
    @Override
    public void hide() {
        this.frame.setVisible(false);
    }

    private class GamePanel extends JPanel implements ActionListener {
        private static final long serialVersionUID = 1L;
        private final Image room;

        GamePanel() {
            room = adaptImage(new ImageIcon("resources/images/Room/room.png"));
        }

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            g.drawImage(room, 0, 0, null);
            for (final Sprite sprite : sprites.values()) {
                g.drawImage(sprite.getImg(), , , null);
            }
            Toolkit.getDefaultToolkit().sync();
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

    private int getNewWidth(final int width) {
        //return width * (int) screen.getWidth() / NATIVE_WIDTH;
        return width;
    }

    private int getNewHeight(final int height) {
        //return height * (int) screen.getHeight() / NATIVE_HEIGHT;
        return height;
    }

    private Image adaptImage(final ImageIcon img) {
        return img.getImage().getScaledInstance(getNewWidth(img.getIconWidth()), getNewHeight(img.getIconHeight()), Image.SCALE_SMOOTH);
    }

    /**
     * 
     */
    @Override
    public void addSprite(final Integer id, final GameObjectType gameObjectType) {
        final ImageIcon image = new ImageIcon(resourceLoader.getPath(gameObjectType));
        final Sprite sprite = new Sprite(adaptImage(image), image.getIconWidth(), image.getIconHeight());
        sprites.put(id, sprite);
    }

}


