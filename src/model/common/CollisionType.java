package model.common;
/**
 * 
 * Enumeration that represents the game objects with which it is possible to have a collision.
 *
 */
public enum CollisionType {
    /**
     * identifies a static object.
     */
    OBSTACLE,
    /**
     * identifies a dynamic object. 
     */
    ENTITY,
    /**
     * identifies an object that interacts with entities.
     */
    INTERACTIVE_ELEMENT;
}
