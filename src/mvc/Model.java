package mvc;

import java.util.List;

import model.common.Point2D;
import model.gameobject.GameObject;

public interface Model {
    Point2D getGameObjectPosition(int id);

    GameObject getGameObject(Integer id);

    List<GameObject> getActualGameObjects();

    void update(double elapsed);
}
