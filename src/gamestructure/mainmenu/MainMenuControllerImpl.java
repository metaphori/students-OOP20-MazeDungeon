package gamestructure.mainmenu;

import gamestructure.game.GameController;
import gamestructure.game.GameControllerImpl;
import gamestructure.game.GameView;
import gamestructure.game.GameViewImpl;
import model.ModelImpl;
import mvc.Model;

public class MainMenuControllerImpl implements MainMenuController {

    private final MainMenuView view = new MainMenuViewImpl();

    public MainMenuControllerImpl() {
        this.view.setController(this);
        this.view.show();
    }

    /**
     * It start a new game using a new Thread.
     */
    @Override
    public void newGame() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final GameView view = new GameViewImpl();
                final Model model = new ModelImpl();
                final GameController controller = new GameControllerImpl(view, model);
                controller.setup();
                controller.mainLoop();
            }
        });
        thread.start();
        this.view.hide();
    }
}
