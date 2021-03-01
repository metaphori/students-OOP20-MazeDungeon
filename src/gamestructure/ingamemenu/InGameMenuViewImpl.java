package gamestructure.ingamemenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import gamestructure.ingamemenu.utilities.ImageLoader;
import gamestructure.ingamemenu.utilities.Images;
import model.shop.Items;

public class InGameMenuViewImpl implements InGameMenuView  {
    
    private final JLayeredPane backGroundPanel = new JLayeredPane();
    
    private static final int SIZE_IMAGE_ITEM = 100;
    private static final double WIDTH_RATIO = 0.67; 
    private static final double HEIGHT_RATIO = 0.736;
    private String priceArthemideBow = "2";
    private String priceHermesBoots = "2";
    private String priceZeusBolth = "2";
    private String priceHelath = "2";
    private String priceOracleAmulet = "2";
    private final JFrame frame = new JFrame();
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private final ImageLoader  imageIconSources = new ImageLoader();

    private final JLabel lblBackgroundMenu = new JLabel(imageIconSources.getImage(Images.BACKGROUNDMENU));
    private final JButton btnResumeMenu = new JButton("", imageIconSources.getImage(Images.BTNRESUME));
    private final JButton btnExitMenu = new JButton("", imageIconSources.getImage(Images.BTNEXIT)); 
    private final JButton btnShopMenu = new JButton("", imageIconSources.getImage(Images.BTNSHOP)); 

    private final JButton btnArthemideBowItem = new JButton("", imageIconSources.getImage(Images.BTNARTHEMIDEBOW)); 
    private final JButton btnHermesBootsItem = new JButton("", imageIconSources.getImage(Images.BTNHERMESBOOTS)); 
    private final JButton btnZeusBoltItem = new JButton("", imageIconSources.getImage(Images.BTNZEUSBOLT)); 
    private final JButton btnHealthItem = new JButton("", imageIconSources.getImage(Images.BTNHEALTH)); 
    private final JButton btnOracleAmulet = new JButton("", imageIconSources.getImage(Images.BTNORACLEAMULET)); 

    private final JButton btnBackToMenu = new JButton("", imageIconSources.getImage(Images.BTNRETURNMENU)); 

    private final JLabel lblBackgroundShop = new JLabel(imageIconSources.getImage(Images.BACKGROUNDSHOP));

