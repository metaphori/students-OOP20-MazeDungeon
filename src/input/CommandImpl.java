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
import java.util.concurrent.ConcurrentHashMap;

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
    private Map<Integer, Boolean> keysMap;
    private int key;
    private final Set<Integer> permittedKeys;
    private final Map<Integer, Vector2D> keyDirectionMap;
    private boolean menuIsOpen;
    private GameController gameController;


    public CommandImpl(final Model model, final GameController gameController) {
        this.model = model;  
        this.gameController = gameController; 
        this.menuIsOpen = false;
        this.keysMap = new ConcurrentHashMap<>(Map.of(KeyEvent.VK_UP, false,
                                            KeyEvent.VK_DOWN, false, 
                                            KeyEvent.VK_LEFT, false,
                                            KeyEvent.VK_RIGHT, false, 
                                            KeyEvent.VK_W, false, 
                                            KeyEvent.VK_S, false,
                                            KeyEvent.VK_D, false,
                                            KeyEvent.VK_A, false,
                                            KeyEvent.VK_ESCAPE, false));

        this.permittedKeys = new HashSet<>(Set.of(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A,
                                                    KeyEvent.VK_ESCAPE, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, 
                                                        KeyEvent.VK_LEFT));

        this.keyDirectionMap = new HashMap<>(Map.of(KeyEvent.VK_UP, new Vector2D(0, -1),
                                                    KeyEvent.VK_DOWN, new Vector2D(0, 1), 
                                                    KeyEvent.VK_LEFT, new Vector2D(-1, 0),
                                                    KeyEvent.VK_RIGHT, new Vector2D(1, 0)));
    }

    /*
     * 
     */
    @Override
    public void execute() {

        final Character character = this.model.getRoomManager().getCharacter();
        if (this.keysMap.get(KeyEvent.VK_W)) {
            character.moveUp();
        }
        if (this.keysMap.get(KeyEvent.VK_S)) {
            character.moveDown();
        }
        if (this.keysMap.get(KeyEvent.VK_D)) {
            character.moveRight();
        }
        if (this.keysMap.get(KeyEvent.VK_A)) {
            character.moveLeft();
        }

        if (this.keysMap.get(KeyEvent.VK_UP)) {
            character.setShoot(true, this.keyDirectionMap.get(KeyEvent.VK_UP));
        }
        if (this.keysMap.get(KeyEvent.VK_DOWN)) {
            character.setShoot(true, this.keyDirectionMap.get(KeyEvent.VK_DOWN));
        }
        if (this.keysMap.get(KeyEvent.VK_LEFT)) {
            character.setShoot(true, this.keyDirectionMap.get(KeyEvent.VK_LEFT));
        }
        if (this.keysMap.get(KeyEvent.VK_RIGHT)) {
            character.setShoot(true, this.keyDirectionMap.get(KeyEvent.VK_RIGHT));
        }

        /*if (this.keysMap.get(KeyEvent.VK_ESCAPE) && !this.menuIsOpen) {
            final InGameMenuController menuController = new InGameMenuControllerImpl(this.gameController, this.model);
            menuController.setup();
            this.menuIsOpen = true;
            return;
        }*/


        if (this.checkStopVertical()) {
            character.stopVertical();
        }

        if (this.checkStopHorizontal()) {
            character.stopHorizontal();
        }
     }

    /**
     * 
     */
    @Override
    public void setKey(final KeyEvent key, final boolean b) {
        if (key.getKeyCode() == KeyEvent.VK_ESCAPE && !this.menuIsOpen) {
            final InGameMenuController menuController = new InGameMenuControllerImpl(this.gameController, this.model);
            menuController.setup();
            this.menuIsOpen = true;
            return;
        }
        if (this.permittedKeys.contains(key.getKeyCode())) {
             this.keysMap.put(key.getKeyCode(), b);
        } else {
            System.out.println("Tasto disabilitato");
        }
    }

    /**
     * 
     */
    @Override
    public boolean checkStopVertical() {
        return !this.keysMap.get(KeyEvent.VK_W) && !this.keysMap.get(KeyEvent.VK_S);
    }

    /**
     * 
     */
    @Override
    public boolean checkStopHorizontal() {
        return !this.keysMap.get(KeyEvent.VK_A) && !this.keysMap.get(KeyEvent.VK_D);
    }

    /*
     * 
     */

    @Override
    public Set<Integer> getPermittedKeys() {
        return this.permittedKeys;
    }

    @Override
    public void setMenuClosed() {
        this.menuIsOpen = false;
    }

    @Override
    public boolean isMenuOpen() {
       return this.menuIsOpen;
    }



}
