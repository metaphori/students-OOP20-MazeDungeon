package input;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

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
    private final GameController gameController;
    private final Character character;
    private final CharacterMovement chMovement;

    private final Map<Integer, Boolean> keysMap = new HashMap<>() {{
        put(KeyEvent.VK_UP, false);
        put(KeyEvent.VK_DOWN, false); 
        put(KeyEvent.VK_LEFT, false);
        put(KeyEvent.VK_RIGHT, false); 
        put(KeyEvent.VK_W, false);
        put(KeyEvent.VK_S, false);
        put(KeyEvent.VK_D, false);
        put(KeyEvent.VK_A, false);
        put(KeyEvent.VK_ESCAPE, false); 
     }};

    private final Map<Integer, Vector2D> keyDirectionMap = new HashMap<>() {{
        put(KeyEvent.VK_UP, new Vector2D(0, -1));
        put(KeyEvent.VK_DOWN, new Vector2D(0, 1));
        put(KeyEvent.VK_LEFT, new Vector2D(-1, 0));
        put(KeyEvent.VK_RIGHT, new Vector2D(1, 0));
    }};
    private boolean menuIsOpen;


    public CommandImpl(final Model model, final GameController gameController) {
        this.model = model;
        this.gameController = gameController; 
        this.character = model.getRoomManager().getCharacter();
        this.chMovement = new CharacterMovementImpl(character);
        this.menuIsOpen = false;
    }

    /**
     * execute the pressed keys.
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
     * @return true if keys up(W) and down(S) are not pressed.
     */
    @Override
    public boolean checkStopVertical() {
        return !this.keysMap.get(KeyEvent.VK_W) && !this.keysMap.get(KeyEvent.VK_S);
    }

    /**
     * @return true if keys left(A) and right(D) are not pressed.
     */
    @Override
    public boolean checkStopHorizontal() {
        return !this.keysMap.get(KeyEvent.VK_A) && !this.keysMap.get(KeyEvent.VK_D);
    }

    /**
     * set the menuIsOpen to false.
     */
    @Override
    public void setMenuClosed() {
        this.menuIsOpen = false;
    }
}
