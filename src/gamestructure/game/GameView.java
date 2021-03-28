package gamestructure.game;

import java.util.Set;

import animations.State;
import gamestructure.View;
import model.common.GameObjectType;
import model.common.Point2D;
import model.shop.Items;

/**
 * An interface modeling the GameView, defining all the methods necessary for realize the game's GUI.
 * It contains the method for update the HUD, add remove and update the animations, and render the items.
 *
 */
public interface GameView extends View {

    /**
     * Initialize the GameView by initializing the gamePanel.
     */
    void initialize();

    /**
     * @param controller : set controller ad the controller of the GameView
     */
    void setController(GameController controller);

    /**
     * Update the Heads Up Display.
     */
    void updateHUD();

    /**
     * @param id : the id of the GameObject to load the sprite of.
     * @param gameObjectType : the type of the GameObject for the correct loading of it's image.
     * @param position : the position where to print the image.
     */
    void addAnimation(Integer id, GameObjectType gameObjectType, Point2D position);

    /**
     * @param id : the id of the sprite to update.
     * @param position : the new position of the sprite.
     * @param state : the state of the animation.
     */
    void updateAnimation(int id, Point2D position, State state);

    /**
     * @param id : the id of the sprite that has to been removed.
     */
    void removeAnimation(int id);

    /**
     * When called set the won at true.
     */
    void isWon();

    /**
     * When called set the gameOver variable at true.
     */
    void gameOver();

    /**
     * @param purchasedItems : the Set of the item purchased by the player.
     * It add all the item purchased to the HUD panel
     */
    void renderItems(Set<Items> purchasedItems);
}
