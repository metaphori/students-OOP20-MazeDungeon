package gamestructure.ingamemenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import gamestructure.WindowUtilities;
import gamestructure.ingamemenu.utilities.ImageLoader;
import gamestructure.ingamemenu.utilities.Images;
import gamestructure.ingamemenu.utilities.MenuShopComponents;
import gamestructure.Pair;
import model.common.ResizableRectangle;
import model.shop.Items;

public class InGameMenuViewImpl implements InGameMenuView  {
    private static final int SIZE_IMAGE_ITEM = 100;
    private JLabel lblBackGroundShop;
    private final WindowUtilities windowUtilities = new WindowUtilities();

    private static final String FONT_ALGERIAN = "Algerian";
    private String priceArthemideBow;
    private String priceHermesBoots;
    private String priceZeusBolth;
    private String priceHealth;
    private String priceOracleAmulet;
    private final JFrame frame = new JFrame();

    private final ImageLoader  imageIconSources = new ImageLoader();

    private final JLayeredPane inGameMenuPanel = new JLayeredPane();
    private final JLayeredPane shopPanel = new JLayeredPane();
    private final JLabel msg = new JLabel();

    private static final Color COLOR_BACKGROUND = new Color(11, 23, 30, 255);
    private boolean startMenu = true;
    private boolean startShop = true;

    private final InGameMenuController controller;
    private static final int WIDTHBTN = 300; 
    private static final int HEIGHTBTN = 90;
    private static final int POS_Y_BTN_ITEM = 180;

    private final Map<MenuShopComponents, Pair<JComponent, ResizableRectangle>> componentMapMenu = new HashMap<>() {{
        put(MenuShopComponents.BTN_RESUME, new Pair<>(new JButton(), new ResizableRectangle(320, 340, WIDTHBTN, HEIGHTBTN)));
        put(MenuShopComponents.BTN_EXIT, new Pair<>(new JButton(), new ResizableRectangle(320, 523, WIDTHBTN, HEIGHTBTN)));
        put(MenuShopComponents.BTN_SHOP, new Pair<>(new JButton(), new ResizableRectangle(320, 433, WIDTHBTN, HEIGHTBTN)));
        put(MenuShopComponents.LBL_BACKGROUND_MENU, new Pair<>( new JLabel(), new ResizableRectangle(0, 0, 1280, 800)));
    }};
    private static final int WIDTH_LABEL = 25;
    private static final int HEIGHT_LABEL = 25;
    private static final int POS_Y_LBL_PRICE = 310;

