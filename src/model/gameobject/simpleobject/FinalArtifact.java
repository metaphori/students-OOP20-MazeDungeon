package model.gameobject.simpleobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;

public class FinalArtifact extends AbstractSimpleObject {

    public FinalArtifact(final Point2D position) {
        super(position, GameObjectType.FINAL_ARTIFACT);
    }

    /**
     * @param obj2 object with is in collision
     */
    @Override
    public void collideWith(final GameObject obj2) {
        if (obj2.getGameObjectType().equals(GameObjectType.CHARACTER)) {
            this.getRoom().getRoomManager().getCharacter().pickedUpFinalArtifact();
            this.getRoom().deleteGameObject(this);
        }
    }
}
