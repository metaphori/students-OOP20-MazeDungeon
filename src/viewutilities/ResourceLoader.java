package viewutilities;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import animations.State;
import model.common.GameObjectType;

public class ResourceLoader {
    private final Map<GameObjectType, Map<State, List<ImageIcon>>> resources = new HashMap<>();
    private static final String IMG_PATH = "/images/";

    private final PathGetter pathGetter = new PathGetter();

    public ResourceLoader() {
        for (final GameObjectType gameObjectType : GameObjectType.values()) {
            final String gameObjectPath = IMG_PATH + gameObjectType.toString().toLowerCase() + "/";
            if (gameObjectType.equals(GameObjectType.INVISIBLE_OBJECT)) {
                continue;
            }
            final Map<State, List<ImageIcon>> stateMap = new HashMap<>();
            for (final State state : gameObjectType.getStates()) {
                final String statePath = gameObjectPath + state.toString().toLowerCase() + "/" + gameObjectType.toString().toLowerCase();
                final List<ImageIcon> images = new LinkedList<>();
                URL tmp;
                for (int i = 1; (tmp = this.getClass().getResource(pathGetter.getPortablePath(statePath + i + ".png"))) != null; i++) {
                    images.add(new ImageIcon(tmp));
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
