package gamestructure.game;

import input.Command;

public interface GameController {
    void mainLoop();
    void setup();
    void notifyCommand(int keyCommand);
}
