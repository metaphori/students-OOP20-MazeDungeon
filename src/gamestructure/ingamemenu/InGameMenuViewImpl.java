package gamestructure.ingamemenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class InGameMenuViewImpl implements InGameMenuView {
    private static int SIZE_IMAGE_ITEM = 100;
    private static double WIDTH_RATIO = 0.67; 
    private static double HEIGHT_RATIO = 0.736;
    private final JFrame frame = new JFrame();
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private final JLabel lblBackgroundMenu = new JLabel(new ImageIcon("resources\\images\\InGameMenu\\ingamemenu.png"));

    private final JButton btnResumeMenu = new JButton("", new ImageIcon("resources//images//InGameMenu//resume.png")); 
    private final JButton btnExitMenu = new JButton("", new ImageIcon("resources//images//InGameMenu//exit.png")); 
    private final JButton btnShopMenu = new JButton("", new ImageIcon("resources//images//InGameMenu//shopBTN.png")); 

    //Dchiarazione componenti shop

    //Image Item Scaled
    private final Image imageZeusBolt = new ImageIcon("resources//images//Item//zeusBolt.png").getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
    private final Image imageArthemideBow = new ImageIcon("resources//images//Item//arthemideBow.png").getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
    private final Image imageHermesBoots = new ImageIcon("resources//images//Item//hermesBoots.png").getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
    private final Image imageHealth = new ImageIcon("resources//images//Item//health.png").getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);

    private final JLabel lblBackgroundShop = new JLabel(new ImageIcon("resources\\images\\InGameMenu\\shop.png"));
    private JButton btnArthemideBowItem;
    private JButton btnHermesBootsItem;
    private JButton btnZeusBoltItem;
    private JButton btnHealthItem;

    private final JPanel inGameMenuPanel = new JPanel(new GridBagLayout());
    private final JPanel shopPanel = new JPanel(new GridBagLayout());
    
    private boolean start= false;

    public InGameMenuViewImpl() {

        
        
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), 
                (int) (screen.getHeight() * HEIGHT_RATIO)));

       
        /*
        btnResumeMenu.setBounds((int) (screen.getWidth()/4), 340, 300, 45);
        this.configureButton(btnResumeMenu);
        this.lblBackgroundMenu.add(btnResumeMenu);
        
        
        btnShopMenu.setBounds((int) (screen.getWidth()/4), 433, 300, 45);
        this.configureButton(btnShopMenu);
        this.lblBackgroundMenu.add(btnShopMenu);
        
        
        btnExitMenu.setBounds((int) (screen.getWidth()/4), 523, 300, 45);
        this.configureButton(btnExitMenu);
        this.lblBackgroundMenu.add(btnExitMenu);


        this.frame.setContentPane(inGameMenuPanel);
        inGameMenuPanel.add(lblBackgroundMenu);
        
        */
        //// INIZIO Panel SHOP
        /*
        btnArthemideBowItem = new JButton("", new ImageIcon(imageArthemideBow)); 
        btnHermesBootsItem = new JButton("", new ImageIcon(imageHermesBoots)); 
        btnZeusBoltItem = new JButton("", new ImageIcon(imageZeusBolt)); 
        btnHealthItem = new JButton("", new ImageIcon(imageHealth)); 


        this.frame.setContentPane(shopPanel);
        shopPanel.add(lblBackgroundShop);

        btnArthemideBowItem.setBounds(45, 180, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnArthemideBowItem);
        this.lblBackgroundShop.add(btnArthemideBowItem);

        btnHermesBootsItem.setBounds(208, 175, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnHermesBootsItem);
        this.lblBackgroundShop.add(btnHermesBootsItem);

        btnZeusBoltItem.setBounds(350, 190, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnZeusBoltItem);
        this.lblBackgroundShop.add(btnZeusBoltItem);

        btnHealthItem.setBounds(510, 180, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnHealthItem);
        this.lblBackgroundShop.add(btnHealthItem);
        */
        this.frame.pack();
        //this.frame.setLocation(screen.width / 2 - this.frame.getSize().width / 2, 
          //                     screen.height / 2 - this.frame.getSize().height / 2);
        this.frame.setVisible(true);
        
    }

    public void showShop() {
        
        this.frame.remove(inGameMenuPanel);
        
        btnArthemideBowItem = new JButton("", new ImageIcon(imageArthemideBow)); 
        btnHermesBootsItem = new JButton("", new ImageIcon(imageHermesBoots)); 
        btnZeusBoltItem = new JButton("", new ImageIcon(imageZeusBolt)); 
        btnHealthItem = new JButton("", new ImageIcon(imageHealth)); 


        this.frame.setContentPane(shopPanel);
        shopPanel.add(lblBackgroundShop);

        btnArthemideBowItem.setBounds(45, 180, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnArthemideBowItem);
        this.lblBackgroundShop.add(btnArthemideBowItem);

        btnHermesBootsItem.setBounds(208, 175, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnHermesBootsItem);
        this.lblBackgroundShop.add(btnHermesBootsItem);

        btnZeusBoltItem.setBounds(350, 190, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnZeusBoltItem);
        this.lblBackgroundShop.add(btnZeusBoltItem);

        btnHealthItem.setBounds(510, 180, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
        this.configureButton(btnHealthItem);
        this.lblBackgroundShop.add(btnHealthItem);
        
        this.frame.pack();
    }
    public void showInGameMenu() {
        
        this.frame.remove(shopPanel);
        
        
        btnResumeMenu.setBounds((int) (screen.getWidth()/4), 340, 300, 45);
        this.configureButton(btnResumeMenu);
        this.lblBackgroundMenu.add(btnResumeMenu);
        
        
        btnShopMenu.setBounds((int) (screen.getWidth()/4), 433, 300, 45);
        this.configureButton(btnShopMenu);
        this.lblBackgroundMenu.add(btnShopMenu);
        
        
        btnExitMenu.setBounds((int) (screen.getWidth()/4), 523, 300, 45);
        this.configureButton(btnExitMenu);
        this.lblBackgroundMenu.add(btnExitMenu);


        this.frame.setContentPane(inGameMenuPanel);
        inGameMenuPanel.add(lblBackgroundMenu);
        
        this.frame.pack();
        
        if(start == false) {
            this.frame.setLocation(screen.width / 2 - this.frame.getSize().width / 2, 
                                 screen.height / 2 - this.frame.getSize().height / 2);
            start=true;
        }
    }
    private void disablePanel(JPanel panel) {
        for (Component cp : panel.getComponents() ){
            cp.setEnabled(false);
        }
        panel.setVisible(false);
    }

    private void enablePanel(JPanel panel) {
        for (Component cp : panel.getComponents() ){
            cp.setEnabled(true);
        }
        panel.setVisible(true);
    }

    /**
     * @Override
     */
    public void show() {
        //this.frame.getContentPane().remove(shopPanel);
        //this.frame.getContentPane().add(inGameMenuPanel);
        //pane.getLayer(inGameMenuPanel);
        this.frame.pack();
        this.frame.setLocation(screen.width / 2 - this.frame.getSize().width / 2, 
                               screen.height / 2 - this.frame.getSize().height / 2);
        this.frame.setVisible(true);
        
      
      
    }

    /**
     * @Override
     */
    public void hide() {
        this.frame.setVisible(false);
    }

    private void configureButton(final JButton btn) {
        //btn.setBackground(Color.BLACK); //Colore del background del bottone
        btn.setBackground(new Color(11,23,30,255));
        
        btn.setBorder(new LineBorder(new Color(11,23,30,255))); //Colore del bordo del bottone
        btn.setFocusPainted(false); //Disabilita il paint del focus sul testo del bottone
        //btn.setLayout(null);
        

    }

}
