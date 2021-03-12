package model.gameobject.dynamicobject.character;

import model.common.VectorDirection;

public interface CharacterMovement {
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
    void stopVertical();
    void stopHorizontal();
    void move(VectorDirection vectorDirection);
}
