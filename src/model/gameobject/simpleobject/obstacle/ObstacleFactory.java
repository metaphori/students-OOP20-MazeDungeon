package model.gameobject.simpleobject.obstacle;

import java.util.List;

import model.gameobject.simpleobject.SimpleObject;

public interface ObstacleFactory {

    List<SimpleObject> createEmptyRoom();

    List<SimpleObject> createSquare(int squares);

}
