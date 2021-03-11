package gamestructure.mainmenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import gamestructure.PathGetter;
import gamestructure.WindowUtilities;
import model.common.ResizableRectangle;

public class MainMenuViewImpl implements MainMenuView {

    private static final int MENU_WIDTH = 613;
    private static final int MENU_HEIGHT = 727;
    private final WindowUtilities windowUtilities = new WindowUtilities();
    private final PathGetter pathGetter = new PathGetter();
    private final JFrame frame = new JFrame();

    private final MainMenuController controller;

    public MainMenuViewImpl(final MainMenuController controller) {
        this.controller = controller;
        this.frame.setTitle("MazeDungeon");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);

        final MenuPanel mainPanel = new MenuPanel();
        this.frame.add(mainPanel);
    }

    /**
     * @Override
     */
    public void show() {
        this.frame.setVisible(true);
        this.frame.setSize(new Dimension((int) (MENU_WIDTH * windowUtilities.getScreenRatio() + this.frame.getInsets().right + this.frame.getInsets().left), 
                            (int) (MENU_HEIGHT * windowUtilities.getScreenRatio() + this.frame.getInsets().top + this.frame.getInsets().bottom)));
        this.frame.setLocation((int) (windowUtilities.getScreen().getWidth()) / 2 - this.frame.getSize().width / 2, 
                (int) (windowUtilities.getScreen().getHeight()) / 2 - this.frame.getSize().height / 2);
    }

    /**
     * @Override
     */
    public void hide() {
        this.frame.dispose();
    }

    private class MenuPanel extends JLayeredPane {
        private static final long serialVersionUID = 6848873930359078496L;

        private final String imagesPath = pathGetter.getPortablePath("resources/images/MainMenu/");

        private final JButton btnNewGame; 
        private final JButton btnExit; 
        private final JButton btnCredits; 
        private final JLabel lblBackground;
        private final JLabel lblCredits;

        MenuPanel() {
            final ResizableRectangle backgroundPosition = new ResizableRectangle(0, 0, 613, 727);
            final ResizableRectangle newGamePosition = new ResizableRectangle(160, 340, 300, 45);
            final ResizableRectangle creditsPosition = new ResizableRectangle(160, 430, 300, 45);
            final ResizableRectangle exitPosition = new ResizableRectangle(160, 520, 300, 45);
            final ResizableRectangle lblCreditsPosition = new ResizableRectangle(140, 595, 335, 122);

            newGamePosition.mul(windowUtilities.getScreenRatio());
            creditsPosition.mul(windowUtilities.getScreenRatio());
            exitPosition.mul(windowUtilities.getScreenRatio());
            backgroundPosition.mul(windowUtilities.getScreenRatio());
            lblCreditsPosition.mul(windowUtilities.getScreenRatio());

            this.btnNewGame = new JButton("", this.resizeImage(new ImageIcon(this.imagesPath + "NewGame.png"), newGamePosition));
            this.btnCredits = new JButton("", this.resizeImage(new ImageIcon(this.imagesPath + "CreditsButton.png"), creditsPosition));
            this.btnExit = new JButton("", this.resizeImage(new ImageIcon(this.imagesPath + "Exit.png"), exitPosition));
            this.lblBackground = new JLabel(this.resizeImage(new ImageIcon(this.imagesPath + "MainMenuBackground.png"), backgroundPosition));
            this.lblCredits = new JLabel(this.resizeImage(new ImageIcon(this.imagesPath + "lblCredits.png"), lblCreditsPosition));

            btnNewGame.setBounds(newGamePosition);
            btnCredits.setBounds(creditsPosition);
            btnExit.setBounds(exitPosition);
            lblBackground.setBounds(backgroundPosition);
            lblCredits.setBounds(lblCreditsPosition);

            this.addAllButtonsEvents();

            this.lblCredits.setVisible(false);

            this.add(lblBackground, JLayeredPane.DEFAULT_LAYER);
            this.add(btnNewGame, JLayeredPane.PALETTE_LAYER);
            this.add(btnCredits, JLayeredPane.PALETTE_LAYER);
            this.add(btnExit, JLayeredPane.PALETTE_LAYER);
            this.add(lblCredits, JLayeredPane.PALETTE_LAYER);
        }

        private ImageIcon resizeImage(final ImageIcon startImgIcon, final ResizableRectangle rectangle) {
            final Image img = startImgIcon.getImage().getScaledInstance((int) rectangle.getWidth(), (int) rectangle.getHeight(), Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }

        private void addAllButtonsEvents() {
            this.configureButton(btnNewGame);

            btnNewGame.addActionListener(e -> {
                controller.newGame();
            });

            this.configureButton(btnCredits);

            btnCredits.addActionListener(e -> {
                this.lblCredits.setVisible(!this.lblCredits.isVisible());
            });

            this.configureButton(btnExit);

            btnExit.addActionListener(e -> {
                MainMenuViewImpl.this.hide();
            });
        }

        private void configureButton(final JButton btn) {
            btn.setBackground(Color.DARK_GRAY);
            btn.setBorder(new LineBorder(Color.BLACK));
            btn.setFocusPainted(false);
        }
    }
}
