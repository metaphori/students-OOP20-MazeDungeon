package model.gameobject.dinamicobject.character;


import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import model.common.Direction;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.AbstractGameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;

public class CharacterImpl extends AbstractDinamicObject implements Character   {

    /*
     * il danno toglie 0.5 ogni proiettile
     * */
    private List<ImageIcon> imageIcons;
    private double life;

    public CharacterImpl(final int id, final Point2D position) {
        super(id, position);
        this.life = 4.0; //vita massima
        this.imageIcons = new ArrayList<>(List.of());

        // TODO Auto-generated constructor stub
    }


    private void moveUp() {

    }

 
    private void moveDown() {
        // TODO Auto-generated method stub

    }

 
    private void moveRight() {
        // TODO Auto-generated method stub

    }

 
    private void moveLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setPosition(final Point2D position) {
        super.setPosition(position);
    }

    @Override
    public void updateState() {
        // TODO Auto-generated method stub

    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case UP:
                break;
            case DOWN:
                break;
            case RIGHT:
                break;
            case LEFT:
                break;
            default:
                break;
        }

    }

    @Override
    public void shoot() {

    }

    @Override
    public void takesDamage(double damage) {
        this.life = this.life - damage;
    }


    @Override
    public void setPosition() {
        // TODO Auto-generated method stub
        
    }

}
