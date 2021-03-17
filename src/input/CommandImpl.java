package input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import gamestructure.game.GameController;
import model.common.VectorDirection;
import model.gameobject.dynamicobject.character.Character;
import model.gameobject.dynamicobject.character.CharacterMovement;
import model.gameobject.dynamicobject.character.CharacterMovementImpl;
import mvc.Model;

public class CommandImpl implements Command {

    private final GameController gameController;
    private final Character character;
    private final CharacterMovement characterMovement; 
    private Trio<Integer, Boolean, Optional<VectorDirection>> command;
    private final List<Trio<Integer, Boolean, Optional<VectorDirection>>> keysList = new ArrayList<>();


    public CommandImpl(final Model model, final GameController controller) {
        this.gameController = controller;
        this.character = model.getRoomManager().getCharacter();
        this.characterMovement = new CharacterMovementImpl(character);
        this.initializeKeysList();
    }

    private void initializeKeysList() {
        this.keysList.add(new Trio<>(KeyEvent.VK_UP, false, Optional.of(VectorDirection.UP)));
        this.keysList.add(new Trio<>(KeyEvent.VK_DOWN, false, Optional.of(VectorDirection.DOWN))); 
        this.keysList.add(new Trio<>(KeyEvent.VK_LEFT, false, Optional.of(VectorDirection.LEFT))); 
        this.keysList.add(new Trio<>(KeyEvent.VK_RIGHT, false, Optional.of(VectorDirection.RIGHT)));

        this.keysList.add(new Trio<>(KeyEvent.VK_W, false, Optional.of(VectorDirection.UP)));
        this.keysList.add(new Trio<>(KeyEvent.VK_S, false, Optional.of(VectorDirection.DOWN)));
        this.keysList.add(new Trio<>(KeyEvent.VK_A, false, Optional.of(VectorDirection.LEFT)));
        this.keysList.add(new Trio<>(KeyEvent.VK_D, false, Optional.of(VectorDirection.RIGHT)));
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
                    if (isArrow(this.command)) {
                       this.character.setShoot(true, this.command.getZ().get());
                    } else {
                        this.characterMovement.move(this.command.getZ().get());
                    }
                }
            }
        }

        if (this.checkUpDownKeys()) {
            characterMovement.stopVertical();
        }

        if (this.checkRightLeftKeys()) {
            characterMovement.stopHorizontal();
        }
     }

    private boolean isArrow(final Trio<Integer, Boolean, Optional<VectorDirection>> trio) {
        return trio.getX() == KeyEvent.VK_UP || trio.getX() == KeyEvent.VK_DOWN || trio.getX() == KeyEvent.VK_LEFT 
                || trio.getX() == KeyEvent.VK_RIGHT;
    }

    private Optional<Trio<Integer, Boolean, Optional<VectorDirection>>> findObjectFromStream(final int key) {
        final Optional<Trio<Integer, Boolean, Optional<VectorDirection>>> object = this.keysList.stream()
                                                                                                .filter(t -> t.getX() == key)
                                                                                                .findFirst();
        if (object.isPresent()) {
            return object;
        }
       return Optional.empty();
    }

    /**
     * set a key when is clicked.
     */
    @Override
    public void setKey(final KeyEvent key, final boolean clicked) {
        if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.gameController.openInGameMenu();
            return;
        }
        final Optional<Trio<Integer, Boolean, Optional<VectorDirection>>> trio = this.keysList.stream()
                                                                                              .filter(t -> t.getX() == key.getKeyCode())
                                                                                              .findFirst();
        if (trio.isPresent()) {
            trio.get().setY(clicked);
        }
    }

    /**
     * @return true if keys up(W) and down(S) are not pressed.
     */
    private boolean checkUpDownKeys() {
        if (this.command != null) {
            return !this.findObjectFromStream(KeyEvent.VK_S).get().getY() && !this.findObjectFromStream(KeyEvent.VK_W).get().getY();
        }
        return false;
    }

    /**
     * @return true if keys left(A) and right(D) are not pressed.
     */
    private boolean checkRightLeftKeys() {
        if (this.command != null) {
            return  !this.findObjectFromStream(KeyEvent.VK_D).get().getY() && !this.findObjectFromStream(KeyEvent.VK_A).get().getY();
        }
        return false;
    }

    /**
     * 
     * @param predicate
     * @return the filtered stream by the predicate
     */
    private Optional<Trio<Integer, Boolean, Optional<VectorDirection>>> filtering(final Predicate predicate) {
        return this.keysList.stream().filter(predicate).findFirst();
    }
}
