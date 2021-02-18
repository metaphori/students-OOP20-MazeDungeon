package gamestructure.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import model.common.Point2D;
import model.common.Sprite;
import model.gameobject.GameObject;
import mvc.Model;

public class GameControllerImpl implements GameController {
    private static final long PERIOD = 20;
    private final GameView view;
    private final Model model;
    private List<Integer> currentgameObjectsID = new LinkedList<>();
    //private final Map<Integer, Rectangle> colliders = new HashMap<>();

    public GameControllerImpl(final GameView view, final Model model) {
        this.view = view;
        this.model = model;
    }

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
        // TODO Auto-generated method stub

    }

    private void updateGame(final double elapsed) {
        this.model.update(elapsed);
        this.checkNewGameObjects();
        this.updateSpritePositions();
    }

    private void render() {
        this.view.render();
    }
    /*
    public void addCollider(Sprite sprite) {
        colliders.put(id, rect);
    }*/

    /**
     * 
     */
    private void updateSpritePositions() {
        for (final Entry<Integer, Sprite> sprite : view.getSprites().entrySet()) {
            final Point2D position = model.getGameObjectPosition(sprite.getKey());
            sprite.getValue().setPosition(position);
        }
    }

    /**
     * 
     */
    private void checkNewGameObjects() {
        final List<Integer> gameObjectsID = model.getActualGameObjects().stream().map(obj -> obj.getID()).collect(Collectors.toList());
        for (final Integer id : gameObjectsID) {
            if (!currentgameObjectsID.contains(id)) {
                final GameObject newObject = model.getGameObject(id);
                view.addSprite(newObject.getID(), newObject.getGameObjectType(), newObject.getPosition());
            }
        }
        currentgameObjectsID.clear();
        currentgameObjectsID.addAll(gameObjectsID);
    }


}
