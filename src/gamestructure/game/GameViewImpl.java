package gamestructure.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameViewImpl implements GameView {

    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final JFrame frame;
    private static double WIDTH_RATIO = 0.67; 
    private static double HEIGHT_RATIO = 0.74; 
    private static double ASPECT_RATIO = 1.6;
    private Timer timer;

    public GameViewImpl() {
        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setTitle("MazeDungeon");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.frame.setSize(new Dimension((int) (screen.getWidth() * WIDTH_RATIO), (int) (screen.getHeight() * HEIGHT_RATIO)));

        final GamePanel gamePanel = new GamePanel();
        //gamePanel.setLayout(new GridBagLayout()); TODO
        this.frame.add(gamePanel);
        timer = new Timer(25, gamePanel);
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
        private Image coin1;
        private Image coin2;
        private Image room;
        public int coinX1 = 100;
        public int coinX2 = 1000;

        GamePanel() {
            ImageIcon ii = new ImageIcon("resources//images//Objects//Coin//coin5.png");
            ImageIcon ii1 = new ImageIcon("resources//images//Objects//Coin//coin3.png");
            coin1 = ii.getImage();
            coin2 = ii1.getImage();
            ii = new ImageIcon("resources//images//Room//room.png");
            room = ii.getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(room, 0, 0, null);
            g.drawImage(this.coin1, coinX1, 100, null);
            g.drawImage(this.coin2, coinX2, 0, null);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            coinX1+=1;
            coinX2-=1;

            repaint();
        }

    }

}


