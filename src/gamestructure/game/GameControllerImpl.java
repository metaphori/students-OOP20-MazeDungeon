package gamestructure.game;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import animations.State;
import gamestructure.ingamemenu.InGameMenuController;
import gamestructure.ingamemenu.InGameMenuControllerImpl;
import gamestructure.mainmenu.MainMenuController;
import gamestructure.mainmenu.MainMenuControllerImpl;
import input.Command;
import input.CommandImpl;
import model.Model;
import model.common.BoundingBox;
import model.common.GameObjectType;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.enemy.AbstractEnemy;

/**
 * A class that implements all the method that define a controller as the GameController.
 * It contains the main part of the application, the game loop.
 *
 */
public class GameControllerImpl implements GameController {
    private static final long PERIOD = 1;
    private static final double MILLISECOND_TO_SECOND = 0.001;
    private static final int END_GAME_WAITING = 2000;
    private final GameView view;
    private final Model model;
    private final List<Integer> lastGameObjectsID = new LinkedList<>();
    private final Command command;
    private boolean inGameMenuOpen;
    private final InGameMenuController inGameMenuController;
    private boolean active = true;

    /**
     * Build a new GameController.
     * @param model : an instance of the model
     */
    public GameControllerImpl(final Model model) {
        this.view = new GameViewImpl();
        this.model = model;
        this.command = new CommandImpl(this.model, this);
        this.inGameMenuController = new InGameMenuControllerImpl(this, model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup() {
        view.show();
        view.setController(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mainLoop() {
        long lastTime = System.currentTimeMillis();
        while (!this.model.isGameOver() && !this.model.isWon()) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            if (inGameMenuOpen || !this.active) {
                waitForNextFrame(current);
                lastTime = current;
                continue;
            }
            processInput();
            updateGame(elapsed * MILLISECOND_TO_SECOND);
            render();
            waitForNextFrame(current);
            lastTime = current;
        }

        if (this.model.isGameOver()) {
            this.view.gameOver();
        } else {
            this.view.isWon();
        }
        try {
            Thread.sleep(END_GAME_WAITING);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.view.hide();
        this.openMainMenu();
    }

    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processInput() {
        this.command.execute();
    }

    private void updateGame(final double elapsed) {
        this.model.updateGameWorld(elapsed);
        this.checkDeletedObject();
        this.checkNewGameObjects();
    }

    private void render() {
        this.checkDeletedObject();
        this.updateAnimations();
        this.view.updateHUD();
    } 

    private void updateAnimations() {
        for (final Integer id : this.lastGameObjectsID) {
            this.view.updateAnimation(id, model.getGameObjectPosition(id), this.getStateFromId(id));
        }
    }

    private List<Integer> getActualObjectsID() {
        return this.model.getActualGameObjects().stream()
                                                .filter(o -> o.getGameObjectType() != GameObjectType.INVISIBLE_OBJECT)
                                                .map(obj -> obj.getID())
                                                .collect(Collectors.toList());
    }

    private void checkDeletedObject() {
        final List<Integer> gameObjectsID = this.getActualObjectsID();
        for (final Integer id : this.lastGameObjectsID) {
            if (!gameObjectsID.contains(id)) {
                this.view.removeAnimation(id);
            }
        }
    }

    private void checkNewGameObjects() {
        final List<Integer> gameObjectsID = this.getActualObjectsID();
        for (final Integer id : gameObjectsID) {
            if (!lastGameObjectsID.contains(id)) {
                final GameObject newObject = model.getGameObject(id);
                view.addAnimation(newObject.getID(), newObject.getGameObjectType(), newObject.getPosition());
            }
        }
        lastGameObjectsID.clear();
        lastGameObjectsID.addAll(gameObjectsID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBoundingBox(final int id, final BoundingBox boundingBox) {
        this.model.getGameObject(id).setBoundingBox(boundingBox);
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public double getCharacterLife() {
        return this.model.getRoomManager().getCharacter().getLife();
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public int getCharacterMoney() {
        return this.model.getRoomManager().getCharacter().getMoney();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyClosedInGameMenu() {
        this.view.renderItems(this.model.getShop().getCart());
        this.model.getShop().clearCart();
        this.inGameMenuOpen = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openInGameMenu() {
        if (this.inGameMenuController.openInGameMenu()) {
            this.inGameMenuOpen = true;
        }
    }

    private void openMainMenu() {
        final MainMenuController main = new MainMenuControllerImpl();
        main.setup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pressKey(final KeyEvent key) {
        this.command.setKey(key, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void releaseKey(final KeyEvent key) {
        this.command.setKey(key, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVisitedRoom() {
        return this.model.getRoomManager().getVisitedRooms();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getBossLife() {
        final Optional<Integer> bossID = this.model.getRoomManager().getCurrentRoom().getBossID();
        if (bossID.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(((AbstractEnemy) this.model.getGameObject(bossID.get())).getLife());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State getStateFromId(final int id) {
        if (!this.getActualObjectsID().contains(id)) {
            return State.IDLE;
        }
        return this.model.getGameObject(id).getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActive(final boolean active) {
        this.active = active;
        if (!active) {
            this.command.setAllInactive();
        }
    }
}
