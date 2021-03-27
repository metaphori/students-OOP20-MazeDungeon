package gamestructure.ingamemenu.utilities;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class ImageLoader {
    private final Map<Images, Image> images = new HashMap<>();
    private static final int SIZE_IMAGE_ITEM = 100;
    private static final int WIDTH_BTN = 300;
    private static final int HEIGHT_BTN = 90;
    private static final String IMAGES_PATH_INGAMEMENU = "/images/InGameMenu/";
    private static final String IMAGES_PATH_ITEM = "/images/Item/";
    private static final String NAME_BTN_FOLDER = "button/";
    public ImageLoader() {
        images.put(Images.BACKGROUNDMENU, new ImageIcon(this.getClass().getResource(IMAGES_PATH_INGAMEMENU + "ingamemenu.png")).getImage());
        images.put(Images.BACKGROUNDSHOP, new ImageIcon(this.getClass().getResource(IMAGES_PATH_INGAMEMENU + "shop.png")).getImage());
        images.put(Images.BTNRESUME, new ImageIcon(this.getClass().getResource(IMAGES_PATH_INGAMEMENU + NAME_BTN_FOLDER + "resume.png")).getImage().getScaledInstance(WIDTH_BTN, HEIGHT_BTN, Image.SCALE_SMOOTH));
        images.put(Images.BTNEXIT, new ImageIcon(this.getClass().getResource(IMAGES_PATH_INGAMEMENU + NAME_BTN_FOLDER + "exit.png")).getImage().getScaledInstance(WIDTH_BTN, HEIGHT_BTN, Image.SCALE_SMOOTH));
        images.put(Images.BTNSHOP, new ImageIcon(this.getClass().getResource(IMAGES_PATH_INGAMEMENU  + NAME_BTN_FOLDER + "shopbtn.png")).getImage().getScaledInstance(WIDTH_BTN, HEIGHT_BTN, Image.SCALE_SMOOTH));
        images.put(Images.BTNRETURNMENU, new ImageIcon(this.getClass().getResource(IMAGES_PATH_INGAMEMENU + NAME_BTN_FOLDER + "backToMenu.png")).getImage().getScaledInstance(WIDTH_BTN, HEIGHT_BTN, Image.SCALE_SMOOTH));
        images.put(Images.BTNARTHEMIDEBOW, new ImageIcon(this.getClass().getResource(IMAGES_PATH_ITEM + "arthemideBow.png")).getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, Image.SCALE_SMOOTH));
        images.put(Images.BTNHEALTH, new ImageIcon(this.getClass().getResource(IMAGES_PATH_ITEM + "health.png")).getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, Image.SCALE_SMOOTH));
        images.put(Images.BTNHERMESBOOTS, new ImageIcon(this.getClass().getResource(IMAGES_PATH_ITEM + "hermesBoots.png")).getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, Image.SCALE_SMOOTH));
        images.put(Images.BTNZEUSBOLT, new ImageIcon(this.getClass().getResource(IMAGES_PATH_ITEM + "zeusBolt.png")).getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, Image.SCALE_SMOOTH));
        images.put(Images.BTNORACLEAMULET, new ImageIcon(this.getClass().getResource(IMAGES_PATH_ITEM + "oracleAmulet.png")).getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, Image.SCALE_SMOOTH));

    }
    /**
     * @param im
     * @return ImageIcon : for to load image
     */
    public ImageIcon getImage(final Images im) {
        if (!images.containsKey(im)) {
            System.out.println("IMMAGINE NON TROVATA");
        }
        return new ImageIcon(this.images.get(im));
    }
}
