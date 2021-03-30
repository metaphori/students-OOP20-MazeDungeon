package viewutilities;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ImageIcon;

import animations.State;
import model.common.GameObjectType;

/**
 * This class permit to load all the images of each gameObject for each state.
 */
public class ResourceLoader {
    private final Map<GameObjectType, Map<State, List<ImageIcon>>> resources = new HashMap<>();
    private static final String IMG_PATH = "/images/gameObject/";

    /**
     * create a resource loader.
     */
    public ResourceLoader() {
        for (final GameObjectType gameObjectType : GameObjectType.values()) {
            final String gameObjectPath = IMG_PATH + gameObjectType.toString().toLowerCase(Locale.ENGLISH) + "/";
            if (gameObjectType.equals(GameObjectType.INVISIBLE_OBJECT)) {
                continue;
            }
            final Map<State, List<ImageIcon>> stateMap = new HashMap<>();
            for (final State state : gameObjectType.getStates()) {
                final String statePath = gameObjectPath + state.toString().toLowerCase(Locale.ENGLISH) + "/" + gameObjectType.toString().toLowerCase(Locale.ENGLISH);
                final List<ImageIcon> images = new LinkedList<>();
                URL tmp = this.getClass().getResource(statePath + "1.png");
                for (int i = 2; tmp != null; i++) {
                    images.add(new ImageIcon(tmp));
                    tmp = this.getClass().getResource(statePath + i + ".png");
                }
                stateMap.put(state, images);
            }
            resources.put(gameObjectType, stateMap);
        }
    }

    /**
     * 
     * @param gameObjectType
     * @return the resource of the gameObject
     */
    public Map<State, List<ImageIcon>> getStateImages(final GameObjectType gameObjectType) {
        if (!resources.containsKey(gameObjectType)) {
            System.out.println("IMMAGINE NON TROVATA" + gameObjectType);
        }
        return this.resources.get(gameObjectType);
    }

}
