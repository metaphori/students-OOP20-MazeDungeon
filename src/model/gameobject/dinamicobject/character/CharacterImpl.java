package model.gameobject.dinamicobject.character;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.bullet.*;
import model.room.RoomImpl;
import model.shop.Item;


public class CharacterImpl extends AbstractDinamicObject implements Character {

    private final double MAXLIFE = 4.0;
    private double life;
    private Set<Item> items; //contains set di items

    public CharacterImpl(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType) {
        super(id, speed, position, direction, gameObjectType);
        this.life = MAXLIFE;
        this.items = new HashSet<>();
    }


    @Override
    public void shoot() {
        //cosa devo fare qui?
    }

    @Override
    public void takeDamage(final int damage) {
        this.life = this.life - damage;
    }

    @Override
    public void updateState(final double elapsed) { 
        //this.move(elapsed);

    }

    @Override
    public void move(double elapsed) {
        super.setSpeed(8);
        super.setPosition(new Point2D(super.getPosition().getX() + super.getDirection().getX(),
                               super.getPosition().getY() + super.getDirection().getY()));
    }


    @Override
    public double getLife() {
        return this.life;
    }

    @Override
    public void setLife(final int life) {
        this.life = life;
    }

    @Override
    public Set<Item> getItems() {
        return this.items;
    }

    @Override
    public void addItem(Item item) {
        this.items.add(item);
    }


    @Override
    public void moveUp() {
        super.setPosition((new Point2D(super.getPosition().getX() + super.getDirection().getX(),
                super.getPosition().getY() + super.getDirection().getY())));
    }


    @Override
    public void moveDown() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void moveRight() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void moveLeft() {
        // TODO Auto-generated method stub
        
    }



}
