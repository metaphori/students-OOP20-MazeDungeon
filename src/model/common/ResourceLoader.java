package model.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {
    private final Map<GameObjectType, String> resources = new HashMap<>();

    private final String sep = File.separator;
    private final String imagesPath = "resources" + sep + "images" + sep;

    public ResourceLoader() {
        resources.put(GameObjectType.COIN, imagesPath + "Objects" + sep + "Coin" + sep + "coin4.png");
        resources.put(GameObjectType.CHARACTER, imagesPath + "Character" + sep + "CharacterStopped.png");
        resources.put(GameObjectType.ENEMY_SOUL, imagesPath + "Enemies" + sep + "Soul" + sep + "MoveLeft" + sep + "Soul1.png");
        resources.put(GameObjectType.ENEMY_SKELETON, imagesPath + "Enemies" + sep + "Skeleton_Seeker" + sep + "Spawn" + sep + "Skeleton9.png");
        resources.put(GameObjectType.ENEMY_SPROUT, imagesPath + "Enemies" + sep + "Sprout" + sep + "Death" + sep + "Sprout1.png");
        resources.put(GameObjectType.ENEMY_BOSS, imagesPath + "Boss" + sep + "Boss.png");


        /*LOAD BULLET IMAGE*/
        resources.put(GameObjectType.SOUL_BULLET, imagesPath + "Bullets" + sep + "TestBullet.png");
        resources.put(GameObjectType.CHARACTER_BULLET, imagesPath + "Bullets" + sep + "characterBullet.png");
        resources.put(GameObjectType.SKELETON_BULLET, imagesPath + "Bullets" + sep + "TestSkull1.png");
        resources.put(GameObjectType.BOSS_BULLET, imagesPath + "Bullets" + sep + "bossBullet.png");
        resources.put(GameObjectType.SPROUT_BULLET, imagesPath + "Bullets" + sep + "TestSprout1.png");

        /*DOOR*/
        resources.put(GameObjectType.DOOR_UP, imagesPath + "Door" + sep + "topDoor.png");
        resources.put(GameObjectType.DOOR_DOWN, imagesPath + "Door" + sep + "bottomDoor.png");
        resources.put(GameObjectType.DOOR_LEFT, imagesPath + "Door" + sep + "leftDoor.png");
        resources.put(GameObjectType.DOOR_RIGHT, imagesPath + "Door" + sep + "rightDoor.png");

        /*OBSTACLE*/
        resources.put(GameObjectType.ROCK, imagesPath + "Obstacle" + sep + "Rock1.png");

        resources.put(GameObjectType.FINAL_ARTEFACT, imagesPath + "Objects" + sep + "FinalItem" + sep + "WinnerSkull.png");
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
