package model.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import animations.State;

/**
 * This enumeration provide to identify a GameObject with a type, 
 * matching to the type, the type of the collision and 
 * a list of possible states of the object (idle, move_left, move_right).
 *
 */
public enum GameObjectType {
    /**
     * The main character. Collision type : ENTITY.
     */
    CHARACTER(CollisionType.ENTITY, List.of(State.IDLE, State.MOVE_LEFT, State.MOVE_RIGHT, State.MOVE_UP, State.MOVE_DOWN)),

    /**
     * An enemy of type : Sprout. Collision type : ENTITY.
     */
    ENEMY_SPROUT(CollisionType.ENTITY, List.of(State.IDLE, State.MOVE_LEFT, State.MOVE_RIGHT)),

    /**
     * An enemy of type : Soul. Collision type : ENTITY.
     */
    ENEMY_SOUL(CollisionType.ENTITY, List.of(State.IDLE, State.MOVE_LEFT, State.MOVE_RIGHT)),

    /**
     * An enemy of type : Skeleton. Collision type : ENTITY.
     */
    ENEMY_SKELETON(CollisionType.ENTITY, List.of(State.IDLE, State.MOVE_LEFT, State.MOVE_RIGHT)),

    /**
     * An enemy of type : Boss. Collision type : ENTITY.
     */
    ENEMY_BOSS(CollisionType.ENTITY, List.of(State.IDLE, State.MOVE_LEFT, State.MOVE_RIGHT)),

    /**
     * Coin. Collision type : INTERACTIVE_ELEMENT.
     */
    COIN(CollisionType.INTERACTIVE_ELEMENT, List.of(State.IDLE)),

    /**
     * The Final Artifact. Collision type : INTERACTIVE_ELEMENT.
     */
    FINAL_ARTIFACT(CollisionType.INTERACTIVE_ELEMENT, List.of(State.IDLE)),

    /**
     * The Left Door. Collision type : INTERACTIVE_ELEMENT.
     */
    DOOR_WEST(CollisionType.INTERACTIVE_ELEMENT, List.of(State.IDLE)),

    /**
     * The Right Door. Collision type : INTERACTIVE_ELEMENT.
     */
    DOOR_EAST(CollisionType.INTERACTIVE_ELEMENT, List.of(State.IDLE)),

    /**
     * The Up Door. Collision type : INTERACTIVE_ELEMENT.
     */
    DOOR_NORTH(CollisionType.INTERACTIVE_ELEMENT, List.of(State.IDLE)),

    /**
     * The Down Door. Collision type : INTERACTIVE_ELEMENT.
     */
    DOOR_SOUTH(CollisionType.INTERACTIVE_ELEMENT, List.of(State.IDLE)),

    /**
     * The Character Bullet. Collision type : INTERACTIVE_ELEMENT.
     */
    CHARACTER_BULLET(CollisionType.INTERACTIVE_ELEMENT, List.of(State.IDLE)),

    /**
     * The Character Bullet. Collision type : INTERACTIVE_ELEMENT.
     */
    SKELETON_BULLET(CollisionType.INTERACTIVE_ELEMENT, List.of(State.IDLE)),

    /**
     * The Character Bullet. Collision type : INTERACTIVE_ELEMENT.
     */
    SOUL_BULLET(CollisionType.INTERACTIVE_ELEMENT, List.of(State.IDLE)),

    /**
     * The Character Bullet. Collision type : INTERACTIVE_ELEMENT.
     */
    SPROUT_BULLET(CollisionType.INTERACTIVE_ELEMENT, List.of(State.IDLE)),

    /**
     * The Character Bullet. Collision type : INTERACTIVE_ELEMENT.
     */
    BOSS_BULLET(CollisionType.INTERACTIVE_ELEMENT, List.of(State.IDLE)),

    /**
     * Invisible object like Wall. Collision type : OBSTACLE.
     */
    INVISIBLE_OBJECT(CollisionType.OBSTACLE, List.of(State.IDLE)),

    /**
     * The rock. Collision type : OBSTACLE.
     */
    ROCK(CollisionType.OBSTACLE, List.of(State.IDLE));

    private static final int SIMPLE_ENEMY_NUMBER = 3;
    private final CollisionType collisionType;
    private final List<State> states;

    /**
     * @param collisionType : the type of the collision.
     * @param states : the possible states.
     */
    GameObjectType(final CollisionType collisionType, final List<State> states) {
        this.collisionType = collisionType;
        this.states = states;
    }

    /**
     * @return the type of the collision of the enemy.
     */
    public CollisionType getCollisionType() {
        return this.collisionType;
    }

    /**
     * @return the list of the possible states of the GameObject.
     */
    public List<State> getStates() {
        return new LinkedList<>(states);
    }

    /**
     * @return a random enemy.
     */
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
