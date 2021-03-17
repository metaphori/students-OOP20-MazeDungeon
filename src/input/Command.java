package input;

import java.awt.event.KeyEvent;


public interface Command {

    void setKey(KeyEvent key, boolean clicked);

    void execute();

}
