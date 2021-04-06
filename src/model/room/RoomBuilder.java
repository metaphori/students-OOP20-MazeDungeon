package model.room;

import java.util.Set;

import model.common.CardinalPoint;

/**
 * The RoomBuilder is an help for the user to create a Room in an easier way.
 * It Permits to add doors, obstacles, enemies and boss.
 */
public interface RoomBuilder {

    /**
     * @param doors : the direction of each door
     * @return the builder object
     */
    RoomBuilder addDoors(Set<CardinalPoint> doors);

    /**
     * add some obstacles to the room. obstacle can't be added if boss is already in the Room.
     * @return the builder object
     */
    RoomBuilder addRandomObstacle();

    /**
     * add some enemy to the room. enemies can't be added if boss is already in the Room.
     * @return the builder object
     */
    RoomBuilder addRandomEnemy();

    /**
     * add the boss in the room. Boss can't be added if obstacles or enemies are already in the Room.
     * @return the builder object 
     */
    RoomBuilder addBoss();

    /**
     * @return the room initialized with the builder 
     */
    Room build();
}
