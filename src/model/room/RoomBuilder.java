package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.common.Direction;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.dynamicobject.enemy.EnemyFactory;
import model.gameobject.dynamicobject.enemy.EnemyFactoryImpl;

public class RoomBuilder {
    private static final int MAX_ENEMY_NUMBER = 4;
    private static final Point2D BOSS_SPAWN_POSITION = new Point2D(256, 54);
    private final DoorFactory doorFactory = new DoorFactoryImpl();
    private ObstaclesFactory obstaclesFactory; 
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final List<Point2D> avaiableEnemyPosition = new LinkedList<>(List.of(new Point2D(254, 200), 
                                                                                new Point2D(940, 200), 
                                                                                new Point2D(254, 550), 
                                                                                new Point2D(940, 550)));
    private Room room;
    private boolean isInitialize = false;
    private boolean build = false;
    private boolean canAddEnemy = true;
    private boolean canAddObstacle = true;
    private boolean canAddBoss = true;

    private void checkInitialize() {
        if (!isInitialize) {
            throw new IllegalStateException("need to initialize first"); 
        }
    }

    /**
     * 
     * @param roomManager : the roomManager of the room
     * @return the builder object
     */
    public RoomBuilder initialize(final RoomManager roomManager) {
        if (isInitialize) {
           throw new IllegalStateException("cannot initialize twice"); 
        }
        this.isInitialize = true;
        this.room = new RoomImpl(roomManager);
        this.obstaclesFactory = new ObstaclesFactory(this.room.getUL(), this.room.getBR());
        this.room.addAllSimpleObject(obstaclesFactory.getEmptyRoom());
        return this;
    }

    /**
     * 
     * @param doors : the direction of each door
     * @return the builder object
     */
    public RoomBuilder addDoors(final Set<Direction> doors) {
        this.checkInitialize();
        for (final Direction direction : doors) {
            this.room.addDoor(direction);
            this.room.addSimpleObject(doorFactory.createDoor(direction));
        }
        return this;
    }

    /**
     * add some obstacles to the room. cannot add with boss.
     * @return the builder object
     */
    public RoomBuilder addRandomObstacle() {
        this.checkInitialize();
        if (!canAddObstacle) {
            throw new IllegalStateException("cannot add obstacle");
        }
        this.canAddBoss = false;
        this.room.addAllSimpleObject(this.obstaclesFactory.createSquare(3));
        return this;
    }

    /**
     * add some enemy to the room. cannot add with boss.
     * @return the builder object
     */
    public RoomBuilder addRandomEnemy() {
        this.checkInitialize();
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
     * add the boss in the room. cannot add with obstacles or enemies.
     * @return the builder object 
     */
    public RoomBuilder addBoss() {
        this.checkInitialize();
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
     * @return the room initialized with the builder 
     */
    public Room build() {
        this.checkInitialize();
        if (build) {
            throw new IllegalStateException("already build"); 
        }
        this.build = true;
        return this.room;
    }

}
