package model.gameobject.dynamicobject.character;

import model.common.Vector2D;
import model.common.VectorDirection;

public class CharacterMovementImpl implements CharacterMovement {

    private final Character character;

    public CharacterMovementImpl(final Character character) {
      this.character = character;
    }


    private void moveUp() {
        this.character.setDirection(new Vector2D(this.character.getDirection().getX(), -1));
    }

    private void moveDown() {
        this.character.setDirection(new Vector2D(this.character.getDirection().getX(), 1));
    }

    private void moveRight() {
        this.character.setDirection(new Vector2D(1, this.character.getDirection().getY()));
    }

    private void moveLeft() {
        this.character.setDirection(new Vector2D(-1, this.character.getDirection().getY()));
    }

    /**
     * stops the character when moving vertically.
     */
    @Override
    public void stopVertical() {
        this.character.setDirection(new Vector2D(this.character.getDirection().getX(), 0));
    }
    /**
     * stops the character when moving horizontally.
     */
    @Override
    public void stopHorizontal() {
        this.character.setDirection(new Vector2D(0, this.character.getDirection().getY()));
    }

    /**
     * 
     */
    @Override
    public void move(final VectorDirection vectorDirection) {
        switch (vectorDirection) {
            case UP:
                this.moveUp();
                break;
            case DOWN:
                this.moveDown();
                break;
            case LEFT:
                this.moveLeft();
                break;
            case RIGHT:
                this.moveRight();
                break;
            default:
                break;
        }
    }
}
