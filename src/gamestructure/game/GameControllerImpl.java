package gamestructure.game;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

import gamestructure.mainmenu.MainMenuController;
import gamestructure.mainmenu.MainMenuControllerImpl;
import gamestructure.mainmenu.MainMenuView;
import gamestructure.mainmenu.MainMenuViewImpl;
import input.Command;
import input.CommandImpl;
import model.common.BoundingBox;
import model.common.GameObjectType;
import model.gameobject.dinamicobject.character.Character;
import model.common.Point2D;
import model.gameobject.GameObject;
import mvc.Model;

public class GameControllerImpl implements GameController {
    private static final long PERIOD = 1;
    private final GameView view;
    private final Model model;
    private final List<Integer> lastGameObjectsID = new LinkedList<>();
    private final Command command;


    public GameControllerImpl(final Model model) {
        this.view = new GameViewImpl();
        this.model = model;
        this.command = new CommandImpl(this.model, this);
    }

    /**
     * 
     */
    @Override
    public void setup() {
        view.setController(this);
        view.show();
        view.initialize();
    }
    /**
     * @Override
     */
    public void mainLoop() {
        long lastTime = System.currentTimeMillis();
        while (!this.model.isGameOver()) {
            final long current = System.currentTimeMillis();
            /*if (this.command.isMenuOpen()) {
                waitForNextFrame(current);
                lastTime = current;
                continue;
            }*/
            final int elapsed = (int) (current - lastTime);
            processInput();
            updateGame(elapsed * 0.001);
            render();
            waitForNextFrame(current);
            lastTime = current;
        }
        
        /**TODO: in questa parte va gestita anche la vittoria, inoltre il codice qui sotto 
         * va messo in un'altra funzione private nel controller*/
        this.view.gameOver();
        
        try {
            Thread.sleep(2000);
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
        this.model.update(elapsed);
        this.checkDeletedObject();
        this.checkNewGameObjects();
    }

    private void render() {
        this.updateSpritePositions();
        this.view.updateHUD();
    } 
    /**
     * 
     */
    private void updateSpritePositions() {
        for (final Integer id : this.lastGameObjectsID) {
            this.view.setSpritePosition(id, model.getGameObjectPosition(id));
        }
    }

    private List<Integer> getActualObjectsID() {
        return this.model.getActualGameObjects().stream().filter(o -> o.getGameObjectType() != GameObjectType.INVISIBLE_OBJECT).map(obj -> obj.getID()).collect(Collectors.toList());
    }

    private void checkDeletedObject() {
        final List<Integer> gameObjectsID = this.getActualObjectsID();
        for (final Integer id : this.lastGameObjectsID) {
            if (!gameObjectsID.contains(id)) {
                this.view.removeSprite(id);
            }
        }
    }

    /**
     * 
     */
    private void checkNewGameObjects() {
        final List<Integer> gameObjectsID = this.getActualObjectsID();
        for (final Integer id : gameObjectsID) {
            if (!lastGameObjectsID.contains(id)) {
                final GameObject newObject = model.getGameObject(id);
                view.addSprite(newObject.getID(), newObject.getGameObjectType(), newObject.getPosition());
            }
        }
        lastGameObjectsID.clear();
        lastGameObjectsID.addAll(gameObjectsID);
    }

    /**
     * 
     */
    @Override
    public void setBoundingBox(final int id, final BoundingBox boundingBox) {
        this.model.getGameObject(id).setBoundingBox(boundingBox);
    }

    @Override 
    public Character getCharacter() {
        return this.model.getRoomManager().getCharacter();
    }

    /*
     * 
     */
    @Override
    public void notifyClosedInGameMenu() {
        this.command.setMenuClosed();
        this.view.renderItems(this.model.getShop().getCart());
        this.model.getShop().clearCart();
    }

    

    @Override
    public void openMainMenu() {
        final MainMenuController main = new MainMenuControllerImpl();
    }

    @Override
    public void pressKey(final KeyEvent key) {
        this.command.setKey(key, true);
    }

    @Override
    public void releaseKey(final KeyEvent key) {
        this.command.setKey(key, false);
    }
}
