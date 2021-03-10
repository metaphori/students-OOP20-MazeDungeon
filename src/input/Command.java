package input;

import java.awt.event.KeyEvent;


public interface Command {

    void setKey(KeyEvent key, boolean b);

    boolean checkStopVertical();

    boolean checkStopHorizontal();

    void execute();

    void setMenuClosed();

}
