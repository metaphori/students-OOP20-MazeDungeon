package model.room;

import java.util.Set;

import model.common.CardinalPoint;

public interface RoomBuilder {

    RoomBuilder initialize(RoomManager roomManager);

    RoomBuilder addDoors(Set<CardinalPoint> doors);

    RoomBuilder addObstacle();

    RoomBuilder addEnemy();

    RoomBuilder addBoss();

    Room build();
}
