package model.gameobject.dynamicobject.character;

import model.common.Vector2D;
import model.common.VectorDirection;

/**
 * 
 * The implementation of CharacterMovement. 
 * 
 * There are methods to move the character in different direction. 
 *
 */
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
     * {@inheritDoc}
     */
    @Override
    public void stopVertical() {
        this.character.setDirection(new Vector2D(this.character.getDirection().getX(), 0));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void stopHorizontal() {
        this.character.setDirection(new Vector2D(0, this.character.getDirection().getY()));
    }

    /**
     * {@inheritDoc}
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
