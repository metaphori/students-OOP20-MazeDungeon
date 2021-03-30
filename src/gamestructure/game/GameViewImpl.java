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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import animations.Animation;
import animations.Sprite;
import animations.SpriteIterator;
import animations.State;
import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.room.Rooms;
import model.shop.Items;
import viewutilities.ResourceLoader;
import viewutilities.WindowUtilities;

/**
 * This class contains the implementation of all the methods defined in the GameView interface.
 *
 * It also contains the events for the key pressed by user, and it has a connection with GameController.
 */
public class GameViewImpl implements GameView, KeyListener {

    private GameController controller;
    private final JFrame frame;
    private final WindowUtilities windowUtilities = new WindowUtilities();
    private final ImageIcon iconImage = new ImageIcon(this.getClass().getResource("/images/gameObject/character/idle/character1.png"));
    private final ImageIcon loadImage = new ImageIcon(this.getClass().getResource("/images/HUD/StartIstruction.png"));
    private static final Color BACKGROUND = new Color(11, 19, 30);
    private static final int PERIOD = 15;
    private final GamePanel gamePanel;
    private final Map<Integer, Animation> animations = new ConcurrentSkipListMap<>();
    private final ResourceLoader resourceLoader = new ResourceLoader();
    private final Timer timer;
    private final HUDPanel hudPanel = new HUDPanel(windowUtilities.getScreenRatio());
    private boolean gameOver;
    private boolean won;
    private static final int ISTRUCTION_TIME = 2000;
    private boolean loading = true;

    /**
     * Build a new GameView, setting all the default parameters, as the title, the background etc.
     * It also make the timer used for rendering start.
     */
    public GameViewImpl() {
        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setTitle("MazeDungeon");
        this.frame.setIconImage(this.iconImage.getImage());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        this.frame.getContentPane().setBackground(BACKGROUND);
        this.frame.add(gamePanel);
        this.frame.addKeyListener(this);
        timer = new Timer(PERIOD, gamePanel);
    }

    /**
     * Initialize the GameView by initializing the gamePanel.
     */
    @Override
    public void initialize() {
        this.gamePanel.initialize();
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void show() {
        final double frameWidth = WindowUtilities.NATIVE_WIDTH * WindowUtilities.WIDTH_RATIO * windowUtilities.getScreenRatio();
        final double frameHeigth = WindowUtilities.NATIVE_HEIGHT * WindowUtilities.HEIGHT_RATIO * windowUtilities.getScreenRatio();
        this.frame.getContentPane().setPreferredSize(new Dimension((int) (frameWidth),
                (int) (frameHeigth) + this.frame.getInsets().top + this.frame.getInsets().bottom));
        this.frame.pack();
        this.frame.setLocation(windowUtilities.getScreen().width / 2 - this.frame.getSize().width / 2,
                               windowUtilities.getScreen().height / 2 - this.frame.getSize().height / 2);
        this.frame.setVisible(true);
        gamePanel.setSize(this.frame.getSize());
        this.timer.start();
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void hide() {
        this.frame.dispose();
    }

    private class GamePanel extends JLayeredPane implements ActionListener {
        private static final long serialVersionUID = 1L;
        private final Image youLoseImage = adaptImage(new ImageIcon(this.getClass().getResource("/images/HUD/GameOver/gameOverFinal.png")));
        private final Image winnerImage = adaptImage(new ImageIcon(this.getClass().getResource("/images/HUD/Victory/winner.png")));
        private final Image roomImage = adaptImage(new ImageIcon(this.getClass().getResource("/images/Room/room.png")));
        private final Image loadImage =  adaptImage(new ImageIcon(this.getClass().getResource("/images/HUD/StartIstruction.png")));
        private final long openTime;

        GamePanel() {
            openTime = System.currentTimeMillis();
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            repaint();
            controller.setActive(frame.isActive());
        }

        @Override
        protected void paintComponent(final Graphics g) {
            if (loading && System.currentTimeMillis() - openTime < ISTRUCTION_TIME) {
                g.drawImage(this.loadImage, 0, 0, null);
                return;
            } else if (loading) {
                loading = false;
                this.add(hudPanel);
            }
            g.drawImage(this.roomImage, 0, 0, null);
            final Map<Integer, Animation> tmpAnimations = new ConcurrentSkipListMap<>(animations);
            tmpAnimations.entrySet().forEach(e -> {
                final Sprite sprite = e.getValue().getNext();
                while (!g.drawImage(sprite.getImg(), (int) Math.round(e.getValue().getPosition().getX()), (int) Math.round(e.getValue().getPosition().getY()), this)) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
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
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void setController(final GameController controller) {
       this.controller = controller;
       this.initialize();
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void updateHUD() {
        if (loading) {
            return;
        }
        hudPanel.updateLife(this.controller.getCharacterLife());
        hudPanel.updateCoinCounter(this.controller.getCharacterMoney());
        hudPanel.updateVisitedRooms(this.controller.getVisitedRoom(), Rooms.NUMBER_OF_ROOMS);
        hudPanel.updateBossLife(this.controller.getBossLife());
    }

    private Image adaptImage(final ImageIcon img) {
        final int width = (int) (img.getIconWidth() * windowUtilities.getScreenRatio());
        final int heigth = (int) (img.getIconHeight() * windowUtilities.getScreenRatio());
        return img.getImage().getScaledInstance(width, heigth, Image.SCALE_SMOOTH);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void addAnimation(final Integer id, final GameObjectType gameObjectType, final Point2D position) {
        final Map<State, List<ImageIcon>> stateMap = new HashMap<>(resourceLoader.getStateImages(gameObjectType));
        final Animation an = new Animation();
        an.setPosition(position);
        stateMap.keySet().forEach(s -> {
            an.addState(s, new SpriteIterator(stateMap.get(s).stream()
                                                             .map(i -> new Sprite(adaptImage(i), i.getIconWidth(), i.getIconHeight()))
                                                             .collect(Collectors.toList())));
        });
        animations.put(id, an);

        final ImageIcon image = stateMap.get(State.IDLE).get(0);
        this.controller.setBoundingBox(id, new BoundingBox(position, image.getIconWidth(), image.getIconHeight()));
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void updateAnimation(final int id, final Point2D position, final State state) {
        this.animations.get(id).setPosition(position.mul(windowUtilities.getScreenRatio()));
        this.animations.get(id).setState(state);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void removeAnimation(final int id) {
        this.animations.remove(id);
    }

    /**
     * It's notify the event at the controller.
     * 
     * @param key : the KeyEvent of the key pressed
     */
    @Override
    public void keyPressed(final KeyEvent key) {
        if (loading) {
            return;
        }
        if (this.controller != null) {
            this.controller.pressKey(key);
        }
    }

    /**
     * It's notify the event at the controller.
     * 
     * @param key : the KeyEvent of the key released
     */
    @Override
    public void keyReleased(final KeyEvent key) {
        if (loading) {
            return;
        }
        if (this.controller != null) {
            this.controller.releaseKey(key);
        }
    }

    @Override
    public void keyTyped(final KeyEvent arg0) {
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void renderItems(final Set<Items> purchasedItems) {
        for (final Items item : purchasedItems) {
            this.hudPanel.addItem(item);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void gameOver() {
        this.gameOver = true;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void isWon() {
        this.won = true;
    }
}


