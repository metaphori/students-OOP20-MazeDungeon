package gamestructure.ingamemenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class InGameMenuViewImpl implements InGameMenuView {
    private final JFrame frame = new JFrame();
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private final JLabel lblBackgroundMenu = new JLabel(new ImageIcon("resources\\images\\InGameMenu\\ingamemenu.png"));
    //private final JLabel lblTitle = new JLabel(new ImageIcon("resources//images//InGameMenu//logo.png"));
    private final JButton btnResumeMenu = new JButton("", new ImageIcon("resources//images//InGameMenu//resume.png")); 
    private final JButton btnExitMenu = new JButton("", new ImageIcon("resources//images//InGameMenu//exit.png")); 
    private final JButton btnShopMenu = new JButton("", new ImageIcon("resources//images//InGameMenu//shopBTN.png")); 
    
    //DIchiarazione componenti shop
    private final JLabel lblBackgroundShop = new JLabel(new ImageIcon("resources\\images\\InGameMenu\\shop.png"));
    private final JButton btnArthemideBowItem = new JButton("", new ImageIcon("resources//images//Item//arthemideBow.png")); 
    private final JButton btnHermesBootsItem = new JButton("", new ImageIcon("resources//images//Item//hermesBoots.png")); 
    private final JButton btnZeusBoltItem = new JButton("", new ImageIcon("resources//images//Item//zeusBolt.png")); 
    private final JButton btnHealthItem = new JButton("", new ImageIcon("resources//images//Item//health.png")); 

    
    private static double WIDTH_RATIO = 0.67; 
    private static double HEIGHT_RATIO = 0.736;
    private final JPanel inGameMenuPanel = new JPanel(new GridBagLayout());
    private final JPanel shopPanel = new JPanel(new GridBagLayout());
    
    public InGameMenuViewImpl() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), 
                (int) (screen.getHeight() * HEIGHT_RATIO)));

       
        btnResumeMenu.setBounds((int) (screen.getWidth()/4), 340, 300, 45);
        this.configureButton(btnResumeMenu);
        this.lblBackgroundMenu.add(btnResumeMenu);
        //this.inGameMenuPanel.add(btnResumeMenu);
        
        btnShopMenu.setBounds((int) (screen.getWidth()/4), 433, 300, 45);
        this.configureButton(btnShopMenu);
        this.lblBackgroundMenu.add(btnShopMenu);
        //this.inGameMenuPanel.add(btnShopMenu);
        
        btnExitMenu.setBounds((int) (screen.getWidth()/4), 523, 300, 45);
        this.configureButton(btnExitMenu);
        this.lblBackgroundMenu.add(btnExitMenu);
        //this.inGameMenuPanel.add(btnExitMenu);

        /*lblTitle.setLayout(null);
        lblBackground.setLayout(null);

        lblTitle.setBounds(50, 208, 500, 100);
        lblBackground.add(lblTitle);*/

        this.frame.setContentPane(inGameMenuPanel);
        inGameMenuPanel.add(lblBackgroundMenu);
        //// INIZIO Panel SHOP
        
        btnArthemideBowItem.setBounds(160, 360, 200, 200);
        this.configureButton(btnArthemideBowItem);
        this.lblBackgroundShop.add(btnArthemideBowItem);
        
        btnHermesBootsItem.setBounds(400, 360, 200, 200);
        this.configureButton(btnHermesBootsItem);
        this.lblBackgroundShop.add(btnHermesBootsItem);
        
        
        
        this.frame.setContentPane(shopPanel);
        shopPanel.add(lblBackgroundShop);
    }

    public void showShop() {
        disablePanel(inGameMenuPanel);
        enablePanel(shopPanel);

    }
    public void showInGameMenu() {
        disablePanel(shopPanel);
        enablePanel(inGameMenuPanel);
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
        btn.setBackground(new Color(255,0,0,128));
        btn.setBorder(new LineBorder(Color.BLACK)); //Colore del bordo del bottone
        btn.setFocusPainted(false); //Disabilita il paint del focus sul testo del bottone
        btn.setLayout(null);
        

    }

}
