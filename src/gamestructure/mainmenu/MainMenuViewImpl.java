package gamestructure.mainmenu;

import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
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

import viewutilities.Pair;
import viewutilities.ResizableRectangle;
import viewutilities.WindowUtilities;

public class MainMenuViewImpl implements MainMenuView {

    private static final int MENU_WIDTH = 630;
    private static final int MENU_HEIGHT = 765;
    private static final Color BACKGROUND_COLOR = new Color(11, 19, 30);
    private final WindowUtilities windowUtilities = new WindowUtilities();
    private final JFrame frame = new JFrame();

    private final MainMenuController controller;

    public MainMenuViewImpl(final MainMenuController controller) {
        this.controller = controller;
        this.frame.setTitle("MazeDungeon");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.getContentPane().setBackground(BACKGROUND_COLOR);

        final MenuPanel mainPanel = new MenuPanel();
        this.frame.add(mainPanel);
    }

    /**
     * @Override
     */
    public void show() {
        this.frame.setSize(new Dimension((int) (MENU_WIDTH * windowUtilities.getScreenRatio()), 
                            (int) (MENU_HEIGHT * windowUtilities.getScreenRatio())));
        this.frame.setLocation((int) (windowUtilities.getScreen().getWidth()) / 2 - this.frame.getSize().width / 2, 
                (int) (windowUtilities.getScreen().getHeight()) / 2 - this.frame.getSize().height / 2);
        this.frame.setVisible(true);
    }

    /**
     * @Override
     */
    public void hide() {
        this.frame.dispose();
    }

    private class MenuPanel extends JLayeredPane {
        private static final long serialVersionUID = 6848873930359078496L;

        private final ResizableRectangle newGameButtonPosition = new ResizableRectangle(160, 340, 300, 45);
        private final ResizableRectangle creditsButtonPosition = new ResizableRectangle(160, 430, 300, 45);
        private final ResizableRectangle exitButtonPosition = new ResizableRectangle(160, 520, 300, 45);
        private final ResizableRectangle lblBackgroundPosition = new ResizableRectangle(0, 0, 613, 727);
        private final ResizableRectangle lblCreditsPosition = new ResizableRectangle(130, 595, 335, 122);
        private boolean lblCreditsIsVisible;

        private final Map<MainMenuComponent, Pair<JComponent, ResizableRectangle>> componentsMap = new HashMap<>();

        MenuPanel() {
            this.initializeComponentsMap();
            this.componentsMap.entrySet().stream().forEach(e -> {
                e.getValue().getY().mul(windowUtilities.getScreenRatio());
                if (e.getValue().getX() instanceof JLabel) {
                    final JLabel lbl = (JLabel) e.getValue().getX();
                    final URL url = this.getClass().getResource(e.getKey().getPath());
                    lbl.setIcon(windowUtilities.resizeImage(new ImageIcon(url), e.getValue().getY()));
                }
                this.configureComponents(e);
                e.getValue().getX().setBounds(e.getValue().getY());
                this.add(e.getValue().getX(), e.getKey().equals(MainMenuComponent.LBL_BACKGROUND) ? JLayeredPane.DEFAULT_LAYER : JLayeredPane.PALETTE_LAYER);
            });

            this.componentsMap.entrySet().stream().filter(e -> e.getValue().getX() instanceof JButton).forEach(e -> {
                final JButton btn = (JButton) e.getValue().getX();
                final URL url = this.getClass().getResource(e.getKey().getPath());
                btn.setIcon(windowUtilities.resizeImage(new ImageIcon(url), e.getValue().getY()));
                this.configureButtonGraphics((JButton) e.getValue().getX());
            });
        }

        private void configureComponents(final Entry<MainMenuComponent, Pair<JComponent, ResizableRectangle>> componentEntry) {
            switch (componentEntry.getKey()) {
            case BTN_NEW_GAME:
                final JButton btnNewGame = (JButton) (componentEntry.getValue().getX());
                btnNewGame.addActionListener(e -> {
                    controller.newGame();
                });
                break;
            case BTN_CREDITS:
                final JButton btnCredits = (JButton) (componentEntry.getValue().getX());
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
                btnExit.addActionListener(e -> {
                    MainMenuViewImpl.this.hide();
                });
                break;
            default:
                break;
            }
        }

        private void initializeComponentsMap() {
            this.componentsMap.put(MainMenuComponent.BTN_NEW_GAME, new Pair<>(new JButton(), newGameButtonPosition));
            this.componentsMap.put(MainMenuComponent.BTN_CREDITS, new Pair<>(new JButton(), creditsButtonPosition));
            this.componentsMap.put(MainMenuComponent.BTN_EXIT, new Pair<>(new JButton(), exitButtonPosition));
            this.componentsMap.put(MainMenuComponent.LBL_BACKGROUND, new Pair<>(new JLabel(), lblBackgroundPosition));
            this.componentsMap.put(MainMenuComponent.LBL_CREDITS, new Pair<>(new JLabel(), lblCreditsPosition));
            this.componentsMap.entrySet().stream()
                                         .filter(e -> e.getKey().equals(MainMenuComponent.LBL_CREDITS))
                                         .map(e -> e.getValue().getX())
                                         .findAny().get().setVisible(false);
        }

        private void configureButtonGraphics(final JButton btn) {
            btn.setBackground(Color.DARK_GRAY);
            btn.setBorder(new LineBorder(Color.BLACK));
            btn.setFocusPainted(false);
        }
    }
}
