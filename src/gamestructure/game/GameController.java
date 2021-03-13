package gamestructure.game;

import java.awt.event.KeyEvent;
import java.util.Optional;

import input.Trio;
import model.common.BoundingBox;
import model.common.VectorDirection;
import mvc.Controller;

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
}
