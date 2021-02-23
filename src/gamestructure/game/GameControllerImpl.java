package gamestructure.game;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

import input.Command;
import input.CommandImpl;
import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import mvc.Model;

public class GameControllerImpl implements GameController {
    private static final long PERIOD = 1;
    private final GameView view;
    private final Model model;
    private final List<Integer> lastGameObjectsID = new LinkedList<>();
    private final Command command;

    public GameControllerImpl(final GameView view, final Model model) {
        this.view = view;
        this.model = model;
        this.command = new CommandImpl(this.model);
    }

    /**
     * 
     */
    @Override
    public void setup() {
        view.setController(this);
        view.show();
    }
    /**
     * @Override
     */
    public void mainLoop() {
        long lastTime = System.currentTimeMillis();
        while (true) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            processInput();
            updateGame(elapsed * 0.001);
            render();
            waitForNextFrame(current);
            lastTime = current;
        }
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
            this.getCommand().execute();
            this.getCommand().lock();
    }

    private void updateGame(final double elapsed) {
        this.model.update(elapsed);
        this.checkDeletedObject();
        this.checkNewGameObjects();
    }

    private void render() {
        this.updateSpritePositions();
        this.view.render();
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

    /*
     * 
     */
    @Override
    public Command getCommand() {
        return this.command;
    }


}
