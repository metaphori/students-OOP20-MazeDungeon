package model.gameobject.simpleobject;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;

public class FinalArtefact extends SimpleObjectImpl {

    public FinalArtefact(final Point2D position, final GameObjectType gameObjectType) {
        super(position, gameObjectType);
    }

    /**
     * @param obj2 object with is in collision
     */
    @Override
    public void collideWith(final GameObject obj2) {
        if (obj2.getGameObjectType().equals(GameObjectType.CHARACTER)) {
            System.out.println("HAI VINTOOOOOOOOOO");
        }
    }
}
