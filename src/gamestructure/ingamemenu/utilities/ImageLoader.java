package gamestructure.ingamemenu.utilities;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class ImageLoader {
    private final Map<Images, Image> images = new HashMap<>();
    private static int SIZE_IMAGE_ITEM = 100;
    private final String sep = File.separator;
    private final String imagesPathInGameMenu = "resources" + sep + "images" + sep + "InGameMenu" + sep;
    private final String imagesPathItem = "resources" + sep + "images" + sep + "Item" + sep ;
    
    public ImageLoader() {
        images.put(Images.BACKGROUNDMENU, new ImageIcon(imagesPathInGameMenu +"ingamemenu.png").getImage());
        images.put(Images.BACKGROUNDSHOP, new ImageIcon(imagesPathInGameMenu+ sep +"shop.png").getImage());
        images.put(Images.BTNRESUME, new ImageIcon(imagesPathInGameMenu + sep +"resume.png").getImage().getScaledInstance(300, 90, Image.SCALE_SMOOTH));
        images.put(Images.BTNEXIT, new ImageIcon(imagesPathInGameMenu + sep +"exit.png").getImage().getScaledInstance(300, 90, Image.SCALE_SMOOTH));
        images.put(Images.BTNSHOP, new ImageIcon(imagesPathInGameMenu + sep +"shopbtn.png").getImage().getScaledInstance(300, 90, Image.SCALE_SMOOTH));
        images.put(Images.BTNRETURNMENU, new ImageIcon(imagesPathInGameMenu + sep +"backToMenu.png").getImage().getScaledInstance(300, 90, Image.SCALE_SMOOTH));
        images.put(Images.BTNARTHEMIDEBOW, new ImageIcon(imagesPathItem + sep +"arthemideBow.png").getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, Image.SCALE_SMOOTH));
        images.put(Images.BTNHEALTH, new ImageIcon(imagesPathItem + sep +"health.png").getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, Image.SCALE_SMOOTH));
        images.put(Images.BTNHERMESBOOTS, new ImageIcon(imagesPathItem + sep +"hermesBoots.png").getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, Image.SCALE_SMOOTH));
        images.put(Images.BTNZEUSBOLT, new ImageIcon(imagesPathItem + sep +"zeusBolt.png").getImage().getScaledInstance(SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM, Image.SCALE_SMOOTH));

    }
    public ImageIcon getImage(final Images im) {
        if(!images.containsKey(im)) {
            System.out.println("IMMAGINE NON TROVATA");
        }
        return new ImageIcon(this.images.get(im));
    }
}
