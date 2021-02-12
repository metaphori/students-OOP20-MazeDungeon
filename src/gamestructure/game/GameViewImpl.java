package gamestructure.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameViewImpl implements GameView {

    private GameController controller;
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final JFrame frame;
    private final Color frameColor;
    private static double WIDTH_RATIO = 0.67; 
    private static double HEIGHT_RATIO = 0.74; 
    private static double ASPECT_RATIO = 1.6;
    private static final int PERIOD = 75;

    private Timer timer;

    public GameViewImpl() {
        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setTitle("MazeDungeon");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), (int) (screen.getHeight() * HEIGHT_RATIO)));
        this.frameColor = Color.BLUE;
        this.frame.setBackground(this.frameColor);
        final GamePanel gamePanel = new GamePanel();
        gamePanel.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), (int) (screen.getHeight() * HEIGHT_RATIO)));
        //gamePanel.setLayout(new GridBagLayout()); //TODO
        this.frame.add(gamePanel);
        timer = new Timer(PERIOD, gamePanel);
        timer.start();
    }

    @Override
    public void show() {
        //this.frame.pack();
        this.frame.setLocation(screen.width / 2 - this.frame.getSize().width / 2, 
                               screen.height / 2 - this.frame.getSize().height / 2);
        this.frame.setVisible(true);
    }

    @Override
    public void hide() {
        this.frame.setVisible(false);
    }

    private class GamePanel extends JPanel implements ActionListener{
        private static final long serialVersionUID = 1L;
        private Image room;
        private int coinX1 = 800;
        private int coinY1 = 300;
        private final List<Image> images = new LinkedList<>();
        private int index = 0;

        GamePanel() {
            ImageIcon ii = new ImageIcon("resources//images//Objects//Coin//coin1.png");
            images.add(ii.getImage());
            ii = new ImageIcon("resources//images//Objects//Coin//coin2.png");
            images.add(ii.getImage());
            ii = new ImageIcon("resources//images//Objects//Coin//coin3.png");
            images.add(ii.getImage());
            ii = new ImageIcon("resources//images//Objects//Coin//coin4.png");
            images.add(ii.getImage());
            ii = new ImageIcon("resources//images//Objects//Coin//coin5.png");
            images.add(ii.getImage());
            ii = new ImageIcon("resources//images//Objects//Coin//coin6.png");
            images.add(ii.getImage());
            ii = new ImageIcon("resources//images//Objects//Coin//coin7.png");
            images.add(ii.getImage());

            //System.out.println(ii.getIconWidth());

            ii = new ImageIcon("resources//images//Room//room.png");
            room = ii.getImage();
        }

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            g.drawImage(room, 0, 0, null);
            /*
            g.drawImage(this.coin1, coinX1, 100, null);
            g.drawImage(this.coin2, coinX2, 0, null);
            */

            g.drawImage(images.get(index), coinX1, coinY1, null);
            index++;
            if (index == images.size() - 1) {
                index = 0;
            }

            coinX1 += 0;
            coinY1 += 0;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            repaint();
        }
    }

    @Override
    public void setController(final GameController controller) {
       this.controller = controller;
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
    }

}


