package gamestructure.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.ResourceLoader;
import model.common.Sprite;
import model.shop.Items;

public class GameViewImpl implements GameView, KeyListener {

    private GameController controller;
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final JFrame frame;
    private static final int NATIVE_WIDTH = 1920;
    private static final int NATIVE_HEIGHT = 1080;
    private static final double WIDTH_RATIO = 0.666_667; 
    private static final double HEIGHT_RATIO = 0.740_740; 
    private static final double ASPECT_RATIO = 1.6;
    private final double screenRatio = screen.getWidth() / NATIVE_WIDTH;
    private static final Color BACKGROUND = new Color(11, 19, 30);
    private static final int PERIOD = 15;
    private final GamePanel gamePanel;
    private final Map<Integer, Sprite> sprites = new ConcurrentSkipListMap<>();
    private final ResourceLoader resourceLoader = new ResourceLoader();
    private final Timer timer;
    private final HUDPanel hudPanel = new HUDPanel(screenRatio);
    private boolean gameOver = false;
    private boolean won = false;
    private final JLabel lblStartInstruction = new JLabel(new ImageIcon(adaptImage(new ImageIcon("resources/images/HUD/StartIstruction.png"))));
    private static final int ISTRUCTION_TIME = 0;

    public GameViewImpl() {
        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setTitle("MazeDungeon");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        gamePanel.setBackground(BACKGROUND);
        this.frame.add(gamePanel);
        this.frame.addKeyListener(this);
        this.frame.remove(this.lblStartInstruction);
        timer = new Timer(PERIOD, gamePanel);
        timer.start();
    }

    /**
     * 
     */
    @Override
    public void initialize() {
        this.gamePanel.initialize();
        this.timer.start();
    }

    /**
     * 
     */
    @Override
    public void show() {
        this.frame.setVisible(true); //QUESTO FA VENIRE LA BARRA BIANCA INIZIALE
        this.frame.setSize(new Dimension((int) (NATIVE_WIDTH * WIDTH_RATIO * screenRatio) + this.frame.getInsets().left,
                (int) (NATIVE_HEIGHT * HEIGHT_RATIO * screenRatio) + this.frame.getInsets().top));
        //this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), (int) (screen.getHeight() * HEIGHT_RATIO)));
        this.frame.setLocation(screen.width / 2 - this.frame.getSize().width / 2,
                               screen.height / 2 - this.frame.getSize().height / 2);
        this.frame.add(this.lblStartInstruction);
        try {
            Thread.sleep(ISTRUCTION_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gamePanel.setSize(this.frame.getSize());
    }

    /**
     * 
     */
    @Override
    public void hide() {
        this.frame.dispose();
    }

    private class GamePanel extends JLayeredPane implements ActionListener {
        private static final long serialVersionUID = 1L;
        private final Image youLoseImage = adaptImage(new ImageIcon("resources/images/HUD/GameOver/gameOverFinal.png"));
        private final Image winnerImage = adaptImage(new ImageIcon("resources/images/HUD/Victory/winner.png"));
        private final Image roomImage = adaptImage(new ImageIcon("resources/images/Room/room.png"));

        @Override
        public void actionPerformed(final ActionEvent e) {
            repaint();
        }

        @Override
        protected void paintComponent(final Graphics g) {
            final List<Sprite> temp = new ArrayList<>(sprites.values());
            g.drawImage(this.roomImage, 0, 0, null);
            temp.forEach(sprite -> {
                g.drawImage(sprite.getImg(), (int) Math.round(sprite.getPosition().getX()), (int) Math.round(sprite.getPosition().getY()), null);
            }); 
            if (gameOver) {
                g.drawImage(this.youLoseImage, 0, 0, null);
            } else if (won) {
                g.drawImage(this.winnerImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();
        }

        public void initialize() {
            hudPanel.initialize(controller.getCharacterLife());
            hudPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            this.add(hudPanel);
        }
    }

    /**
     * @param controller 
     */
    @Override
    public void setController(final GameController controller) {
       this.controller = controller;
       this.initialize();
    }

    /**
     * 
     */
    @Override
    public void updateHUD() {
        hudPanel.updateLife(this.controller.getCharacterLife());
        hudPanel.updateCoinCounter(this.controller.getCharacterMoney());
        hudPanel.updateVisitedRooms(this.controller.getVisitedRoom(), this.controller.getTotalRooms());
        hudPanel.updateBossLife(this.controller.getBossLife());
    }

    private Image adaptImage(final ImageIcon img) {
        //return width * (int) screen.getWidth() / NATIVE_WIDTH;
        //return height * (int) screen.getHeight() / NATIVE_HEIGHT;
        final int width = (int) (img.getIconWidth() * screenRatio);
        final int heigth = (int) (img.getIconHeight() * screenRatio);
        return img.getImage().getScaledInstance(width, heigth, Image.SCALE_SMOOTH);
    }

    /**
     * 
     */
    @Override
    public void addSprite(final Integer id, final GameObjectType gameObjectType, final Point2D position) {
        final ImageIcon image = new ImageIcon(resourceLoader.getPath(gameObjectType));
        final Sprite sprite = new Sprite(adaptImage(image), image.getIconWidth(), image.getIconHeight());
        sprite.setPosition(position);
        sprites.put(id, sprite);
        this.controller.setBoundingBox(id, new BoundingBox(position, image.getIconWidth(), image.getIconHeight()));
    }

    /**
     * @param id the id of the sprite to update.
     * @param position the new position of the sprite.
     */
    @Override
    public void setSpritePosition(final int id, final Point2D position) {
        this.sprites.get(id).setPosition(position.mul(screenRatio));
    }

    /**
     * @param id the id of the sprite that has to been removed.
     */
    @Override
    public void removeSprite(final int id) {
        this.sprites.remove(id);
    }

    @Override
    public void keyPressed(final KeyEvent key) {
        if (this.controller != null) {
            this.controller.pressKey(key);
        }
    }

    @Override
    public void keyReleased(final KeyEvent key) {
        if (this.controller != null) {
            this.controller.releaseKey(key);
        }
    }

    @Override
    public void keyTyped(final KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void gameOver() {
        this.gameOver = true;
    }

    @Override
    public void renderItems(final Set<Items> purchasedItems) {
        for (final Items item : purchasedItems) {
            this.hudPanel.addItem(item);
        }
    }

    @Override
    public void isWon() {
        this.won = true;
    }
}


