package gamestructure.game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.shop.Items;

public class HUDPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int LEFT_BOUND = 10;

    private final List<Image> items = new LinkedList<>();

    HUDPanel() {
        this.setOpaque(false);
    }

    /**
     * @param item
     */
    public void addItem(final Items item) {
        switch (item) {
        case ARTHEMIDEBOW:
            items.add(new ImageIcon("resources/images/Item/arthemideBow.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
            break;
        case HERMESBOOTS:
            items.add(new ImageIcon("resources/images/Item/hermesBoots.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
            break;
        case ZEUSBOLT:
            items.add(new ImageIcon("resources/images/Item/zeusBolt.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
            break;
        case ORACLEAMULET:
            items.add(new ImageIcon("resources/images/Item/oracleAmulet.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
            break;
        default:
            break;
        }
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < items.size(); i++) {
            g.drawImage(items.get(i), 0, i * 64, null);
        }
    }
}
