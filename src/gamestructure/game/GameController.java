package gamestructure.game;

import model.common.BoundingBox;

public interface GameController {
    void mainLoop();

    void setup();

    void notifyCommand(int keyCommand);

    void setBoundingBox(int id, BoundingBox boundingBox);
}
