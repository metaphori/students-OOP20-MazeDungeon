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
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import gamestructure.PathGetter;
import gamestructure.WindowUtilities;
import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.ResourceLoader;
import model.common.animations.Animation;
import model.common.animations.Sprite;
import model.common.animations.SpriteIterator;
import model.common.animations.State;
import model.shop.Items;

public class GameViewImpl implements GameView, KeyListener {

    private GameController controller;
    private final JFrame frame;
    private final WindowUtilities windowUtilities = new WindowUtilities();
    private final PathGetter pathGetter = new PathGetter();
    private static final Color BACKGROUND = new Color(11, 19, 30);
    private static final int PERIOD = 15;
    private final GamePanel gamePanel;
    private final Map<Integer, Animation> animations = new ConcurrentSkipListMap<>();
    private final ResourceLoader resourceLoader = new ResourceLoader();
    private final Timer timer;
    private final HUDPanel hudPanel = new HUDPanel(windowUtilities.getScreenRatio());
    private boolean gameOver;
    private boolean won;
    private final JLabel lblStartInstruction = new JLabel(new ImageIcon(adaptImage(new ImageIcon(pathGetter.getPortablePath("resources/images/HUD/StartIstruction.png")))));
    private static final int ISTRUCTION_TIME = 2000;

    public GameViewImpl() {
        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setTitle("MazeDungeon");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        this.frame.setBackground(BACKGROUND);
        this.frame.add(gamePanel);
        this.frame.addKeyListener(this);
        this.frame.remove(this.lblStartInstruction);
        timer = new Timer(PERIOD, gamePanel);
        timer.start();
    }

    /**
     * Initialize the GameView by initializing the gamePanel and starting the timer.
     */
    @Override
    public void initialize() {
        this.gamePanel.initialize();
        this.timer.start();
    }

    /**
     * It show the GameView.
     */
    @Override
    public void show() {
        this.frame.setVisible(true);
        final double frameWidth = WindowUtilities.NATIVE_WIDTH * WindowUtilities.WIDTH_RATIO * windowUtilities.getScreenRatio();
        final double frameHeigth = WindowUtilities.NATIVE_HEIGHT * WindowUtilities.HEIGHT_RATIO * windowUtilities.getScreenRatio();
        this.frame.setSize(new Dimension((int) (frameWidth),
                (int) (frameHeigth) + this.frame.getInsets().top + this.frame.getInsets().bottom));
        this.frame.setLocation(windowUtilities.getScreen().width / 2 - this.frame.getSize().width / 2,
                               windowUtilities.getScreen().height / 2 - this.frame.getSize().height / 2);
        this.frame.add(this.lblStartInstruction);
        try {
            Thread.sleep(ISTRUCTION_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gamePanel.setSize(this.frame.getSize());
    }

    /**
     * Destroy the GameView.
     */
    @Override
    public void hide() {
        this.frame.dispose();
    }

    private class GamePanel extends JLayeredPane implements ActionListener {
        private static final long serialVersionUID = 1L;
        private final Image youLoseImage = adaptImage(new ImageIcon(pathGetter.getPortablePath("resources/images/HUD/GameOver/gameOverFinal.png")));
        private final Image winnerImage = adaptImage(new ImageIcon(pathGetter.getPortablePath("resources/images/HUD/Victory/winner.png")));
        private final Image roomImage = adaptImage(new ImageIcon(pathGetter.getPortablePath("resources/images/Room/room.png")));

        @Override
        public void actionPerformed(final ActionEvent e) {
            repaint();
        }

        @Override
        protected void paintComponent(final Graphics g) {
            g.drawImage(this.roomImage, 0, 0, null);
            final Map<Integer, Animation> tmpAnimations = new ConcurrentSkipListMap<>(animations);
            tmpAnimations.entrySet().forEach(e -> {
                final Sprite sprite = e.getValue().getNext(controller.getStateFromId(e.getKey()));
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
            this.add(hudPanel);
        }
    }

    /**
     * @param controller : set controller ad the controller of the GameView
     */
    @Override
    public void setController(final GameController controller) {
       this.controller = controller;
       this.initialize();
    }

    /**
     * Update the Heads Up Display.
     */
    @Override
    public void updateHUD() {
        hudPanel.updateLife(this.controller.getCharacterLife());
        hudPanel.updateCoinCounter(this.controller.getCharacterMoney());
        hudPanel.updateVisitedRooms(this.controller.getVisitedRoom(), this.controller.getTotalRooms());
        hudPanel.updateBossLife(this.controller.getBossLife());
    }

    private Image adaptImage(final ImageIcon img) {
        final int width = (int) (img.getIconWidth() * windowUtilities.getScreenRatio());
        final int heigth = (int) (img.getIconHeight() * windowUtilities.getScreenRatio());
        return img.getImage().getScaledInstance(width, heigth, Image.SCALE_SMOOTH);
    }

    /**
     * @param id : the id of the GameObject to load the sprite of.
     * @param gameObjectType : the type of the GameObject for the correct loading of it's image.
     * @param position : the position where to print the image.
     */
    @Override
    public void addSprite(final Integer id, final GameObjectType gameObjectType, final Point2D position) {
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
     * @param id : the id of the sprite to update.
     * @param position : the new position of the sprite.
     */
    @Override
    public void setSpritePosition(final int id, final Point2D position) {
        this.animations.get(id).setPosition(position.mul(windowUtilities.getScreenRatio()));
    }

    /**
     * @param id : the id of the sprite that has to been removed.
     */
    @Override
    public void removeAnimation(final int id) {
        this.animations.remove(id);
    }

    /**
     * @param key : the KeyEvent of the key pressed
     * it's notify the event at the controller
     */
    @Override
    public void keyPressed(final KeyEvent key) {
        if (this.controller != null) {
            this.controller.pressKey(key);
        }
    }

    /**
     * @param key : the KeyEvent of the key released
     * it's notify the event at the controller
     */
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

    /**
     * When called set the gameOvervariable at true.
     */
    @Override
    public void gameOver() {
        this.gameOver = true;
    }

    /**
     * @param purchasedItems : the Set of the item purchased by the player.
     * It add all the item purchased to the HUD panel
     */
    @Override
    public void renderItems(final Set<Items> purchasedItems) {
        for (final Items item : purchasedItems) {
            this.hudPanel.addItem(item);
        }
    }

    /**
     * When called set the won at true.
     */
    @Override
    public void isWon() {
        this.won = true;
    }
}


