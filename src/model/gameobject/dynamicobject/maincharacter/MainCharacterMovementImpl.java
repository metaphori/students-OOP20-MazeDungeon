package model.gameobject.dynamicobject.maincharacter;

import model.common.Vector2D;
import model.common.VectorDirection;

/**
 * 
 * The implementation of MainCharacterMovement. 
 * 
 * There are methods to move the Main Character in different direction. 
 *
 */
public class MainCharacterMovementImpl implements MainCharacterMovement {

    private final MainCharacter mainCharacter;

    public MainCharacterMovementImpl(final MainCharacter mainCharacter) {
      this.mainCharacter = mainCharacter;
    }


    private void moveUp() {
        this.mainCharacter.setDirection(new Vector2D(this.mainCharacter.getDirection().getX(), -1));
    }

    private void moveDown() {
        this.mainCharacter.setDirection(new Vector2D(this.mainCharacter.getDirection().getX(), 1));
    }

    private void moveRight() {
        this.mainCharacter.setDirection(new Vector2D(1, this.mainCharacter.getDirection().getY()));
    }

    private void moveLeft() {
        this.mainCharacter.setDirection(new Vector2D(-1, this.mainCharacter.getDirection().getY()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopVertical() {
        this.mainCharacter.setDirection(new Vector2D(this.mainCharacter.getDirection().getX(), 0));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void stopHorizontal() {
        this.mainCharacter.setDirection(new Vector2D(0, this.mainCharacter.getDirection().getY()));
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
