package gamestructure.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;

import model.shop.Items;
import viewutilities.ResizableRectangle;

/**
 * an HUD panel permit to show information about the game on the frame.
 * In particular shows the life of the character, the amount of coins collected, 
 * the owned items, the number of visited rooms and the life bar of the boss if there is
 * the boss in the room.
 */
public class HUDPanel extends JLayeredPane {

    private static final long serialVersionUID = 1L;
    private static final int MARGIN = 10;
    private static final int ROOM_VISITED_FONT_SIZE = 20;
    private static final int COIN_COUNTER_FONT_SIZE = 25;
    private static final Color CHAR_LIFEBAR_FOREGROUND = new Color(150, 0, 0);
    private static final Color BOSS_LIFEBAR_FOREGROUND = new Color(167, 142, 13);

    //creating rectangles, one for each HUD element
    private final ResizableRectangle coinPosition = new ResizableRectangle(MARGIN, 50, 50, 50);
    private final ResizableRectangle coinCounterPosition = new ResizableRectangle((int) coinPosition.getX() + (int) coinPosition.getWidth(), 
                                                                                  (int) coinPosition.getY(), 50, 50);
    private final ResizableRectangle roomVisitedPosition = new ResizableRectangle(980, 10, 220, 20);
    private final ResizableRectangle finalArtefactPosition = new ResizableRectangle((int) roomVisitedPosition.getX(),
                                                                                    (int) roomVisitedPosition.getY() + (int) roomVisitedPosition.getHeight() + MARGIN,
                                                                                    170, 60);
    private final ResizableRectangle characterLifeBarPosition = new ResizableRectangle(MARGIN, MARGIN, 200, 30);
    private final ResizableRectangle itemsPosition = new ResizableRectangle(MARGIN, (int) coinPosition.getY() + (int) coinPosition.getHeight() + MARGIN, 
                                                                            64, 64);
    private final ResizableRectangle bossLifeBarPosition = new ResizableRectangle(360, MARGIN, 500, 30);
    private final ResizableRectangle bossIconPosition = new ResizableRectangle(290, 0, 74, 49);

    //HUD elements
    private final JLabel lblCoinCounter = new JLabel();
    private final JLabel lblRoomVisited;
    private final Image coinImage;
    private final Image finalArtefactImage;
    private final Image bossIconImage;
    private JProgressBar characterLifeBar;
    private Optional<JProgressBar> bossLifeBar = Optional.empty();
    private boolean finalArtefactVisible;
    private final List<Image> items = new LinkedList<>();

    /**
     * 
     * @param screenRatio : the ratio between the screen width and the native width
     */
    HUDPanel(final double screenRatio) {
        coinPosition.mul(screenRatio);
        coinCounterPosition.mul(screenRatio);
        finalArtefactPosition.mul(screenRatio);
        roomVisitedPosition.mul(screenRatio);
        characterLifeBarPosition.mul(screenRatio);
        itemsPosition.mul(screenRatio);
        bossLifeBarPosition.mul(screenRatio);
        bossIconPosition.mul(screenRatio);

        this.coinImage = new ImageIcon(this.getClass().getResource("/images/HUD/Coins/coin.png")).getImage().getScaledInstance((int) coinPosition.getWidth(),
                                                                                                           (int) coinPosition.getHeight(), 
                                                                                                           Image.SCALE_SMOOTH);
        this.finalArtefactImage = new ImageIcon(this.getClass().getResource("/images/gameObject/final_artifact/spawned.png")).getImage().getScaledInstance((int) finalArtefactPosition.getWidth(), 
                                                                                                                               (int) finalArtefactPosition.getHeight(), 
                                                                                                                               Image.SCALE_SMOOTH);
        this.bossIconImage = new ImageIcon(this.getClass().getResource("/images/gameObject/enemy_boss/idle/enemy_boss1.png")).getImage().getScaledInstance((int) bossIconPosition.getWidth(), 
                                                                                                               (int) bossIconPosition.getHeight(), 
                                                                                                               Image.SCALE_SMOOTH);
        this.lblRoomVisited = new JLabel();
        this.lblRoomVisited.setBounds(roomVisitedPosition);
        this.lblRoomVisited.setFont(new Font("Helvetica", Font.ITALIC, (int) (ROOM_VISITED_FONT_SIZE * screenRatio)));
        this.lblRoomVisited.setForeground(Color.white);

        lblCoinCounter.setText("0");
        lblCoinCounter.setFont(new Font("Helvetica", Font.ITALIC, (int) (COIN_COUNTER_FONT_SIZE * screenRatio)));
        lblCoinCounter.setForeground(Color.white);
        lblCoinCounter.setBounds(coinCounterPosition);
        this.add(this.lblRoomVisited);
        this.add(this.lblCoinCounter);
        this.setOpaque(false);
    }

