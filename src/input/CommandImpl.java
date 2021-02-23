package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;

import gamestructure.game.GameController;
import gamestructure.ingamemenu.InGameMenuView;
import gamestructure.ingamemenu.InGameMenuViewImpl;
import gamestructure.mainmenu.MainMenuView;
import gamestructure.mainmenu.MainMenuViewImpl;
import model.common.Point2D;
import mvc.Model;

public class CommandImpl implements Command {

    private final Model model;
    private boolean[] keys;
    private int key;
    private final Set<Integer> permittedKeys;


    public CommandImpl(final Model model) {
        this.model = model;
        this.keys = new boolean[256];
        this.permittedKeys = new HashSet<>(Set.of(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE));
    }

    @Override
    public void execute(final int keyCommand) {
       
        if (keys[KeyEvent.VK_W]) {
            this.model.getRoomManager().getCharacter().moveUp();
        }
        if (keys[KeyEvent.VK_S]) {
            this.model.getRoomManager().getCharacter().moveDown();
        }
        if (keys[KeyEvent.VK_D]) {
            this.model.getRoomManager().getCharacter().moveRight();
        }
        if (keys[KeyEvent.VK_A]) {
            this.model.getRoomManager().getCharacter().moveLeft();
        }
        if (keys[KeyEvent.VK_SPACE]) {
            this.model.getRoomManager().getCharacter().shoot();
        }
        if (keys[KeyEvent.VK_ESCAPE]) {
            final InGameMenuView window = new InGameMenuViewImpl();
            window.show();
        }

        if (this.checkStopVertical()) {
            this.model.getRoomManager().getCharacter().stopVertical();
        }

        if (this.checkStopHorizontal()) {
            this.model.getRoomManager().getCharacter().stopHorizontal();
        }
     }

    /**
     * 
     */
    @Override
    public void setKey(final KeyEvent key, final boolean b) {
        this.keys[key.getKeyCode()] = b;
        this.key = key.getKeyCode();
    }

    /**
     * 
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * 
     */
    @Override
    public boolean checkStopVertical() {
        return !this.keys[KeyEvent.VK_W] && !this.keys[KeyEvent.VK_S];
    }

    /**
     * 
     */
    @Override
    public boolean checkStopHorizontal() {
        return !this.keys[KeyEvent.VK_A] && !this.keys[KeyEvent.VK_D];
    }

    /*
     * 
     */
    @Override
    public void lock() {
        this.key = 0;

    }

    @Override
    public Set<Integer> getPermittedKeys() {
        return this.permittedKeys;
    }


}
