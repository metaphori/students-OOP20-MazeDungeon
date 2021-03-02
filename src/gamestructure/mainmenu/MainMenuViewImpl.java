package gamestructure.mainmenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MainMenuViewImpl implements MainMenuView {

    private final String sep = File.separator;
    private final String imagesPath = "resources" + sep + "images" + sep + "MainMenu" + sep;
    private final JLabel lblBackground = new JLabel(new ImageIcon(this.imagesPath + "MainMenu3.png"));
    private final JLabel lblTitle = new JLabel(new ImageIcon(this.imagesPath + "Title.png"));
    private final JButton btnNewGame = new JButton("", new ImageIcon(this.imagesPath + "NewGame.png")); 
    private final JButton btnExit = new JButton("", new ImageIcon(this.imagesPath + "Exit.png")); 
    private final JButton btnCredits = new JButton("", new ImageIcon(this.imagesPath + "Credits.png")); 
    private final JFrame frame = new JFrame();
    private final JPanel mainPanel = new JPanel(new GridLayout());

    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double WIDTH_RATIO = 0.67; 
    private static final double HEIGHT_RATIO = 0.736;
    private final MainMenuController controller;

    public MainMenuViewImpl(final MainMenuController controller) {
        this.controller = controller;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), 
                (int) (screen.getHeight() * HEIGHT_RATIO)));

        btnNewGame.setBounds(160, 340, 300, 45);
        this.configureButton(btnNewGame);

        btnNewGame.addActionListener(e -> {
            this.controller.newGame();
        });

        btnCredits.setBounds(160, 433, 300, 45);
        this.configureButton(btnCredits);

        btnExit.setBounds(160, 523, 300, 45);
        this.configureButton(btnExit);

        btnExit.addActionListener(e -> {
            System.exit(0);
        });

        lblTitle.setBounds(50, 208, 500, 100);
        lblBackground.add(lblTitle);

        mainPanel.add(lblBackground, JLayeredPane.FRAME_CONTENT_LAYER);
        this.frame.setContentPane(mainPanel);
    }

    /**
     * @Override
     */
    public void show() {
        this.frame.pack();
        this.frame.setLocation(screen.width / 2 - this.frame.getSize().width / 2, 
                               screen.height / 2 - this.frame.getSize().height / 2);
        //MP3Player mp3Player = new MP3Player(new File("resources/sounds/undergroundMusic.mp3"));
        //mp3Player.play();
        this.frame.setVisible(true);
    }

    /**
     * @Override
     */
    public void hide() {
        this.frame.dispose();
    }

    private void configureButton(final JButton btn) {
        btn.setBackground(Color.DARK_GRAY);
        btn.setBorder(new LineBorder(Color.BLACK));
        btn.setFocusPainted(false);
        this.lblBackground.add(btn);
    }
}
