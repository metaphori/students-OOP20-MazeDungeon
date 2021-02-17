package model.gameobject.simpleobject;

import model.gameobject.SimpleObjectImpl;

public class Door extends SimpleObjectImpl {
    private boolean isOpen = false;

    /**
     * 
     */
    public void open() {
        this.isOpen = true;
    }

    /**
     * 
     * @return true if the door is open, false if not
     */
    public boolean getIsOpen() {
        return this.isOpen;
    }
}
