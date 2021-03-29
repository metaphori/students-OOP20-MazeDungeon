package model.gameobject.dynamicobject.character;

import model.common.VectorDirection;
/**
 * An interface for modeling character Movements.
 *
 */
public interface CharacterMovement {
    /**
     * stops the character when goes vertically.
     */
    void stopVertical();

    /**
     * stops the character when goes horizontally.
     */
    void stopHorizontal();

    /**
     * move the character in the direction specified by the vectorDirection enum.
     * @param vectorDirection
     */
    void move(VectorDirection vectorDirection);
}
