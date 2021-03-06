package gamestructure.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;

import model.common.Point2D;
import model.shop.Items;

public class HUDPanel extends JLayeredPane {

    private static final long serialVersionUID = 1L;
    private int leftBoundItems = 10;
    private int itemSize = 64;
    private final Point2D coinPosition;
    private final JLabel lblCoinCounter = new JLabel();
    private final JLabel lblRoomVisited;
    private final Image coinImage; 
    private JProgressBar life;
    private final double screenRatio;

    private final List<Image> items = new LinkedList<>();

    HUDPanel(final double screenRatio) {
        this.screenRatio = screenRatio;
        itemSize *= screenRatio;
        coinPosition = new Point2D(leftBoundItems, 50 * screenRatio);
        final ImageIcon coin = new ImageIcon("resources/images/HUD/Coins/coin.png");
        lblCoinCounter.setBounds((int) (50 * screenRatio) + leftBoundItems , (int) coinPosition.getY(), 
                                 (int) (50 * screenRatio), (int) (50 * screenRatio));
        this.coinImage = adaptImage(coin);
        this.setOpaque(false);

        this.lblRoomVisited = new JLabel();
        this.lblRoomVisited.setBounds(1000, 10, 200, 20);
        this.lblRoomVisited.setFont(new Font("Helvetica", Font.ITALIC, (int) (20 * screenRatio)));
        this.lblRoomVisited.setForeground(Color.white);

        lblCoinCounter.setText("0");
        lblCoinCounter.setFont(new Font("Helvetica", Font.ITALIC, (int) (25 * screenRatio)));
        lblCoinCounter.setForeground(Color.white);
        this.add(this.lblRoomVisited);
        this.add(this.lblCoinCounter);
    }

    /**
     * 
     * @param maxLife
     */
    public void initialize(final double maxLife) {
        life = new JProgressBar(0, (int) maxLife);
        life.setBounds(leftBoundItems, (int) (10 * screenRatio), (int) (200 * screenRatio), (int) (30 * screenRatio));
        life.setForeground(new Color((int) (150 * screenRatio), 0, 0));
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
        items.add(tmpImage.getImage().getScaledInstance(itemSize, itemSize, Image.SCALE_SMOOTH));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < items.size(); i++) {
            g.drawImage(items.get(i), leftBoundItems, i * itemSize, null);
        }
        g.drawImage(this.coinImage, leftBoundItems, (int) (50 * screenRatio), null);
        Toolkit.getDefaultToolkit().sync();
    }

    private Image adaptImage(final ImageIcon img) {
        //return width * (int) screen.getWidth() / NATIVE_WIDTH;
        //return height * (int) screen.getHeight() / NATIVE_HEIGHT;
        final int width = (int) (img.getIconWidth() * screenRatio);
        final int heigth = (int) (img.getIconHeight() * screenRatio);
        return img.getImage().getScaledInstance(width, heigth, Image.SCALE_SMOOTH);
    }
}
