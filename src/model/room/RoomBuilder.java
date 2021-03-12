package model.room;

import java.util.Set;

import model.common.CardinalPoint;

public interface RoomBuilder {

    RoomBuilderImpl initialize(RoomManager roomManager);

    RoomBuilderImpl addDoors(Set<CardinalPoint> doors);

    RoomBuilderImpl addRandomObstacle();

    RoomBuilderImpl addRandomEnemy();

    RoomBuilderImpl addBoss();

    Room build();
}
