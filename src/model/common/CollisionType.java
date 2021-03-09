package model.common;

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
