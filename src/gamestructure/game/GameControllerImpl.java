package gamestructure.game;

import mvc.Model;

public class GameControllerImpl implements GameController {
    private static final long PERIOD = 20;
    private final GameView view;
    private final Model model;

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
        view.show();
        while (true) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            processInput();
            updateGame(elapsed);
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

    private void updateGame(final int elapsed) {
        // TODO Auto-generated method stub

    }

    private void render() {
        this.view.render();
    }
}