    /**
     * 
     * @param maxLife : the max life of the character
     */
    public void initialize(final double maxLife) {
        characterLifeBar = new JProgressBar(0, (int) maxLife);
        characterLifeBar.setBounds(characterLifeBarPosition);
        characterLifeBar.setForeground(CHAR_LIFEBAR_FOREGROUND);
        this.add(characterLifeBar);
    }

    /**
     * 
     * @param life : the life of the boss if there is.
     */
    public void updateBossLife(final Optional<Double> life) {
        if (life.isEmpty() && bossLifeBar.isEmpty()) {
            return;
        }
        if (life.isEmpty() && bossLifeBar.isPresent()) {
            this.remove(bossLifeBar.get());
            bossLifeBar = Optional.empty();
            return;
        }
        if (life.isPresent() && bossLifeBar.isEmpty()) {
            bossLifeBar = Optional.of(new JProgressBar(0, life.get().intValue()));
            bossLifeBar.get().setBounds(bossLifeBarPosition);
            bossLifeBar.get().setForeground(BOSS_LIFEBAR_FOREGROUND);
            this.add(bossLifeBar.get());
        }
        this.bossLifeBar.get().setValue(life.get().intValue());
    }

    /**
     * 
     * @param coins : the actual amount of coins
     */
    public void updateCoinCounter(final int coins) {
        this.lblCoinCounter.setText(coins + "$");
    }

    /**
     * @param life : the actual life of the character
     */
    public void updateLife(final double life) {
        this.characterLifeBar.setValue((int) (life));
    }

    /**
     * Update the number of visited rooms.
     * @param nRooms : the number of visited rooms
     * @param totalRooms : the total number of rooms
     */
    public void updateVisitedRooms(final int nRooms, final int totalRooms) {
        this.lblRoomVisited.setText("Visited Rooms: " + nRooms + "/" + totalRooms);
        if (nRooms == totalRooms) {
            this.finalArtefactVisible = true;
        }
    }

    /**
     * Add a bought item to the view.
     * @param item : the new item to add
     */
    public void addItem(final Items item) {
        final ImageIcon tmpImage;
        switch (item) {
        case ARTHEMIDEBOW:
            tmpImage = new ImageIcon(this.getClass().getResource("/images/Item/arthemideBow.png"));
            break;
        case HERMESBOOTS:
            tmpImage = new ImageIcon(this.getClass().getResource("/images/Item/hermesBoots.png"));
            break;
        case ZEUSBOLT:
            tmpImage = new ImageIcon(this.getClass().getResource("/images/Item/zeusBolt.png"));
            break;
        case ORACLEAMULET:
            tmpImage = new ImageIcon(this.getClass().getResource("/images/Item/oracleAmulet.png"));
            break;
        default:
            return;
        }
        items.add(tmpImage.getImage().getScaledInstance((int) itemsPosition.getWidth(), (int) itemsPosition.getHeight(), Image.SCALE_SMOOTH));
    }

    /**
     * 
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < items.size(); i++) {
            g.drawImage(items.get(i), (int) itemsPosition.getX(), i * (int) itemsPosition.getHeight() + (int) itemsPosition.getY(), null);
        }
        g.drawImage(this.coinImage, (int) coinPosition.getX(), (int) coinPosition.getY(), null);
        if (finalArtefactVisible) {
            g.drawImage(this.finalArtefactImage, (int) finalArtefactPosition.getX(), (int) finalArtefactPosition.getY(), null);
        }
        if (bossLifeBar.isPresent()) {
            g.drawImage(this.bossIconImage, (int) bossIconPosition.getX(), (int) bossIconPosition.getY(), null);
        }
        Toolkit.getDefaultToolkit().sync();
    }
}
