package model.gameobject.simpleobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;

/**
 * This class model the FinalArtifact, 
 * a SimpleObject that make the game finish when is picked up by MainCharacter. 
 *
 */
public class FinalArtifact extends AbstractSimpleObject {

    /**
     * Build a new FinalArtifact.
     * @param position : the position where the FinalArtifact will be spawned.
     */
    public FinalArtifact(final Point2D position) {
        super(position, GameObjectType.FINAL_ARTIFACT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(final GameObject obj2) {
        if (obj2.getGameObjectType().equals(GameObjectType.MAINCHARACTER)) {
            this.getRoom().getRoomManager().getMainCharacter().pickedUpFinalArtifact();
            this.getRoom().deleteGameObject(this);
        }
    }
}
