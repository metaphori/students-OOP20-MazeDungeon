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

    private final JFrame frame = new JFrame();
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private static double WIDTH_RATIO = 0.67; 
    private static double HEIGHT_RATIO = 0.736;

    public MainMenuViewImpl() {
        final JLabel lblBackground = new JLabel(new ImageIcon("resources//images//MainMenu//MainMenu3.png"));
        final JButton btnNewGame = new JButton("NEW GAME"); //MΛZΣ DUNGΣӨП
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), 
                (int) (screen.getHeight() * HEIGHT_RATIO)));
        final JPanel mainPanel = new JPanel(new GridBagLayout());
        btnNewGame.setBounds(160, 350, 300, 30);
        btnNewGame.setFont(new Font("MS Gothic", Font.BOLD, 30));
        btnNewGame.setBackground(Color.DARK_GRAY); //Colore del background del bottone
        btnNewGame.setForeground(new Color(23, 11, 0)); //Colore del testo
        btnNewGame.setBorder(new LineBorder(Color.BLACK)); //Colore del bordo del bottone
        btnNewGame.setFocusPainted(false); //Disabilita il paint del focus sul testo del bottone
        btnNewGame.setLayout(null);
        lblBackground.setLayout(null);
        lblBackground.add(btnNewGame);
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

}
