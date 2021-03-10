package model.gameobject.dynamicobject.character;

public interface CharacterMovement {
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
    void stopVertical();
    void stopHorizontal();
}
