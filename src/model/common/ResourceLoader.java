package model.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import gamestructure.PathGetter;
import model.common.animations.State;

public class ResourceLoader {
    private final Map<GameObjectType, Map<State, List<ImageIcon>>> resources = new HashMap<>();
    private static final String IMG_PATH = "resources/images/";

    private final PathGetter pathGetter = new PathGetter();

    public ResourceLoader() {
        final Map<State, List<ImageIcon>> coinMap = new HashMap<>();
        coinMap.put(State.IDLE, this.loadImagesFromPath("Objects/Coin/", "coin", 7, ".png"));
        resources.put(GameObjectType.COIN, coinMap);

        final Map<State, List<ImageIcon>> characterMap = new HashMap<>();
        characterMap.put(State.IDLE, this.loadImagesFromPath("Character/Stopped/", "CharacterStopped", 1, ".png"));
        resources.put(GameObjectType.CHARACTER, characterMap);
        /*resources.put(GameObjectType.ENEMY_SOUL, pathGetter.getPortablePath("resources/images/Enemies/Soul/AttackLeft/Soul1.png"));
        resources.put(GameObjectType.ENEMY_SKELETON, pathGetter.getPortablePath("resources/images/Enemies/Skeleton_Seeker/AttackLeft/Skeleton1.png"));
        resources.put(GameObjectType.ENEMY_SPROUT, pathGetter.getPortablePath("resources/images/Enemies/Sprout/AttackLeft/Sprout1.png"));
        resources.put(GameObjectType.ENEMY_BOSS, pathGetter.getPortablePath("resources/images/Boss/Boss.png"));


        /*LOAD BULLET IMAGE*/
        /*resources.put(GameObjectType.SOUL_BULLET, pathGetter.getPortablePath("resources/images/Bullets/soulBullet.png"));
        resources.put(GameObjectType.CHARACTER_BULLET, pathGetter.getPortablePath("resources/images/Bullets/characterBullet.png"));
        resources.put(GameObjectType.SKELETON_BULLET, pathGetter.getPortablePath("resources/images/Bullets/skeletonBullet.png"));
        resources.put(GameObjectType.BOSS_BULLET, pathGetter.getPortablePath("resources/images/Bullets/bossBullet.png"));
        resources.put(GameObjectType.SPROUT_BULLET, pathGetter.getPortablePath("resources/images/Bullets/sproutBullet.png"));*/

        /*DOOR*/
        final Map<State, List<ImageIcon>> doorUpMap = new HashMap<>();
        doorUpMap.put(State.IDLE, this.loadImagesFromPath("Room/Door/", "topDoor", 1, ".png"));
        resources.put(GameObjectType.DOOR_TOP, doorUpMap);

        final Map<State, List<ImageIcon>> doorBottom = new HashMap<>();
        doorBottom.put(State.IDLE, this.loadImagesFromPath("Room/Door/", "bottomDoor", 1, ".png"));
        resources.put(GameObjectType.DOOR_SOUTH, doorBottom);

        final Map<State, List<ImageIcon>> doorLeft = new HashMap<>();
        doorLeft.put(State.IDLE, this.loadImagesFromPath("Room/Door/", "leftDoor", 1, ".png"));
        resources.put(GameObjectType.DOOR_WEST, doorLeft);

        final Map<State, List<ImageIcon>> doorRight = new HashMap<>();
        doorRight.put(State.IDLE, this.loadImagesFromPath("Room/Door/", "rightDoor", 1, ".png"));
        resources.put(GameObjectType.DOOR_EAST, doorRight);


        /*OBSTACLE*/
        /*resources.put(GameObjectType.ROCK, pathGetter.getPortablePath("resources/images/Room/Obstacle/Rock.png"));

        resources.put(GameObjectType.FINAL_ARTIFACT, pathGetter.getPortablePath("resources/images/Objects/FinalArtifact/WinnerSkull.png"));*/
    }

    /**
     * 
     * @param gameObjectType
     * @return the resource of the gameObject
     */
    public Map<State, List<ImageIcon>> getStateImages(final GameObjectType gameObjectType) {
        if (!resources.containsKey(gameObjectType)) {
            System.out.println("IMMAGINE NON TROVATA");
        }
        return this.resources.get(gameObjectType);
    }

    private List<ImageIcon> loadImagesFromPath(final String initPath, final String imgName, final int numberOfImages, final String extension) {
        if (numberOfImages == 1) {
            return List.of(new ImageIcon(pathGetter.getPortablePath(IMG_PATH + initPath + imgName + extension)));
        }
        final List<ImageIcon> imgList = new ArrayList<>();
        for (int i = 1; i <= numberOfImages; i++) {
            imgList.add(new ImageIcon(pathGetter.getPortablePath(IMG_PATH + initPath + imgName + i + extension)));
        }
        return imgList;
    }
}
