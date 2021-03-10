package input;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;


import gamestructure.game.GameController;
import gamestructure.ingamemenu.InGameMenuController;
import gamestructure.ingamemenu.InGameMenuControllerImpl;
import model.common.Vector2D;
import model.gameobject.dynamicobject.character.Character;
import model.gameobject.dynamicobject.character.CharacterMovement;
import model.gameobject.dynamicobject.character.CharacterMovementImpl;
import mvc.Model;

public class CommandImpl implements Command {

    private final Model model;
    private Map<Integer, Boolean> keysMap;
    private final Map<Integer, Vector2D> keyDirectionMap;
    private boolean menuIsOpen;
    private GameController gameController;
    final Character character;
    final CharacterMovement chMovement;


    public CommandImpl(final Model model, final GameController gameController) {
        this.model = model;
        this.gameController = gameController; 
        this.character = model.getRoomManager().getCharacter();
        this.chMovement = new CharacterMovementImpl(character);
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

        if (this.keysMap.get(KeyEvent.VK_W)) {
            chMovement.moveUp();
        }
        if (this.keysMap.get(KeyEvent.VK_S)) {
            chMovement.moveDown();
        }
        if (this.keysMap.get(KeyEvent.VK_D)) {
            chMovement.moveRight();
        }
        if (this.keysMap.get(KeyEvent.VK_A)) {
            chMovement.moveLeft();
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

        if (this.checkStopVertical()) {
            chMovement.stopVertical();
        }

        if (this.checkStopHorizontal()) {
            chMovement.stopHorizontal();
        }
     }

    /**
     * 
     */
    @Override
    public void setKey(final KeyEvent key, final boolean b) {
        if (key.getKeyCode() == KeyEvent.VK_ESCAPE && this.canOpenInGameMenu()) {
            final InGameMenuController menuController = new InGameMenuControllerImpl(this.gameController, this.model);
            menuController.setup();
            this.menuIsOpen = true;
            return;
        }

        if (this.keysMap.keySet().contains(key.getKeyCode())) {
             this.keysMap.put(key.getKeyCode(), b);
        }
    }

    private boolean canOpenInGameMenu() {
        return !this.menuIsOpen && this.model.getRoomManager().getCurrentRoom().isDoorOpen() 
                                && !this.model.getRoomManager().getCharacter().isDead();
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

    @Override
    public void setMenuClosed() {
        this.menuIsOpen = false;
    }
}
