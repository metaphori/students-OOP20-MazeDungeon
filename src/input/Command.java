package input;

import java.util.Set;

public interface Command {
    
    void execute(int keyCommand);

    Set<Integer> getKeysPressed();
}