    private final Map<MenuShopComponents, Pair<JComponent, ResizableRectangle>> componentMapShop = new HashMap<>() {{
        put(MenuShopComponents.BTN_ARTHEMIDE_BOW, new Pair<>( new JButton(), new ResizableRectangle(45, POS_Y_BTN_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM)));
        put(MenuShopComponents.BTN_HERMES_BOOTS, new Pair<>(new JButton(), new ResizableRectangle(208, POS_Y_BTN_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM)));
        put(MenuShopComponents.BTN_ZEUS_BOLT, new Pair<>(new JButton(), new ResizableRectangle(350, POS_Y_BTN_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM)));
        put(MenuShopComponents.BTN_HEALTH, new Pair<>(new JButton(), new ResizableRectangle(510, POS_Y_BTN_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM)));
        put(MenuShopComponents.BTN_ORACLE_AMULET, new Pair<>(new JButton(), new ResizableRectangle(680, POS_Y_BTN_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM)));
        put(MenuShopComponents.BTN_BACK, new Pair<>(new JButton(), new ResizableRectangle(320, 533, 300, 90)));
        put(MenuShopComponents.LBL_BACKGROUND_SHOP, new Pair<>(new JLabel(), new ResizableRectangle(0, 0, 1280, 800)));
        put(MenuShopComponents.LBL_PRICE_AB, new Pair<>(new JLabel(), new ResizableRectangle(90, POS_Y_LBL_PRICE, WIDTH_LABEL, HEIGHT_LABEL)));
        put(MenuShopComponents.LBL_PRICE_H, new Pair<>(new JLabel(), new ResizableRectangle(560, POS_Y_LBL_PRICE, WIDTH_LABEL, HEIGHT_LABEL)));
        put(MenuShopComponents.LBL_PRICE_HB, new Pair<>(new JLabel(), new ResizableRectangle(250, POS_Y_LBL_PRICE, WIDTH_LABEL, HEIGHT_LABEL)));
        put(MenuShopComponents.LBL_PRICE_OA, new Pair<>(new JLabel(), new ResizableRectangle(710, POS_Y_LBL_PRICE, WIDTH_LABEL, HEIGHT_LABEL)));
        put(MenuShopComponents.LBL_PRICE_ZB, new Pair<>(new JLabel(), new ResizableRectangle(390, POS_Y_LBL_PRICE, WIDTH_LABEL, HEIGHT_LABEL)));
        put(MenuShopComponents.LBL_MSG, new Pair<>(new JLabel(), new ResizableRectangle(390, POS_Y_LBL_PRICE, WIDTH_LABEL, HEIGHT_LABEL)));
    }};
    public InGameMenuViewImpl(final InGameMenuController controller) {
        this.controller = controller;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setTitle("MazeDungeon");

    }
    /**
     * show shop panel and set button and label.
     */
    public void showShop() {
        this.frame.remove(inGameMenuPanel);

        if (startShop) {
            this.componentMapShop.entrySet().stream().forEach(e -> {
                e.getValue().getY().mul(windowUtilities.getScreenRatio());
                e.getValue().getX().setBounds(e.getValue().getY());
                this.configureShopComponent(e);
                if (e.getValue().getX() instanceof JButton) {
                    this.configureButton((JButton) e.getValue().getX());
                    this.shopPanel.add(e.getValue().getX(), JLayeredPane.PALETTE_LAYER);
                } else if (e.getKey().equals(MenuShopComponents.LBL_BACKGROUND_SHOP)) {
                    this.shopPanel.add(e.getValue().getX(), JLayeredPane.DEFAULT_LAYER);
                } else {
                    this.shopPanel.add(e.getValue().getX(), JLayeredPane.PALETTE_LAYER);
                }
            });
            startShop = false;
        }
        this.frame.setContentPane(shopPanel);
        this.show();
    }
    /**
     * open in game menu panel and set button and label.
     */
    public void showInGameMenu() {
        this.frame.remove(shopPanel);
        this.frame.setContentPane(inGameMenuPanel);
        if (startMenu) {
            this.componentMapMenu.entrySet().stream().forEach(e -> {
                e.getValue().getY().mul(windowUtilities.getScreenRatio());
                e.getValue().getX().setBounds(e.getValue().getY());
                this.configureMenuComponent(e);
                if (e.getValue().getX() instanceof JButton) {
                    this.configureButton((JButton) e.getValue().getX());
                    this.inGameMenuPanel.add(e.getValue().getX(), JLayeredPane.PALETTE_LAYER);
                } else {
                    this.inGameMenuPanel.add(e.getValue().getX(), JLayeredPane.DEFAULT_LAYER);
                }

            });
            startMenu = false;
        }
        this.show();
    }
    /**
     * 
     */
    @Override
    public void show() {
        this.frame.setLocation(windowUtilities.getScreen().width / 2 - this.frame.getSize().width / 2,
                windowUtilities.getScreen().height / 2 - this.frame.getSize().height / 2);
        this.frame.setVisible(true);
        this.frame.setSize(new Dimension((int) (WindowUtilities.NATIVE_WIDTH * WindowUtilities.WIDTH_RATIO * windowUtilities.getScreenRatio()) + this.frame.getInsets().left  + this.frame.getInsets().right,
                (int) (WindowUtilities.NATIVE_HEIGHT * WindowUtilities.HEIGHT_RATIO * windowUtilities.getScreenRatio()) + this.frame.getInsets().top + this.frame.getInsets().bottom));
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
        btn.setBorder(new LineBorder(COLOR_BACKGROUND));
        btn.setFocusPainted(false);
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
     * create and set message label, for output information.
     * @param messageOutput : set message in JLabel
     */
    public void returnMessage(final String messageOutput) {
        final int sizeFont = 20;
        msg.setBounds(150, 580, 420, 50);
        this.lblBackGroundShop.add(msg);
        msg.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));
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
     * @param map : for set Item price in view
     */
    public void setPriceItem(final Map<Items, Integer> map) {
        priceArthemideBow = map.get(Items.ARTHEMIDEBOW).toString();
        priceHermesBoots = map.get(Items.HERMESBOOTS).toString();
        priceZeusBolth = map.get(Items.ZEUSBOLT).toString();
        priceHealth = map.get(Items.HEALTH).toString();
        priceOracleAmulet = map.get(Items.ORACLEAMULET).toString();
    }
    private void configureMenuComponent(final Entry<MenuShopComponents, Pair<JComponent, ResizableRectangle>> entry) {
        switch (entry.getKey()) {
        case BTN_EXIT:
            final JButton btnExitMenu = (JButton) entry.getValue().getX();
            btnExitMenu.addActionListener(e -> this.controller.exit());
            btnExitMenu.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNEXIT), entry.getValue().getY()));
            break;
        case BTN_RESUME:
            final JButton btnResume = (JButton) entry.getValue().getX();
            btnResume.addActionListener(e -> this.controller.resume());
            btnResume.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNRESUME), entry.getValue().getY()));
            break;
        case BTN_SHOP:
            final JButton btnShop = (JButton) entry.getValue().getX();
            btnShop.addActionListener(e -> this.controller.openShop());
            btnShop.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNSHOP), entry.getValue().getY()));
            break;
        case LBL_BACKGROUND_MENU:
            final JLabel lblBackGround = (JLabel) entry.getValue().getX();
            lblBackGround.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BACKGROUNDMENU), entry.getValue().getY()));
            break;
        default:
            break;
        }
    }
    private void configureShopComponent(final Entry<MenuShopComponents, Pair<JComponent, ResizableRectangle>> entry) {
        final int sizeFont = 25;
        switch (entry.getKey()) {
        case BTN_ARTHEMIDE_BOW:
            final JButton btnArthemideBow = (JButton) entry.getValue().getX();
            btnArthemideBow.addActionListener(e -> this.controller.buyItem(Items.ARTHEMIDEBOW));
            btnArthemideBow.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNARTHEMIDEBOW), entry.getValue().getY()));
            break;
        case BTN_BACK:
            final JButton btnReturn = (JButton) entry.getValue().getX();
            btnReturn.addActionListener(e -> this.controller.openInGameMenu());
            btnReturn.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNRETURNMENU), entry.getValue().getY()));
            break;
        case BTN_HEALTH:
            final JButton btnHealth = (JButton) entry.getValue().getX();
            btnHealth.addActionListener(e -> this.controller.buyItem(Items.HEALTH));
            btnHealth.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNHEALTH), entry.getValue().getY()));
            break;
        case BTN_HERMES_BOOTS:
            final JButton btnHermesBoots = (JButton) entry.getValue().getX();
            btnHermesBoots.addActionListener(e -> this.controller.buyItem(Items.HERMESBOOTS));
            btnHermesBoots.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNHERMESBOOTS), entry.getValue().getY()));
            break;
        case BTN_ORACLE_AMULET:
            final JButton btnOracleAmulet = (JButton) entry.getValue().getX();
            btnOracleAmulet.addActionListener(e -> this.controller.buyItem(Items.ORACLEAMULET));
            btnOracleAmulet.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNORACLEAMULET), entry.getValue().getY()));
            break;
        case BTN_ZEUS_BOLT:
            final JButton btnZeusBolt = (JButton) entry.getValue().getX();
            btnZeusBolt.addActionListener(e -> this.controller.buyItem(Items.ZEUSBOLT));
            btnZeusBolt.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNZEUSBOLT), entry.getValue().getY()));
            break;
        case LBL_BACKGROUND_SHOP:
            lblBackGroundShop = (JLabel) entry.getValue().getX();
            lblBackGroundShop.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BACKGROUNDSHOP), entry.getValue().getY()));
            break;
        case LBL_PRICE_AB:
            final JLabel lblPriceArthemideBow = (JLabel) entry.getValue().getX();
            lblPriceArthemideBow.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));
            lblPriceArthemideBow.setText(priceArthemideBow);
            break;
        case LBL_PRICE_H:
            final JLabel lblPriceHealth = (JLabel) entry.getValue().getX();
            lblPriceHealth.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));
            lblPriceHealth.setText(priceHealth);
            break;
        case LBL_PRICE_HB:
            final JLabel lblPriceHermesBoots = (JLabel) entry.getValue().getX();
            lblPriceHermesBoots.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));
            lblPriceHermesBoots.setText(priceHermesBoots);
            break;
        case LBL_PRICE_OA:
            final JLabel lblPriceOracleAmulet = (JLabel) entry.getValue().getX();
            lblPriceOracleAmulet.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));
            lblPriceOracleAmulet.setText(priceOracleAmulet);
            break;
        case LBL_PRICE_ZB:
            final JLabel lblPriceZeusBolt = (JLabel) entry.getValue().getX();
            lblPriceZeusBolt.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, sizeFont));
            lblPriceZeusBolt.setText(priceZeusBolth);
            break;
        default:
            break;
        }
    }
    /////POSSIBILE TOGLIERE QUESTO METODO
   /* private void showItemInformation() {
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
    }*/
}