    private final JPanel inGameMenuPanel = new JPanel(new GridBagLayout());
    private final JPanel shopPanel = new JPanel(new GridBagLayout());
    private final JLabel msg = new JLabel();
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
        showInGameMenu();
    }
    /**
     * 
     */
    public void showShop() {
        this.frame.remove(inGameMenuPanel);
        this.frame.setContentPane(shopPanel);

        btnBackToMenu.setBounds((int) (screen.getWidth()/4), (int) (screen.getHeight()/1.5), 300, 90);
        this.configureButton(btnBackToMenu);
        this.lblBackgroundShop.add(btnBackToMenu);
        btnBackToMenu.addActionListener(e -> {
            this.controller.openInGameMenu();
        });

        btnArthemideBowItem.setBounds(45, 180, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnArthemideBowItem);
        this.lblBackgroundShop.add(btnArthemideBowItem);
        btnArthemideBowItem.addActionListener(e -> {
            this.controller.buyItem(Items.ARTHEMIDEBOW);
        });


        btnHermesBootsItem.setBounds(208, 175, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnHermesBootsItem);
        this.lblBackgroundShop.add(btnHermesBootsItem);
        btnHermesBootsItem.addActionListener(e -> {
            this.controller.buyItem(Items.HERMESBOOTS);
        });


        btnZeusBoltItem.setBounds(350, 190, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnZeusBoltItem);
        this.lblBackgroundShop.add(btnZeusBoltItem);
        btnZeusBoltItem.addActionListener(e -> {
            this.controller.buyItem(Items.ZEUSBOLT);
        });

        btnHealthItem.setBounds(510, 180, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnHealthItem);
        this.lblBackgroundShop.add(btnHealthItem);
        btnHealthItem.addActionListener(e -> {
            this.controller.buyItem(Items.HEALTH);
        });
        btnOracleAmulet.setBounds(680, 180, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnOracleAmulet);
        this.lblBackgroundShop.add(btnOracleAmulet);
        btnOracleAmulet.addActionListener(e -> {
            this.controller.buyItem(Items.ORACLEAMULET);
        });

        final JLabel labelPriceArthemideBow = new JLabel(priceArthemideBow);
        labelPriceArthemideBow.setBounds(90, 310, 25, 25);
        this.lblBackgroundShop.add(labelPriceArthemideBow);
        //priceArthemideBow.setText(PRICE_ARTHEMIDEBOW);
        labelPriceArthemideBow.setVisible(true);
        //priceArthemideBow.setForeground(Color.black);
        labelPriceArthemideBow.setFont(new Font("", Font.PLAIN, 25));

        final JLabel labelPriceHermesBoots = new JLabel(priceHermesBoots);
        labelPriceHermesBoots.setBounds(250, 310, 25, 25);
        this.lblBackgroundShop.add(labelPriceHermesBoots);
        labelPriceHermesBoots.setVisible(true);
        labelPriceHermesBoots.setFont(new Font("", Font.PLAIN, 25));

        final JLabel labelPriceZeusBolt = new JLabel(priceZeusBolth);
        labelPriceZeusBolt.setBounds(390, 310, 25, 25);
        this.lblBackgroundShop.add(labelPriceZeusBolt);
        labelPriceZeusBolt.setVisible(true);
        labelPriceZeusBolt.setFont(new Font("", Font.PLAIN, 25));

        final JLabel labelPriceHealth = new JLabel(priceHelath);
        labelPriceHealth.setBounds(560, 310, 25, 25);
        this.lblBackgroundShop.add(labelPriceHealth);
        labelPriceHealth.setVisible(true);
        labelPriceHealth.setFont(new Font("", Font.PLAIN, 25));

        final JLabel labelPriceOracleAmulet = new JLabel(priceOracleAmulet);
        labelPriceOracleAmulet.setBounds(710, 310, 25, 25);
        this.lblBackgroundShop.add(labelPriceOracleAmulet);
        labelPriceOracleAmulet.setVisible(true);
        labelPriceOracleAmulet.setFont(new Font("", Font.PLAIN, 25));

       this.show();
    }
    /**
     * 
     */
    public void showInGameMenu() {

        this.frame.remove(shopPanel);

        this.frame.setContentPane(inGameMenuPanel);

        btnResumeMenu.setBounds((int) (screen.getWidth()/4), 340, 300, 90);
        this.configureButton(btnResumeMenu);
        this.lblBackgroundMenu.add(btnResumeMenu);
        btnResumeMenu.addActionListener(e -> {
           // System.out.println("Premuto");
            this.controller.resume();
        });

        btnShopMenu.setBounds((int) (screen.getWidth()/4), 433, 300, 90);
        this.configureButton(btnShopMenu);
        this.lblBackgroundMenu.add(btnShopMenu);
        btnShopMenu.addActionListener(e -> {
            this.controller.openShop();
            //System.out.println("Count of listeners: " + ((JButton) e.getSource()).getActionListeners().length);
            //System.out.println("priva");
        });

        btnExitMenu.setBounds((int) (screen.getWidth()/4), 523, 300, 90);
        this.configureButton(btnExitMenu);
        this.lblBackgroundMenu.add(btnExitMenu);
        btnExitMenu.addActionListener(e -> {
            this.controller.exit();
        });

        if (start) {
            this.show();
        }
    }

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

    @Override
    public void hide() {
        this.frame.setVisible(false);
    }

    private void configureButton(final JButton btn) {

        btn.setBackground(new Color(11,23,30,255));
        btn.setBorder(new LineBorder(new Color(11,23,30,255))); //Colore del bordo del bottone
        btn.setFocusPainted(false); //Disabilita il paint del focus sul testo del bottone


    }

    /**
     * 
     */
    public void returnMessage(final String messageOuput) {

        msg.setBounds(250, 580, 350, 50);
        this.lblBackgroundShop.add(msg);
        msg.setText(messageOuput);
        msg.setVisible(true);
    }
    /**
     * 
     */
    public void removeMessage() {
        msg.setText("");
    }
    public void setPriceItem(Map<Items, Integer> map) {
        priceArthemideBow = map.get(Items.ARTHEMIDEBOW).toString();
        priceHermesBoots = map.get(Items.HERMESBOOTS).toString();
        priceZeusBolth = map.get(Items.ZEUSBOLT).toString();
        priceHelath = map.get(Items.HEALTH).toString();
        priceOracleAmulet = map.get(Items.ORACLEAMULET).toString();
    }

}
