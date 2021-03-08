package gamestructure.ingamemenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import gamestructure.ingamemenu.utilities.ImageLoader;
import gamestructure.ingamemenu.utilities.Images;
import model.shop.Items;

public class InGameMenuViewImpl implements InGameMenuView  {
    private static final int SIZE_IMAGE_ITEM = 100;
    private static final double WIDTH_RATIO = 0.67; 
    private static final double HEIGHT_RATIO = 0.736;
    private static final String FONT_ALGERIAN = "Algerian";
    private String priceArthemideBow;
    private String priceHermesBoots;
    private String priceZeusBolth;
    private String priceHelath;
    private String priceOracleAmulet;
    private final JFrame frame = new JFrame();
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private final ImageLoader  imageIconSources = new ImageLoader();
    private final JLabel lblBackgroundMenu = new JLabel(imageIconSources.getImage(Images.BACKGROUNDMENU));
    private final JLabel lblBackgroundShop = new JLabel(imageIconSources.getImage(Images.BACKGROUNDSHOP));
    private final JButton btnResumeMenu = new JButton("", imageIconSources.getImage(Images.BTNRESUME));
    private final JButton btnExitMenu = new JButton("", imageIconSources.getImage(Images.BTNEXIT)); 
    private final JButton btnShopMenu = new JButton("", imageIconSources.getImage(Images.BTNSHOP)); 
    private final JButton btnArthemideBowItem = new JButton("", imageIconSources.getImage(Images.BTNARTHEMIDEBOW)); 
    private final JButton btnHermesBootsItem = new JButton("", imageIconSources.getImage(Images.BTNHERMESBOOTS)); 
    private final JButton btnZeusBoltItem = new JButton("", imageIconSources.getImage(Images.BTNZEUSBOLT)); 
    private final JButton btnHealthItem = new JButton("", imageIconSources.getImage(Images.BTNHEALTH)); 
    private final JButton btnOracleAmulet = new JButton("", imageIconSources.getImage(Images.BTNORACLEAMULET)); 
    private final JButton btnBackToMenu = new JButton("", imageIconSources.getImage(Images.BTNRETURNMENU)); 
    private final JPanel inGameMenuPanel = new JPanel(new GridBagLayout());
    private final JPanel shopPanel = new JPanel(new GridBagLayout());
    private final JLabel msg = new JLabel();

    private static final Color COLOR_BACKGROUND = new Color(11, 23, 30, 255);
    private boolean start;

