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
        resources.put(GameObjectType.ENEMY_BOSS, imagesPath + "Boss" + sep + "Boss.png");
        
        /*LOAD BULLET IMAGE*/
        resources.put(GameObjectType.SOUL_BULLET, imagesPath + "Bullets" + sep + "TestBullet.png");
        resources.put(GameObjectType.CHARACTER_BULLET, imagesPath + "Bullets" + sep + "characterBullet.png");
        resources.put(GameObjectType.BOSS_BULLET, imagesPath + "Bullets" + sep + "bossBullet.png");
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
