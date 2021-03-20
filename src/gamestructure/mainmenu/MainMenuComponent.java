package gamestructure.mainmenu;

import javax.swing.ImageIcon;

import viewutilities.PathGetter;

public enum MainMenuComponent {
    /**
     * The button that start a new game.
     */
    BTN_NEW_GAME(new ImageIcon(PathResources.IMAGE_PATH + "NewGame.png")),

    /**
     * The button that show the credits.
     */
    BTN_CREDITS(new ImageIcon(PathResources.IMAGE_PATH + "CreditsButton.png")),

    /**
     * The button for exit the game.
     */
    BTN_EXIT(new ImageIcon(PathResources.IMAGE_PATH + "Exit.png")),

    /**
     * The label where the credits are showed.
     */
    LBL_CREDITS(new ImageIcon(PathResources.IMAGE_PATH + "lblCredits.png")),

    /**
     * The label that contains the background image.
     */
    LBL_BACKGROUND(new ImageIcon(PathResources.IMAGE_PATH + "MainMenuBackground.png"));

    private final ImageIcon img;

    MainMenuComponent(final ImageIcon img) {
        this.img = img;
    }

    private static class PathResources {
        private static final PathGetter PATH_GETTER = new PathGetter();
        private static final String IMAGE_PATH = PATH_GETTER.getPortablePath("resources/images/MainMenu/");
    }

    public ImageIcon getImage() {
        return this.img;
    }
}
