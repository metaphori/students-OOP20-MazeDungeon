package input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import gamestructure.game.GameController;
import gamestructure.ingamemenu.InGameMenuController;
import gamestructure.ingamemenu.InGameMenuControllerImpl;
import model.common.Vector2D;
import model.common.VectorDirection;
import model.gameobject.dynamicobject.character.Character;
import model.gameobject.dynamicobject.character.CharacterMovement;
import model.gameobject.dynamicobject.character.CharacterMovementImpl;
import mvc.Model;

public class CommandImpl implements Command {

    private final Model model;
    private final GameController gameController;
    private final Character character;
    private final CharacterMovement chMovement; 
    private boolean menuIsOpen;
    private Trio<Integer, Boolean, Optional<VectorDirection>> command;


    private final List<Trio<Integer, Boolean, Optional<VectorDirection>>> keysList = new ArrayList<>() 
    {{
       add(new Trio<>(KeyEvent.VK_UP, false, Optional.of(VectorDirection.UP)));
       add(new Trio<>(KeyEvent.VK_DOWN, false, Optional.of(VectorDirection.DOWN))); 
       add(new Trio<>(KeyEvent.VK_LEFT, false, Optional.of(VectorDirection.LEFT))); 
       add(new Trio<>(KeyEvent.VK_RIGHT, false, Optional.of(VectorDirection.RIGHT)));

       add(new Trio<>(KeyEvent.VK_W, false, Optional.of(VectorDirection.UP)));
       add(new Trio<>(KeyEvent.VK_S, false, Optional.of(VectorDirection.DOWN)));
       add(new Trio<>(KeyEvent.VK_D, false, Optional.of(VectorDirection.RIGHT)));
       add(new Trio<>(KeyEvent.VK_A, false, Optional.of(VectorDirection.LEFT)));
    }};



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
        int keyCode;
        for (final Trio<Integer, Boolean, Optional<VectorDirection>> trio : this.keysList) {
            if (trio.getY()) {
                keyCode = trio.getX();
                this.command = findObjectFromStream(keyCode).get();
                if (this.command != null) {
                    if (isArrow(command)) {
                       this.character.setShoot(true, this.command.getZ().get());
                    } else {
                        this.chMovement.move(this.command.getZ().get());
                    }
                }
            }
        }

        /**
         *@TODO movements
         * METTERE QUESTI METODI NEL MOVEMENT*/
       if (this.checkStopVertical()) {
            chMovement.stopVertical();
        }

        if (this.checkStopHorizontal()) {
            chMovement.stopHorizontal();
        }
     }



    private boolean isArrow(final Trio<Integer, Boolean, Optional<VectorDirection>> obj) {
        return obj.getX() == KeyEvent.VK_UP || obj.getX() == KeyEvent.VK_DOWN || obj.getX() == KeyEvent.VK_LEFT 
                || obj.getX() == KeyEvent.VK_RIGHT;
    }

    private Optional<Trio<Integer, Boolean, Optional<VectorDirection>>> findObjectFromStream(final int key) {
        final Optional<Trio<Integer, Boolean, Optional<VectorDirection>>> object = this.keysList.stream().filter(t -> t.getX() == key).findFirst();
        if (object.isPresent()) {
            return object;
        }
       return Optional.empty();
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
        final Optional<Trio<Integer, Boolean, Optional<VectorDirection>>> trio = this.keysList.stream().filter(t -> t.getX() == key.getKeyCode()).findFirst();
        if (trio.isPresent()) {
            trio.get().setY(b);
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
        if (this.command != null) {
            return !this.findObjectFromStream(KeyEvent.VK_S).get().getY() && !this.findObjectFromStream(KeyEvent.VK_W).get().getY();
        }
        return true;
    }

    /**
     * @return true if keys left(A) and right(D) are not pressed.
     */
    @Override
    public boolean checkStopHorizontal() {
        if (this.command != null) {
            return  !this.findObjectFromStream(KeyEvent.VK_D).get().getY() && !this.findObjectFromStream(KeyEvent.VK_A).get().getY();
        }
        return true;
    }

    /**
     * set the menuIsOpen to false.
     */
    @Override
    public void setMenuClosed() {
        this.menuIsOpen = false;
    }
}
