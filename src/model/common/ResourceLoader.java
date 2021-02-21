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

        /*LOAD BULLET IMAGE*/
        resources.put(GameObjectType.CHARACTER_BULLET_RIGHT, imagesPath + "Bullets" + sep + "CharacterBulletRight.png");
        resources.put(GameObjectType.CHARACTER_BULLET_LEFT, imagesPath + "Bullets" + sep + "CharacterBulletLeft.png");
        resources.put(GameObjectType.CHARACTER_BULLET_UP, imagesPath + "Bullets" + sep + "CharacterBulletUp.png");
        resources.put(GameObjectType.CHARACTER_BULLET_DOWN, imagesPath + "Bullets" + sep + "CharacterBulletDown.png");
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
