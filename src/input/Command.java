package input;

import java.awt.event.KeyEvent;

/**
 * 
 * An interface for modeling a Command that is a key input from keyboard.
 */

public interface Command {

    /**
     * 
     * @param key : the key pressed.
     * @param clicked : true if the key is clicked.
     */
    void setKey(KeyEvent key, boolean clicked);

    /**
     * execute the pressed keys.
     */
    void execute();

    /**
     * set all the command to false (not clicked).
     */
    void setAllInactive();

}
