package gamestructure.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;

import model.common.ResizableRectangle;
import model.shop.Items;

public class HUDPanel extends JLayeredPane {

    private static final long serialVersionUID = 1L;
    private static final int MARGIN = 10;
    private final ResizableRectangle coinPosition = new ResizableRectangle(MARGIN, 50, 50, 50);
    private final ResizableRectangle coinCounterPosition = new ResizableRectangle((int) coinPosition.getX() + (int) coinPosition.getWidth(), 
                                                                                  (int) coinPosition.getY(), 50, 50);
    private final ResizableRectangle roomVisitedPosition = new ResizableRectangle(1000, 10, 200, 20);
    private final ResizableRectangle finalArtefactPosition = new ResizableRectangle((int) roomVisitedPosition.getX(),
                                                                                    (int) roomVisitedPosition.getY() + (int) roomVisitedPosition.getHeight() + MARGIN,
                                                                                    170, 60);
    private final ResizableRectangle lifeBarPosition = new ResizableRectangle(MARGIN, MARGIN, 200, 30);
    private final ResizableRectangle itemsPosition = new ResizableRectangle(MARGIN, (int) coinPosition.getY() + (int) coinPosition.getHeight() + MARGIN, 
                                                                            64, 64);
    private static final int ROOM_VISITED_FONT_SIZE = 20;
    private static final int COIN_COUNTER_FONT_SIZE = 25;
    private static final Color LIFEBAR_FOREGROUND = new Color(150, 0, 0);
    private final JLabel lblCoinCounter = new JLabel();
    private final JLabel lblRoomVisited;
    private final Image coinImage;
    private final Image finalArtefactImage;
    private JProgressBar life;
    private boolean finalArtefactVisible = false;

    private final List<Image> items = new LinkedList<>();

    HUDPanel(final double screenRatio) {
        coinPosition.mul(screenRatio);
        coinCounterPosition.mul(screenRatio);
        finalArtefactPosition.mul(screenRatio);
        roomVisitedPosition.mul(screenRatio);
        lifeBarPosition.mul(screenRatio);
        itemsPosition.mul(screenRatio);

        this.coinImage = new ImageIcon("resources/images/HUD/Coins/coin.png").getImage().getScaledInstance((int) coinPosition.getWidth(),
                                                                                                           (int) coinPosition.getHeight(), 
                                                                                                           Image.SCALE_SMOOTH);
        this.finalArtefactImage = new ImageIcon("resources/images/Objects/FinalItem/spawned.png").getImage().getScaledInstance((int) finalArtefactPosition.getWidth(), 
                                                                                                                               (int) finalArtefactPosition.getHeight(), 
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
     * @param maxLife
     */
    public void initialize(final double maxLife) {
        life = new JProgressBar(0, (int) maxLife);
        life.setBounds(lifeBarPosition);
        life.setForeground(LIFEBAR_FOREGROUND);
        this.add(life);
    }

    /**
     * 
     * @param coins
     */
    public void updateCoinCounter(final int coins) {
        this.lblCoinCounter.setText(coins + "$");
    }

    /**
     * @param life
     */
    public void updateLife(final double life) {
        this.life.setValue((int) (life));
    }

    /**
     * @param nRooms
     * @param totalRooms
     */
    public void updateVisitedRooms(final int nRooms, final int totalRooms) {
        this.lblRoomVisited.setText("Visited Rooms: " + nRooms + "/" + totalRooms);
        if (nRooms == totalRooms) {
            this.finalArtefactVisible = true;
        }
    }

    /**
     * @param item
     */
    public void addItem(final Items item) {
        final ImageIcon tmpImage;
        switch (item) {
        case ARTHEMIDEBOW:
            tmpImage = new ImageIcon("resources/images/Item/arthemideBow.png");
            break;
        case HERMESBOOTS:
            tmpImage = new ImageIcon("resources/images/Item/hermesBoots.png");
            break;
        case ZEUSBOLT:
            tmpImage = new ImageIcon("resources/images/Item/zeusBolt.png");
            break;
        case ORACLEAMULET:
            tmpImage = new ImageIcon("resources/images/Item/oracleAmulet.png");
            break;
        default:
            return;
        }
        items.add(tmpImage.getImage().getScaledInstance((int) itemsPosition.getWidth(), (int) itemsPosition.getHeight(), Image.SCALE_SMOOTH));
    }

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
        Toolkit.getDefaultToolkit().sync();
    }
}
