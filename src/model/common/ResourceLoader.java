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

        final Map<State, List<ImageIcon>> soulMap = new HashMap<>();
        soulMap.put(State.IDLE, this.loadImagesFromPath("Enemies/Soul/MoveLeft/", "Soul", 8, ".png"));
        resources.put(GameObjectType.ENEMY_SOUL, soulMap);

        final Map<State, List<ImageIcon>> skeletonMap = new HashMap<>();
        skeletonMap.put(State.IDLE, this.loadImagesFromPath("Enemies/Skeleton_Seeker/MoveLeft/", "Skeleton", 6, ".png"));
        resources.put(GameObjectType.ENEMY_SKELETON, skeletonMap);

        final Map<State, List<ImageIcon>> sproutMap = new HashMap<>();
        sproutMap.put(State.IDLE, this.loadImagesFromPath("Enemies/Sprout/MoveLeft/", "Sprout", 5, ".png"));
        resources.put(GameObjectType.ENEMY_SPROUT, sproutMap);

        final Map<State, List<ImageIcon>> bossMap = new HashMap<>();
        bossMap.put(State.IDLE, this.loadImagesFromPath("Boss/", "Boss", 1, ".png"));
        resources.put(GameObjectType.ENEMY_BOSS, bossMap);


        /*LOAD BULLET IMAGE*/
        final Map<State, List<ImageIcon>> soulBulletMap = new HashMap<>();
        soulBulletMap.put(State.IDLE, this.loadImagesFromPath("Bullets/", "soulBullet", 1, ".png"));
        resources.put(GameObjectType.SOUL_BULLET, soulBulletMap);

        final Map<State, List<ImageIcon>> characterBulletMap = new HashMap<>();
        characterBulletMap.put(State.IDLE, this.loadImagesFromPath("Bullets/", "characterBullet", 1, ".png"));
        resources.put(GameObjectType.CHARACTER_BULLET, characterBulletMap);

        final Map<State, List<ImageIcon>> skeletonBulletMap = new HashMap<>();
        skeletonBulletMap.put(State.IDLE, this.loadImagesFromPath("Bullets/", "skeletonBullet", 1, ".png"));
        resources.put(GameObjectType.SKELETON_BULLET, skeletonBulletMap);

        final Map<State, List<ImageIcon>> bossBulletMap = new HashMap<>();
        bossBulletMap.put(State.IDLE, this.loadImagesFromPath("Bullets/", "bossBullet", 1, ".png"));
        resources.put(GameObjectType.BOSS_BULLET, bossBulletMap);

        final Map<State, List<ImageIcon>> sproutBulletMap = new HashMap<>();
        sproutBulletMap.put(State.IDLE, this.loadImagesFromPath("Bullets/", "sproutBullet", 1, ".png"));

        resources.put(GameObjectType.SPROUT_BULLET, sproutBulletMap);
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


        final Map<State, List<ImageIcon>> rockMap = new HashMap<>();
        rockMap.put(State.IDLE, this.loadImagesFromPath("Room/Obstacle/", "Rock", 1, ".png"));
        resources.put(GameObjectType.ROCK, rockMap);

        final Map<State, List<ImageIcon>> finalArtifactMap = new HashMap<>();
        finalArtifactMap.put(State.IDLE, this.loadImagesFromPath("Objects/FinalArtifact/", "WinnerSkull", 1, ".png"));
        resources.put(GameObjectType.FINAL_ARTIFACT, finalArtifactMap);
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
            return List.of(new ImageIcon(pathGetter.getPortablePath(IMG_PATH + initPath + imgName + extension)));
        }
        final List<ImageIcon> imgList = new ArrayList<>();
        for (int i = 1; i <= numberOfImages; i++) {
            imgList.add(new ImageIcon(pathGetter.getPortablePath(IMG_PATH + initPath + imgName + i + extension)));
        }
        return imgList;
    }
}
