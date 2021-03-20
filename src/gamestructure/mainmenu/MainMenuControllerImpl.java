package gamestructure.mainmenu;

import gamestructure.game.GameController;
import gamestructure.game.GameControllerImpl;
import model.Model;
import model.ModelImpl;

public class MainMenuControllerImpl implements MainMenuController {

    private MainMenuView view;

    /**
     * Set up the MainMenu Controller.
     *  @Override
     */
    public void setup() {
        this.view = new MainMenuViewImpl(this);
        this.view.show();
    }

    /**
     * It start a new game using a new Thread.
     * @Override
     */
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
