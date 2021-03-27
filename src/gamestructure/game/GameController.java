package gamestructure.game;

import java.awt.event.KeyEvent;
import java.util.Optional;

import animations.State;
import gamestructure.Controller;
import model.common.BoundingBox;

public interface GameController extends Controller {
    void mainLoop();

    void setBoundingBox(int id, BoundingBox boundingBox);

    void pressKey(KeyEvent key);

    void releaseKey(KeyEvent key);

    void notifyClosedInGameMenu();

    double getCharacterLife();

    int getCharacterMoney();

    int getVisitedRoom();

    int getTotalRooms();

    Optional<Double> getBossLife();

    void openInGameMenu();

    State getStateFromId(int id);

    void setActive(boolean active);

    void setInactive();
}
