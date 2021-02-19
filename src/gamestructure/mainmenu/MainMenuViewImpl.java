package gamestructure.mainmenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class MainMenuViewImpl implements MainMenuView {

    private final MainMenuController controller = new MainMenuControllerImpl(this);
    private final JFrame frame = new JFrame();
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final JLabel lblBackground = new JLabel(new ImageIcon("resources//images//MainMenu//MainMenu3.png"));
    private final JLabel lblTitle = new JLabel(new ImageIcon("resources//images//MainMenu//Title.png"));
    private final JButton btnNewGame = new JButton("", new ImageIcon("resources//images//MainMenu//NewGame.png")); 
    private final JButton btnExit = new JButton("", new ImageIcon("resources//images//MainMenu//Exit.png")); 
    private final JButton btnCredits = new JButton("", new ImageIcon("resources//images//MainMenu//Credits.png")); 
    private static double WIDTH_RATIO = 0.67; 
    private static double HEIGHT_RATIO = 0.736;

    public MainMenuViewImpl() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), 
                (int) (screen.getHeight() * HEIGHT_RATIO)));
        final JPanel mainPanel = new JPanel(new GridBagLayout());

        btnNewGame.setBounds(160, 340, 300, 45);
        this.configureButton(btnNewGame);

        btnNewGame.addActionListener(e -> {
            this.controller.newGame();
        });

        btnCredits.setBounds(160, 433, 300, 45);
        this.configureButton(btnCredits);

        btnExit.setBounds(160, 523, 300, 45);
        this.configureButton(btnExit);

        lblTitle.setLayout(null);
        lblBackground.setLayout(null);

        lblTitle.setBounds(50, 208, 500, 100);
        lblBackground.add(lblTitle);

        mainPanel.add(lblBackground);
        this.frame.setContentPane(mainPanel);
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
        btn.setBackground(Color.DARK_GRAY); //Colore del background del bottone
        btn.setBorder(new LineBorder(Color.BLACK)); //Colore del bordo del bottone
        btn.setFocusPainted(false); //Disabilita il paint del focus sul testo del bottone
        btn.setLayout(null);
        this.lblBackground.add(btn);
    }

}
