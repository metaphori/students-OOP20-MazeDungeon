package model.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.ImageIcon;

import gamestructure.PathGetter;
import model.common.animations.State;

public class ResourceLoader {
    private final Map<GameObjectType, Map<State, List<ImageIcon>>> resources = new HashMap<>();
    private static final String IMG_PATH = "resources" + File.separator + "images" + File.separator;

    private final PathGetter pathGetter = new PathGetter();

    public ResourceLoader() {
        for (final GameObjectType gameObjectType : GameObjectType.values()) {
            if (gameObjectType.equals(GameObjectType.INVISIBLE_OBJECT)) {
                continue;
            }
            final Optional<File> directory = this.searchDirectory(new File(IMG_PATH), gameObjectType.toString().toLowerCase());
            if (directory.isEmpty()) {
                System.out.println(gameObjectType);
                System.out.println("folder not found");
                continue;
            }
            final Map<State, List<ImageIcon>> stateMap = new HashMap<>();
            for (final State state : gameObjectType.getStates()) {
                final List<ImageIcon> images = new LinkedList<>();
                final String statePath = this.pathGetter.getPortablePath(directory.get().getPath() + File.separator + state.toString().toLowerCase());
                images.addAll(this.loadImagesFromPath(statePath + File.separator, 
                                                      gameObjectType.toString().toLowerCase(), 
                                                      new File(statePath).list().length,
                                                      ".png"));
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

    private List<ImageIcon> loadImagesFromPath(final String initPath, final String imgName, final int numberOfImages, final String extension) {
        if (numberOfImages == 1) {
            return List.of(new ImageIcon(pathGetter.getPortablePath(initPath + imgName + extension)));
        }
        final List<ImageIcon> imgList = new ArrayList<>();
        for (int i = 1; i <= numberOfImages; i++) {
            imgList.add(new ImageIcon(pathGetter.getPortablePath(initPath + imgName + i + extension)));
        }
        return imgList;
    }

    private Optional<File> searchDirectory(final File startPath, final String name) {
        final List<File> directoryToCheck = new LinkedList<>(Arrays.asList(startPath.listFiles()));
        do {
            final File current = directoryToCheck.get(0);
            directoryToCheck.remove(current);
            if (current.isFile()) {
                continue;
            }
            if (current.isDirectory()) {
                if (name.equals(current.getName())) {
                    return Optional.of(current);
                }
                for (final File file : current.listFiles()) {
                    directoryToCheck.add(file);
                }
            }
        } while (!directoryToCheck.isEmpty());
        return Optional.empty();
    }
}
