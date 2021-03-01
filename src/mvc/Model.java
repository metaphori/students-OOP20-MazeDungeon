package mvc;

import java.util.List;
import java.util.Optional;

import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.RoomManager;

public interface Model {
    Point2D getGameObjectPosition(int id);

    GameObject getGameObject(int id);

    List<GameObject> getActualGameObjects();

    void update(double elapsed);

    RoomManager getRoomManager();

    boolean isGameOver();
}
