package model.common;

public enum GameObjectType {
    /**
     * 
     */
    CHARACTER(CollisionType.ENTITY),
    ENEMY_SPROUT(CollisionType.ENTITY),
    ENEMY_SOUL(CollisionType.ENTITY),
    ENEMY_SKELETON(CollisionType.ENTITY),
    ENEMY_BOSS(CollisionType.ENTITY),
    /**
     * 
     */
    COIN(CollisionType.INTERACTIVE_ELEMENT),
    DOOR_LEFT(CollisionType.INTERACTIVE_ELEMENT),
    DOOR_RIGHT(CollisionType.INTERACTIVE_ELEMENT),
    DOOR_UP(CollisionType.INTERACTIVE_ELEMENT),
    DOOR_DOWN(CollisionType.INTERACTIVE_ELEMENT),
    
    CHARACTER_BULLET(CollisionType.INTERACTIVE_ELEMENT),
    SKELETON_BULLET(CollisionType.INTERACTIVE_ELEMENT),
    SOUL_BULLET(CollisionType.INTERACTIVE_ELEMENT),
    SPROUT_BULLET(CollisionType.INTERACTIVE_ELEMENT),
    BOSS_BULLET(CollisionType.INTERACTIVE_ELEMENT),
    OLDGUARDIAN_BULLET(CollisionType.INTERACTIVE_ELEMENT),

    INVISIBLE_OBJECT(CollisionType.OBSTACLE),
    ROCK(CollisionType.OBSTACLE);

    private final CollisionType collisionType;

    GameObjectType(final CollisionType collisionType) {
        this.collisionType = collisionType;
    }

    public CollisionType getCollisionType() {
        return this.collisionType;
    }
}