    private final InGameMenuController controller;
    public InGameMenuViewImpl(final InGameMenuController controller) {
        this.controller = controller;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), 
                (int) (screen.getHeight() * HEIGHT_RATIO)));
        inGameMenuPanel.add(lblBackgroundMenu);
        shopPanel.add(lblBackgroundShop);
        this.frame.setTitle("MazeDungeon");
    }
    /**
     * show shop panel and set button and label.
     */
    public void showShop() {
        this.frame.remove(inGameMenuPanel);
        this.frame.setContentPane(shopPanel);

        btnBackToMenu.setBounds((int) (screen.getWidth() / 4), (int) (screen.getHeight() / 1.5), 300, 90);
        this.configureButton(btnBackToMenu);
        this.lblBackgroundShop.add(btnBackToMenu);
        btnBackToMenu.addActionListener(e -> this.controller.openInGameMenu());
        showSelectButton(btnBackToMenu);

        btnArthemideBowItem.setBounds(45, 180, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnArthemideBowItem);
        this.lblBackgroundShop.add(btnArthemideBowItem);
        btnArthemideBowItem.addActionListener(e -> this.controller.buyItem(Items.ARTHEMIDEBOW));
        showSelectButton(btnArthemideBowItem);

        btnHermesBootsItem.setBounds(208, 175, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnHermesBootsItem);
        this.lblBackgroundShop.add(btnHermesBootsItem);
        btnHermesBootsItem.addActionListener(e -> this.controller.buyItem(Items.HERMESBOOTS));
        showSelectButton(btnHermesBootsItem);

        btnZeusBoltItem.setBounds(350, 190, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnZeusBoltItem);
        this.lblBackgroundShop.add(btnZeusBoltItem);
        btnZeusBoltItem.addActionListener(e -> this.controller.buyItem(Items.ZEUSBOLT));
        showSelectButton(btnZeusBoltItem);

        btnHealthItem.setBounds(510, 180, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnHealthItem);
        this.lblBackgroundShop.add(btnHealthItem);
        btnHealthItem.addActionListener(e -> this.controller.buyItem(Items.HEALTH));
        showSelectButton(btnHealthItem);


        btnOracleAmulet.setBounds(680, 180, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnOracleAmulet);
        this.lblBackgroundShop.add(btnOracleAmulet);
        btnOracleAmulet.addActionListener(e -> this.controller.buyItem(Items.ORACLEAMULET));
        showSelectButton(btnOracleAmulet);


        this.showPrice();
        this.showItemInformation();
        this.show();
    }
    private void showSelectButton(final JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                btn.setBackground(new Color(Color.TRANSLUCENT));
            }
            @Override
            public void mouseExited(final MouseEvent e) {
                btn.setBackground(COLOR_BACKGROUND);
            }
        });
    }
    /**
     * open in game menu panel and set button and label.
     */
    public void showInGameMenu() {
        this.frame.remove(shopPanel);
        this.frame.setContentPane(inGameMenuPanel);

        btnResumeMenu.setBounds((int) (screen.getWidth() / 4), 340, 300, 90);
        this.configureButton(btnResumeMenu);
        this.lblBackgroundMenu.add(btnResumeMenu);
        btnResumeMenu.addActionListener(e -> this.controller.resume());
        showSelectButton(btnResumeMenu);

        btnShopMenu.setBounds((int) (screen.getWidth() / 4), 433, 300, 90);
        this.configureButton(btnShopMenu);
        this.lblBackgroundMenu.add(btnShopMenu);
        btnShopMenu.addActionListener(e -> this.controller.openShop());
        showSelectButton(btnShopMenu);

        btnExitMenu.setBounds((int) (screen.getWidth() / 4), 523, 300, 90);
        this.configureButton(btnExitMenu);
        this.lblBackgroundMenu.add(btnExitMenu);
        btnExitMenu.addActionListener(e -> this.controller.exit());
        showSelectButton(btnExitMenu);
        if (start) {
            this.show();
        }
    }

    /**
     * @Override
     */
    @Override
    public void show() {
        this.frame.pack();
        if (!start) {
            this.frame.setLocation(screen.width / 2 - this.frame.getSize().width / 2, 
                                 screen.height / 2 - this.frame.getSize().height / 2);
            start = true;
        }
        this.frame.setVisible(true);
    }

    /**
     * 
     */
    @Override
    public void hide() {
        this.frame.setVisible(false);
    }

    private void configureButton(final JButton btn) {
        btn.setBackground(COLOR_BACKGROUND);
        btn.setBorder(new LineBorder(COLOR_BACKGROUND)); //Colore del bordo del bottone
        btn.setFocusPainted(false); //Disabilita il paint del focus sul testo del bottone
    }

    /**
     * create and set message label, for output information.
     * @param messageOutput : set message in JLabel
     */
    public void returnMessage(final String messageOutput) {
        msg.setBounds(150, 580, 420, 50);
        this.lblBackgroundShop.add(msg);
        msg.setFont(new Font("Algerian", Font.ITALIC, 20));
        msg.setText(messageOutput);
        msg.setVisible(true);
    }
    /**
     * clear message label.
     */
    public void removeMessage() {
        msg.setText("");
    }

    /**
     * set output price item.
     */
    public void setPriceItem(final Map<Items, Integer> map) {
        priceArthemideBow = map.get(Items.ARTHEMIDEBOW).toString();
        priceHermesBoots = map.get(Items.HERMESBOOTS).toString();
        priceZeusBolth = map.get(Items.ZEUSBOLT).toString();
        priceHelath = map.get(Items.HEALTH).toString();
        priceOracleAmulet = map.get(Items.ORACLEAMULET).toString();
    }

    private void showPrice() {
        final int sizeFont = 25;
        final int widthLabel = 25;
        final int heightLabel = 25;
        final JLabel labelPriceArthemideBow = new JLabel(priceArthemideBow);
        labelPriceArthemideBow.setBounds(90, 310, widthLabel, heightLabel);
        this.lblBackgroundShop.add(labelPriceArthemideBow);
        labelPriceArthemideBow.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));

        final JLabel labelPriceHermesBoots = new JLabel(priceHermesBoots);
        labelPriceHermesBoots.setBounds(250, 310, widthLabel, heightLabel);
        this.lblBackgroundShop.add(labelPriceHermesBoots);
        labelPriceHermesBoots.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));

        final JLabel labelPriceZeusBolt = new JLabel(priceZeusBolth);
        labelPriceZeusBolt.setBounds(390, 310, widthLabel, heightLabel);
        this.lblBackgroundShop.add(labelPriceZeusBolt);
        labelPriceZeusBolt.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));

        final JLabel labelPriceHealth = new JLabel(priceHelath);
        labelPriceHealth.setBounds(560, 310, widthLabel, heightLabel);
        this.lblBackgroundShop.add(labelPriceHealth);
        labelPriceHealth.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));

        final JLabel labelPriceOracleAmulet = new JLabel(priceOracleAmulet);
        labelPriceOracleAmulet.setBounds(710, 310, widthLabel, heightLabel);
        this.lblBackgroundShop.add(labelPriceOracleAmulet);
        labelPriceOracleAmulet.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));
    }
    private void showItemInformation() {
        final int sizeFont = 15;
        final int widthLabel = 140;
        final int heightLabel = 20;

        final JLabel labelInfoArthemideBow = new JLabel("+ damage");
        labelInfoArthemideBow.setBounds(45, 385, widthLabel, heightLabel);
        this.lblBackgroundShop.add(labelInfoArthemideBow);
        labelInfoArthemideBow.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));

        final JLabel labelInfoHermesBoots = new JLabel("+ speed");
        labelInfoHermesBoots.setBounds(205, 385, widthLabel, heightLabel);
        this.lblBackgroundShop.add(labelInfoHermesBoots);
        labelInfoHermesBoots.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));

        final JLabel labelInfoZeusBolt = new JLabel("+ bullet speed");
        labelInfoZeusBolt.setBounds(355, 385, widthLabel, heightLabel);
        this.lblBackgroundShop.add(labelInfoZeusBolt);
        labelInfoZeusBolt.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));

        final JLabel labelInfoHealth = new JLabel("+ health");
        labelInfoHealth.setBounds(520, 385, widthLabel, heightLabel);
        this.lblBackgroundShop.add(labelInfoHealth);
        labelInfoHealth.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));

        final JLabel labelInfoOracleAmulet = new JLabel("+ everything");
        labelInfoOracleAmulet.setBounds(665, 385, widthLabel, heightLabel);
        this.lblBackgroundShop.add(labelInfoOracleAmulet);
        labelInfoOracleAmulet.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));
    }

}
