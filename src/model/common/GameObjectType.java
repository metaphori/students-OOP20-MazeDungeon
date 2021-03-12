package model.common;

import java.util.Random;

public enum GameObjectType {
    /**
     * The main character. Collision type : ENTITY.
     */
    CHARACTER(CollisionType.ENTITY),

    /**
     * An enemy of type : Sprout. Collision type : ENTITY.
     */
    ENEMY_SPROUT(CollisionType.ENTITY),

    /**
     * An enemy of type : Soul. Collision type : ENTITY.
     */
    ENEMY_SOUL(CollisionType.ENTITY),

    /**
     * An enemy of type : Skeleton. Collision type : ENTITY.
     */
    ENEMY_SKELETON(CollisionType.ENTITY),

    /**
     * An enemy of type : Boss. Collision type : ENTITY.
     */
    ENEMY_BOSS(CollisionType.ENTITY),

    /**
     * Coin. Collision type : INTERACTIVE_ELEMENT.
     */
    COIN(CollisionType.INTERACTIVE_ELEMENT),

    /**
     * The Final Artifact. Collision type : INTERACTIVE_ELEMENT.
     */
    FINAL_ARTIFACT(CollisionType.INTERACTIVE_ELEMENT),

    /**
     * The Left Door. Collision type : INTERACTIVE_ELEMENT.
     */
    DOOR_WEST(CollisionType.INTERACTIVE_ELEMENT),

    /**
     * The Right Door. Collision type : INTERACTIVE_ELEMENT.
     */
    DOOR_EAST(CollisionType.INTERACTIVE_ELEMENT),

    /**
     * The Up Door. Collision type : INTERACTIVE_ELEMENT.
     */
    DOOR_TOP(CollisionType.INTERACTIVE_ELEMENT),

    /**
     * The Down Door. Collision type : INTERACTIVE_ELEMENT.
     */
    DOOR_SOUTH(CollisionType.INTERACTIVE_ELEMENT),

    /**
     * The Character Bullet. Collision type : INTERACTIVE_ELEMENT.
     */
    CHARACTER_BULLET(CollisionType.INTERACTIVE_ELEMENT),

    /**
     * The Character Bullet. Collision type : INTERACTIVE_ELEMENT.
     */
    SKELETON_BULLET(CollisionType.INTERACTIVE_ELEMENT),

    /**
     * The Character Bullet. Collision type : INTERACTIVE_ELEMENT.
     */
    SOUL_BULLET(CollisionType.INTERACTIVE_ELEMENT),

    /**
     * The Character Bullet. Collision type : INTERACTIVE_ELEMENT.
     */
    SPROUT_BULLET(CollisionType.INTERACTIVE_ELEMENT),

    /**
     * The Character Bullet. Collision type : INTERACTIVE_ELEMENT.
     */
    BOSS_BULLET(CollisionType.INTERACTIVE_ELEMENT),

    /**
     * Invisible object like Wall. Collision type : OBSTACLE.
     */
    INVISIBLE_OBJECT(CollisionType.OBSTACLE),

    /**
     * The rock. Collision type : OBSTACLE.
     */
    ROCK(CollisionType.OBSTACLE);

    private static final int SIMPLE_ENEMY_NUMBER = 3;
    private final CollisionType collisionType;

    GameObjectType(final CollisionType collisionType) {
        this.collisionType = collisionType;
    }

    public CollisionType getCollisionType() {
        return this.collisionType;
    }

    public static GameObjectType getRandomEnemy() {
        switch (new Random().nextInt(SIMPLE_ENEMY_NUMBER)) {
        case 0:
            return ENEMY_SPROUT;
        case 1:
            return ENEMY_SKELETON;
        default:
            return ENEMY_SOUL;
        }
    }
}
