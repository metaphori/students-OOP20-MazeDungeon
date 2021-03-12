package gamestructure.mainmenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
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
        private boolean lblCreditsIsVisible;

        private final Map<MainMenuComponent, Pair<JComponent, ResizableRectangle>> componentsMap = new HashMap<>() {{
          put(MainMenuComponent.BTN_NEW_GAME, new Pair<>(new JButton(), new ResizableRectangle(160, 340, 300, 45)));
          put(MainMenuComponent.BTN_CREDITS, new Pair<>(new JButton(), new ResizableRectangle(160, 430, 300, 45)));
          put(MainMenuComponent.BTN_EXIT, new Pair<>(new JButton(), new ResizableRectangle(160, 520, 300, 45)));
          put(MainMenuComponent.LBL_BACKGROUND, new Pair<>(new JLabel(), new ResizableRectangle(0, 0, 613, 727)));
          put(MainMenuComponent.LBL_CREDITS, new Pair<>(new JLabel(), new ResizableRectangle(140, 595, 335, 122)));
        }};

        MenuPanel() {
            this.componentsMap.entrySet().stream().forEach(e -> {
                e.getValue().getY().mul(windowUtilities.getScreenRatio());
                this.configureComponents(e);
                e.getValue().getX().setBounds(e.getValue().getY());
                this.add(e.getValue().getX(), JLayeredPane.PALETTE_LAYER);
            });

            
            this.componentsMap.entrySet().stream().filter(e -> e.getValue().getX() instanceof JButton).forEach(e -> {
                this.configureButtonGraphics((JButton) e.getValue().getX());
            });
        }

        private void configureComponents(final Entry<MainMenuComponent, Pair<JComponent, ResizableRectangle>> componentEntry) {
            switch (componentEntry.getKey()) {
            case BTN_CREDITS:
                final JButton btnCredits = (JButton) (componentEntry.getValue().getX());
                btnCredits.setIcon(windowUtilities.resizeImage(new ImageIcon(this.imagesPath + "CreditsButton.png"), componentEntry.getValue().getY()));
                btnCredits.addActionListener(e -> {
                    this.lblCreditsIsVisible = !this.lblCreditsIsVisible;
                    this.componentsMap.entrySet().stream()
                                                 .filter(p -> p.getKey().equals(MainMenuComponent.LBL_CREDITS))
                                                 .findAny().get().getValue().getX()
                                                 .setVisible(this.lblCreditsIsVisible);
                });
                break;
            case BTN_EXIT:
                final JButton btnExit = (JButton) (componentEntry.getValue().getX());
                btnExit.setIcon(windowUtilities.resizeImage(new ImageIcon(this.imagesPath + "Exit.png"), componentEntry.getValue().getY()));
                btnExit.addActionListener(e -> {
                    MainMenuViewImpl.this.hide();
                });
                break;
            case BTN_NEW_GAME:
                final JButton btnNewGame = (JButton) (componentEntry.getValue().getX());
                btnNewGame.setIcon(windowUtilities.resizeImage(new ImageIcon(this.imagesPath + "NewGame.png"), componentEntry.getValue().getY()));
                btnNewGame.addActionListener(e -> {
                    controller.newGame();
                });
                break;
            case LBL_BACKGROUND:
                final JLabel lblBackGround = (JLabel) (componentEntry.getValue().getX());
                lblBackGround.setIcon(windowUtilities.resizeImage(new ImageIcon(this.imagesPath + "MainMenuBackground.png"), componentEntry.getValue().getY()));
                break;
            default:
                final JLabel lblCredits = (JLabel) (componentEntry.getValue().getX());
                lblCredits.setVisible(false);
                lblCredits.setIcon(windowUtilities.resizeImage(new ImageIcon(this.imagesPath + "lblCredits.png"), componentEntry.getValue().getY()));
                break;
            }
        }

        private void configureButtonGraphics(final JButton btn) {
            btn.setBackground(Color.DARK_GRAY);
            btn.setBorder(new LineBorder(Color.BLACK));
            btn.setFocusPainted(false);
        }
    }
}
