package input;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import mvc.Model;

public interface Command {

    void execute(int keyCommand);
    void setKey(KeyEvent key, boolean b);
    int getKey();
    boolean checkStopVertical();
    boolean checkStopHorizontal();
    void lock();
    Set<Integer> getPermittedKeys();
}
