package gamestructure.game;

import java.awt.event.KeyEvent;
import java.util.Optional;

import input.Command;
import model.common.BoundingBox;
import model.gameobject.dinamicobject.character.Character;
import mvc.Controller;;

public interface GameController extends Controller {
    void mainLoop();

    void setBoundingBox(int id, BoundingBox boundingBox);

    void pressKey(KeyEvent key);

    void releaseKey(KeyEvent key);

    void notifyClosedInGameMenu();

    Character getCharacter();

    void openMainMenu();
}
