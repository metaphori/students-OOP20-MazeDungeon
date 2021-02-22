package input;

import java.awt.event.KeyEvent;
import java.util.Set;

import mvc.Model;

public interface Command {
    
    void execute(int keyCommand);

   

    Model getModel();

    void setKey(KeyEvent key, boolean b);



    int getKey();



    void setKey(int key);
}
