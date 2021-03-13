package model.room;

import java.util.List;

import model.gameobject.simpleobject.SimpleObject;

public interface ObstacleFactory {

    List<SimpleObject> createEmptyRoom();

    List<SimpleObject> createSquare(int squares);

}
