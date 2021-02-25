package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.swing.SwingUtilities;

import gamestructure.game.GameController;
import gamestructure.ingamemenu.InGameMenuController;
import gamestructure.ingamemenu.InGameMenuControllerImpl;
import gamestructure.ingamemenu.InGameMenuView;
import gamestructure.ingamemenu.InGameMenuViewImpl;
import gamestructure.mainmenu.MainMenuView;
import gamestructure.mainmenu.MainMenuViewImpl;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.character.Character;
import mvc.Model;

public class CommandImpl implements Command {

    private final Model model;
    private boolean[] keys;
    private int key;
    private final Set<Integer> permittedKeys;
    private final Map<Integer, Vector2D> keyDirectionMap;
    private boolean menuIsOpen;
    private GameController gameController;


    public CommandImpl(final Model model, final GameController gameController) {
        this.model = model;
        this.keys = new boolean[256];
        this.menuIsOpen = false;
        this.gameController = gameController;
        this.permittedKeys = new HashSet<>(Set.of(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A,
                                                    KeyEvent.VK_ESCAPE, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT));

        this.keyDirectionMap = new HashMap<>(Map.of(KeyEvent.VK_UP, new Vector2D(0, -1),
                                                    KeyEvent.VK_DOWN, new Vector2D(0, 1), 
                                                    KeyEvent.VK_LEFT, new Vector2D(-1, 0),
                                                    KeyEvent.VK_RIGHT, new Vector2D(1, 0)));
    }

    @Override
    public void execute() {

        final Optional<Character> character = this.model.getRoomManager().getCurrentRoom().getCharacter();
        if (character.isEmpty()) {
            return;
        }
        if (keys[KeyEvent.VK_W]) {
            character.get().moveUp();
        }
        if (keys[KeyEvent.VK_S]) {
            character.get().moveDown();
        }
        if (keys[KeyEvent.VK_D]) {
            character.get().moveRight();
        }
        if (keys[KeyEvent.VK_A]) {
            character.get().moveLeft();
        }
        if (keys[KeyEvent.VK_UP]) {
            character.get().setShoot(true, this.keyDirectionMap.get(KeyEvent.VK_UP));
        }
        if (keys[KeyEvent.VK_DOWN]) {
            character.get().setShoot(true, this.keyDirectionMap.get(KeyEvent.VK_DOWN));
        }
        if (keys[KeyEvent.VK_LEFT]) {
            character.get().setShoot(true, this.keyDirectionMap.get(KeyEvent.VK_LEFT));
        }
        if (keys[KeyEvent.VK_RIGHT]) {
            character.get().setShoot(true, this.keyDirectionMap.get(KeyEvent.VK_RIGHT));
        }
        if (keys[KeyEvent.VK_ESCAPE]) {
            //System.out.println("APRO MENU IN GAME");
          /*  final Thread thread = new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    final InGameMenuView window = new InGameMenuViewImpl();
                    window.show();
            });
            thread.start();*/
            if (!this.menuIsOpen) {
                final InGameMenuController menuController = new InGameMenuControllerImpl(this.gameController);
                this.menuIsOpen = true;
            }

            /*try {
                while (!chiuso) {
                    Thread.currentThread().wait();
                }

            } catch (InterruptedException | InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
        }
        if (this.checkStopVertical()) {
            character.get().stopVertical();
        }

        if (this.checkStopHorizontal()) {
            character.get().stopHorizontal();
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

    @Override
    public void setMenuClosed() {
        this.menuIsOpen = false;
    }



}
