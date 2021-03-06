package model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.common.Direction;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.dinamicobject.enemy.EnemyFactory;
import model.gameobject.dinamicobject.enemy.EnemyFactoryImpl;

public class RoomBuilder {
    private static final int MAX_ENEMY_NUMBER = 4;
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
     * @param roomManager
     * @return . 
     */
    public RoomBuilder initialize(final RoomManager roomManager) {
        if (isInitialize) {
           throw new IllegalStateException("cannot initialize twice"); 
        }
        this.isInitialize = true;
        this.room = new RoomImpl(roomManager);
        this.obstaclesFactory = new ObstaclesFactory(this.room.getUL(), this.room.getBR());
        this.room.addSimpleObject(obstaclesFactory.getEmptyRoom());
        return this;
    }

    /**
     * 
     * @param doors
     * @return .
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
     * 
     * @return .
     */
    public RoomBuilder addRandomObstacle() {
        this.checkInitialize();
        if (!canAddObstacle) {
            throw new IllegalStateException("cannot add obstacle");
        }
        this.canAddBoss = false;
        this.room.addSimpleObject(this.obstaclesFactory.createSquare(3));
        return this;
    }

    /**
     * @return .
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
                this.room.addDinamicObject(this.enemyFactory.createSkeletonSeeker(position));
                break;
            case ENEMY_SOUL:
                this.room.addDinamicObject(this.enemyFactory.createSoul(position));
                break;
            case ENEMY_SPROUT:
                this.room.addDinamicObject(this.enemyFactory.createSprout(position));
                break;
            default:
                break;
            }
        }
        return this;
    }

    /**
     * @return . 
     */
    public RoomBuilder addBoss() {
        this.checkInitialize();
        if (!canAddBoss) {
            throw new IllegalStateException("cannot add boss");
        }
        this.canAddBoss = false;
        this.canAddEnemy = false;
        this.canAddObstacle = false;
        this.room.addDinamicObject(this.enemyFactory.createBoss(new Point2D(256, 54)));
        return this;
    }

    /**
     * 
     * @return . 
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
