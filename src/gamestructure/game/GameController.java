package gamestructure.game;

import java.awt.event.KeyEvent;
import java.util.Optional;

import input.Command;
import model.common.BoundingBox;
import model.gameobject.dinamicobject.character.Character;;

public interface GameController {
    void mainLoop();

    void setup();

    void setBoundingBox(int id, BoundingBox boundingBox);

    void pressKey(KeyEvent key);

    void releaseKey(KeyEvent key);

    void notifyClosedInGameMenu();

    Optional<Character> getCharacter();


    void openMainMenu();
}
