package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.SwingUtilities;

import gamestructure.game.GameController;
import model.common.Point2D;
import mvc.Model;

public class CommandImpl implements Command {

    private final Model model;
    private boolean[] keys;

    private int key;


    public CommandImpl(final Model model) {
        this.model = model;
        this.keys = new boolean[256];
    }


    @Override
    public void execute(final int keyCommand) {

        if (keyCommand == KeyEvent.VK_W) {

            if (keys[keyCommand]) {
                    this.model.getRoomManager().getCharacter().moveUp();
             }
        }

        if (keyCommand == KeyEvent.VK_S) {

            if (keys[keyCommand]) {
                    this.model.getRoomManager().getCharacter().moveDown();
             }
        }

        if (keyCommand == KeyEvent.VK_D) {

            if (keys[keyCommand]) {
                    this.model.getRoomManager().getCharacter().moveRight();
             }
        }

        if (keyCommand == KeyEvent.VK_A) {

            if (keys[keyCommand]) {
                    this.model.getRoomManager().getCharacter().moveLeft();
             }
        }

        if (keyCommand == KeyEvent.VK_SPACE) {

            if (keys[keyCommand]) {
                    this.model.getRoomManager().getCharacter().shoot();
            }
        }


        if (this.checkStopVertical()) {
            this.model.getRoomManager().getCharacter().stopVertical();
        }
        
        if (this.checkStopHorizontal()) {
            this.model.getRoomManager().getCharacter().stopHorizontal();
        }
        
        



     }

    @Override
    public void setKey(KeyEvent key, boolean b) {
        this.keys[key.getKeyCode()] = b;
       // this.key = key.getKeyCode();

    }

    @Override
    public Model getModel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getKey() {
        // TODO Auto-generated method stub
        return this.key;
    }
    
    @Override
    public void setKey(int key) {
        // TODO Auto-generated method stub
        this.key = key;
    }


    @Override
    public boolean checkStopVertical() {
        
        return !this.keys[KeyEvent.VK_W] && !this.keys[KeyEvent.VK_S];
    }


    @Override
    public boolean checkStopHorizontal() {
       
        return !this.keys[KeyEvent.VK_A] && !this.keys[KeyEvent.VK_D];
    }


}
