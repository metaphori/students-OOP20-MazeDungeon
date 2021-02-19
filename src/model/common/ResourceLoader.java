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
        resources.put(GameObjectType.CHARACTER, imagesPath + "Character"+ sep + "Character.png");
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
