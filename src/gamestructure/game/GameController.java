package gamestructure.game;

import java.awt.event.KeyEvent;
import java.util.Optional;

import animations.State;
import gamestructure.Controller;
import model.common.BoundingBox;
/**
 * This interface represent the GameController, that is the most important controller of MazeDungeon.
 * It extends the generic features of a generic Controller.
 *      It contains all the needed methods for the coordination by GameView and Model.
 */
public interface GameController extends Controller {
    /**
     * The main loop of the game.
     */
    void mainLoop();

    /**
     * @param id : the id of the GameObject 
     * @param boundingBox : the BoundingBox to set at the GameObject
     */
    void setBoundingBox(int id, BoundingBox boundingBox);

    /**
     * @param key : the key pressed
     */
    void pressKey(KeyEvent key);

    /**
     * @param key : the key released
     */
    void releaseKey(KeyEvent key);

    /**
     * notify that the InGameMenu is closed.
     */
    void notifyClosedInGameMenu();

    /**
     * @return the main character current life.
     */
    double getCharacterLife();

    /**
     * @return the main character current money.
     */
    int getCharacterMoney();

    /**
     * @return the number of visited rooms.
     */
    int getVisitedRoom();

    /**
     * @return the Optional of the boss life.
     */
    Optional<Double> getBossLife();

    /**
     * The method for open the InGame menu.
     */
    void openInGameMenu();

    /**
     * @param id : the id of the GameObject
     * @return the state of the GameObject
     */
    State getStateFromId(int id);

    /**
     * Set if the controller is active.
     * @param active : the state to be set.
     */
    void setActive(boolean active);
}
