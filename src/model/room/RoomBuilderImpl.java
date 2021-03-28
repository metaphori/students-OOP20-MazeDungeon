package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.dynamicobject.enemy.EnemyFactory;
import model.gameobject.dynamicobject.enemy.EnemyFactoryImpl;
import model.gameobject.simpleobject.door.DoorFactory;
import model.gameobject.simpleobject.door.DoorFactoryImpl;
import model.gameobject.simpleobject.obstacle.ObstacleFactory;
import model.gameobject.simpleobject.obstacle.ObstaclesFactoryImpl;

/**
 * this class implements RoomBuilder.
 * 
 * this type of RoomBuilder has specifics restrictions.
 * A boss can't be added if there are already obstacles or enemies.
 * Enemies can't be added if there is already a boss.
 * Obstacles can't be added if there is already a boss.
 * 
 * Can't call the method Build twice.
 */
public class RoomBuilderImpl implements RoomBuilder {
    private static final int MAX_ENEMY_NUMBER = 4;
    private static final Point2D BOSS_SPAWN_POSITION = new Point2D(256, 70);
    private final DoorFactory doorFactory = new DoorFactoryImpl();
    private final ObstacleFactory obstaclesFactory = new ObstaclesFactoryImpl(); 
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final List<Point2D> avaiableEnemyPosition = new LinkedList<>(List.of(new Point2D(260, 200), 
                                                                                new Point2D(930, 200), 
                                                                                new Point2D(260, 540), 
                                                                                new Point2D(930, 540)));
    private final Room room;
    private boolean build;
    private boolean canAddEnemy = true;
    private boolean canAddObstacle = true;
    private boolean canAddBoss = true;

    /**
     * create a RoomBuilder using the indispensable parameter RoomManager.
     * @param roomManager : the RoomManager of the Room that the builder will create
     */
    public RoomBuilderImpl(final RoomManager roomManager) {
        if (roomManager == null) {
            throw new IllegalArgumentException("roomManager cannot be null");
        }
        this.room = new RoomImpl(roomManager);
        this.room.addAllSimpleObject(obstaclesFactory.createEmptyRoom());
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public RoomBuilder addDoors(final Set<CardinalPoint> doors) {
        if (doors == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        for (final CardinalPoint direction : doors) {
            this.room.addDoor(direction);
            this.room.addSimpleObject(doorFactory.createDoor(direction));
        }
        return this;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public RoomBuilder addObstacle() {
        if (!canAddObstacle) {
            throw new IllegalStateException("cannot add obstacle");
        }
        this.canAddBoss = false;
        this.room.addAllSimpleObject(this.obstaclesFactory.createSquare(3));
        return this;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public RoomBuilder addEnemy() {
        if (!canAddEnemy) {
            throw new IllegalStateException("cannot add enemy");
        }
        this.canAddBoss = false;
        this.canAddEnemy = false;
        final Random rnd = new Random();
        final int enemyNumber = rnd.nextInt(MAX_ENEMY_NUMBER - 1) + 2;
        for (int i = 0; i < enemyNumber; i++) {
            final int pos = rnd.nextInt(this.avaiableEnemyPosition.size());
            final Point2D position = this.avaiableEnemyPosition.get(pos);
            this.avaiableEnemyPosition.remove(pos);
            switch (GameObjectType.getRandomEnemy()) {
            case ENEMY_SKELETON:
                this.room.addDynamicObject(this.enemyFactory.createSkeletonSeeker(position));
                break;
            case ENEMY_SOUL:
                this.room.addDynamicObject(this.enemyFactory.createSoul(position));
                break;
            case ENEMY_SPROUT:
                this.room.addDynamicObject(this.enemyFactory.createSprout(position));
                break;
            default:
                break;
            }
        }
        return this;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public RoomBuilder addBoss() {
        if (!canAddBoss) {
            throw new IllegalStateException("cannot add boss");
        }
        this.canAddBoss = false;
        this.canAddEnemy = false;
        this.canAddObstacle = false;
        this.room.addDynamicObject(this.enemyFactory.createBoss(BOSS_SPAWN_POSITION));
        return this;
    }

    /**
     * This method can't be called twice.
     * @return the room initialized with the builder 
     */
    @Override
    public Room build() {
        if (build) {
            throw new IllegalStateException("already build"); 
        }
        this.build = true;
        return this.room;
    }

}
