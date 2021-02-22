package model.gameobject.dinamicobject.character;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import model.common.GameObjectType;
import model.common.IdIterator;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.bullet.*;
import model.room.Room;
import model.shop.Item;


public class CharacterImpl extends AbstractDinamicObject implements Character {

    private final double MAXLIFE = 4.0;
    private double life;
    private Set<Item> items; //contains set di items
    private final BulletFactory bulletFactory;
    private double velX = 0;
    private double velY = 0;


    public CharacterImpl(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final Room room) {
        super(id, speed, position, direction, gameObjectType, room);
        this.life = MAXLIFE;
        this.items = new HashSet<>();
        this.bulletFactory = new BulletFactoryImpl(this.getRoom().getRoomManager().getIdIterator());

    }


    @Override
    public void shoot() {
        //System.out.println(this.getDirection().getX() + " " + this.getDirection().getY());
        Bullet bullet = this.bulletFactory.createCharacterBullet(GameObjectType.ENEMY_SOUL,
                    new Point2D(this.getPosition().getX() + this.getBoundingBox().getWidth() / 2,
                    this.getPosition().getY() + this.getBoundingBox().getHeight() / 2), this.getDirection(), this.getRoom());
        this.getRoom().addDinamicObject(bullet);

    }

    @Override
    public void takeDamage(final int damage) {
        this.life = this.life - damage;
    }

    @Override
    public void updateState(final double elapsed) { 
        this.move(elapsed);
    }

    /*@Override
    public void move(double elapsed) {
        super.setSpeed(8);
        super.setPosition(new Point2D(super.getPosition().getX() + super.getDirection().getX(),
                               super.getPosition().getY() + super.getDirection().getY()));
    }*/


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
        //this.setPosition(new Point2D(this.getPosition().getX(), 
                //this.getPosition().getY() - 0.5 ));
        this.setDirection(new Vector2D(this.getDirection().getX() , -1));
    }


    @Override
    public void moveDown() {
        //this.setPosition(new Point2D(this.getPosition().getX(), 
                //this.getPosition().getY() + 0.5));
        this.setDirection(new Vector2D(this.getDirection().getX() , 1));
    }


    @Override
    public void moveRight() {
        //this.setPosition(new Point2D(this.getPosition().getX() + 0.5, 
                //this.getPosition().getY() ));
        this.setDirection(new Vector2D(1, this.getDirection().getY()));

    }


    @Override
    public void moveLeft() {
        //this.setPosition(new Point2D(this.getPosition().getX() - 0.5 , 
                //this.getPosition().getY() ));
        this.setDirection(new Vector2D(-1, this.getDirection().getY()));
    }


    @Override
    public void collideWith(final GameObject obj2) {
        /*final int footHeight = 15;
        final Point2D footColliderUL = new Point2D(this.getBoundingBox().getULCorner().getX(), this.getBoundingBox().getBRCorner().getY() - footHeight);
        final BoundingBox footCollider = new BoundingBox(footColliderUL, this.getBoundingBox().getWidth(), footHeight);
        if(footCollider.intersectWith(obj2.getBoundingBox())) {
            this.setDirection(new Vector2D(0, 0));
            this.setPosition(this.getLastPosition());
        }*/
        //this.setDirection(new Vector2D(0, 0));
        this.setPosition(this.getLastPosition());
    }

    @Override
    public void tick() {
        System.out.println(this.getPosition().getX());
    }


}
