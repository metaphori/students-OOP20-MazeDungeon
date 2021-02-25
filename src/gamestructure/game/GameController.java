package gamestructure.game;

import java.awt.event.KeyEvent;

import input.Command;
import model.common.BoundingBox;

public interface GameController {
    void mainLoop();

    void setup();

    void setBoundingBox(int id, BoundingBox boundingBox);

    Command getCommand();

    void pressKey(KeyEvent key);

    void releaseKey(KeyEvent key);

    void notifyClosedMenu();
}
