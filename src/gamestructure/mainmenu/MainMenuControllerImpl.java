package gamestructure.mainmenu;

import gamestructure.game.GameController;
import gamestructure.game.GameControllerImpl;
import model.Model;
import model.ModelImpl;

/**
 * It contains the implementation of the methods of a generic Controller,
 * and the methods defined by MainMenuController's interface.
 *
 */
public class MainMenuControllerImpl implements MainMenuController {

    private final MainMenuView view;

    /**
     * Instantiate a new MainMenuController initializing also the corresponding MainMenuView.
     */
    public MainMenuControllerImpl() {
        this.view = new MainMenuViewImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup() {
        this.view.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newGame() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Model model = new ModelImpl();
                final GameController controller = new GameControllerImpl(model);
                controller.setup();
                controller.mainLoop();
            }
        });
        thread.start();
        this.view.hide();
    }
}
