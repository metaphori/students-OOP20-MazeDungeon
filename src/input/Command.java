package input;

import java.awt.event.KeyEvent;


public interface Command {

    void setKey(KeyEvent key, boolean b);

    //void execute();

    void setMenuClosed();

    void execute();

    boolean checkStopVertical();

    boolean checkStopHorizontal();

}
