package model.common;

import java.util.HashMap;
import java.util.Map;

import gamestructure.PathGetter;

public class ResourceLoader {
    private final Map<GameObjectType, String> resources = new HashMap<>();

    private final PathGetter pathGetter = new PathGetter();

    public ResourceLoader() {
        resources.put(GameObjectType.COIN, pathGetter.getPortablePath("resources/images/Objects/Coin/coin4.png"));
        resources.put(GameObjectType.CHARACTER, pathGetter.getPortablePath("resources/images/Character/Stopped/CharacterStopped.png"));
        resources.put(GameObjectType.ENEMY_SOUL, pathGetter.getPortablePath("resources/images/Enemies/Soul/AttackLeft/Soul1.png"));
        resources.put(GameObjectType.ENEMY_SKELETON, pathGetter.getPortablePath("resources/images/Enemies/Skeleton_Seeker/AttackLeft/Skeleton1.png"));
        resources.put(GameObjectType.ENEMY_SPROUT, pathGetter.getPortablePath("resources/images/Enemies/Sprout/AttackLeft/Sprout1.png"));
        resources.put(GameObjectType.ENEMY_BOSS, pathGetter.getPortablePath("resources/images/Boss/Boss.png"));


        /*LOAD BULLET IMAGE*/
        resources.put(GameObjectType.SOUL_BULLET, pathGetter.getPortablePath("resources/images/Bullets/soulBullet.png"));
        resources.put(GameObjectType.CHARACTER_BULLET, pathGetter.getPortablePath("resources/images/Bullets/characterBullet.png"));
        resources.put(GameObjectType.SKELETON_BULLET, pathGetter.getPortablePath("resources/images/Bullets/skeletonBullet.png"));
        resources.put(GameObjectType.BOSS_BULLET, pathGetter.getPortablePath("resources/images/Bullets/bossBullet.png"));
        resources.put(GameObjectType.SPROUT_BULLET, pathGetter.getPortablePath("resources/images/Bullets/sproutBullet.png"));

        /*DOOR*/
        resources.put(GameObjectType.DOOR_UP, pathGetter.getPortablePath("resources/images/Room/Door/topDoor.png"));
        resources.put(GameObjectType.DOOR_DOWN, pathGetter.getPortablePath("resources/images/Room/Door/bottomDoor.png"));
        resources.put(GameObjectType.DOOR_LEFT, pathGetter.getPortablePath("resources/images/Room/Door/leftDoor.png"));
        resources.put(GameObjectType.DOOR_RIGHT, pathGetter.getPortablePath("resources/images/Room/Door/rightDoor.png"));

        /*OBSTACLE*/
        resources.put(GameObjectType.ROCK, pathGetter.getPortablePath("resources/images/Room/Obstacle/Rock.png"));

        resources.put(GameObjectType.FINAL_ARTIFACT, pathGetter.getPortablePath("resources/images/Objects/FinalArtifact/WinnerSkull.png"));
    }

    /**
     * 
     * @param gameObjectType
     * @return the resource of the gameObject
     */
    public String getPath(final GameObjectType gameObjectType) {
        if (!resources.containsKey(gameObjectType)) {
            System.out.println("IMMAGINE NON TROVATA");
        }
        return this.resources.get(gameObjectType);
    }
}
