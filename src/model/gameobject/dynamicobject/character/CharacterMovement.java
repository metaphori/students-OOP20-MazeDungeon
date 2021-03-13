package model.gameobject.dynamicobject.character;

import model.common.VectorDirection;

public interface CharacterMovement {
    void stopVertical();
    void stopHorizontal();
    void move(VectorDirection vectorDirection);
}
