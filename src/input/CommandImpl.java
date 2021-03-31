package input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import gamestructure.game.GameController;
import model.Model;
import model.common.VectorDirection;
import model.gameobject.dynamicobject.maincharacter.MainCharacter;
import model.gameobject.dynamicobject.maincharacter.MainCharacterMovement;
import model.gameobject.dynamicobject.maincharacter.MainCharacterMovementImpl;

/**
 * The implementation of command interface.
 * 
 * It contains methods to check what type of key is pressed and some methods to handle the trio list.
 * A trio in this class represents a key. Key, clicked, an optional for the direction of the movement.
 */
public class CommandImpl implements Command {

    private final GameController gameController;
    private final MainCharacter mainCharacter;
    private final MainCharacterMovement mainCharacterMovement; 
    private Optional<Trio<Integer, Boolean, Optional<VectorDirection>>> command;
    private final Set<Integer> letterKeys = new HashSet<>(Set.of(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D));
    private final Set<Integer> arrowKeys = new HashSet<>(Set.of(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT));
    private final List<Trio<Integer, Boolean, Optional<VectorDirection>>> keysList = new ArrayList<>();


    public CommandImpl(final Model model, final GameController controller) {
        this.gameController = controller;
        this.mainCharacter = model.getRoomManager().getMainCharacter();
        this.mainCharacterMovement = new MainCharacterMovementImpl(this.mainCharacter);
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
        this.keysList.add(new Trio<>(KeyEvent.VK_ESCAPE, false, Optional.empty()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.keysList.forEach(key -> {
            int keyCode;
            if (key.getY()) {
                keyCode = key.getX();
                this.command = findObjectFromStream(keyCode);
                if (!this.command.isEmpty()) {
                    if (this.isArrow(this.command)) {
                       this.mainCharacter.setShoot(true, this.command.get().getZ().get());
                    } else if (this.isLetter(this.command)) {
                        this.mainCharacterMovement.move(this.command.get().getZ().get());
                    } else {
                        this.gameController.openInGameMenu();
                    }
                }
            }
        });

        if (this.checkUpDownKeys()) {
            this.mainCharacterMovement.stopVertical();
        }

        if (this.checkRightLeftKeys()) {
            this.mainCharacterMovement.stopHorizontal();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKey(final KeyEvent key, final boolean clicked) {
        final Optional<Trio<Integer, Boolean, Optional<VectorDirection>>> trio = this.keysList.stream()
                                                                                              .filter(t -> t.getX() == key.getKeyCode())
                                                                                              .findFirst();
        if (trio.isPresent()) {
            trio.get().setY(clicked);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAllInactive() {
        this.keysList.stream().forEach(k -> k.setY(false));
    }

    private boolean isLetter(final Optional<Trio<Integer, Boolean, Optional<VectorDirection>>> command) {
        return letterKeys.contains(command.get().getX());
    }

    private boolean isArrow(final Optional<Trio<Integer, Boolean, Optional<VectorDirection>>> command) {
        return arrowKeys.contains(command.get().getX());
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
}
