package input;

import java.util.List;
import java.util.Optional;

import model.common.VectorDirection;

/**
 * 
 * An interface for modeling a Command that is a key input from keyboard.
 */

public interface Command {

    /**
     * 
     * @param key : the keyCode of key pressed.
     * @param clicked : true if the key is clicked.
     */
    void setKey(int key, boolean clicked);

    /**
     * execute the pressed keys.
     */
    void execute();

    /**
     * set all the command to false (not clicked).
     */
    void setAllInactive();

    /**
     * 
     * @return the list of actual clicked keys.
     */
    List<Trio<Integer, Boolean, Optional<VectorDirection>>> getKeysList();

}
