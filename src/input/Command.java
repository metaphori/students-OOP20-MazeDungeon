package input;

import java.awt.event.KeyEvent;


public interface Command {

    void setKey(KeyEvent key, boolean b);

    void execute();

    boolean checkUpDownKeys();

    boolean checkRightLeftKeys();

}
