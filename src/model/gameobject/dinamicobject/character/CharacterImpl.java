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


    public CharacterImpl(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final Room room) {
        super(id, speed, position, direction, gameObjectType, room);
        this.life = MAXLIFE;
        this.items = new HashSet<>();
        this.bulletFactory = new BulletFactoryImpl(this.getRoom().getRoomManager().getIdIterator());

    }


    @Override
    public void shoot() {
        System.out.println(this.getDirection().getX() + " " + this.getDirection().getY());

        final Vector2D up = new Vector2D(0, 1);
        final Vector2D down = new Vector2D(0, -1);

        final Vector2D left = new Vector2D(-1, 0);
        final Vector2D right = new Vector2D(1, 0);
        Bullet bullet = null;
        if (this.getDirection().getX() == up.getX() && this.getDirection().getY() == up.getY()) { //sto andando in alto
             bullet = this.bulletFactory.createCharacterBullet(GameObjectType.CHARACTER_BULLET_UP, new Point2D(this.getPosition().getX() + this.getBoundingBox().getWidth() / 2, this.getPosition().getY() + this.getBoundingBox().getHeight() / 2), this.getDirection(), this.getRoom());

        }else if (this.getDirection().getX() == down.getX() && this.getDirection().getY() == down.getY()  ) {
            bullet = this.bulletFactory.createCharacterBullet(GameObjectType.CHARACTER_BULLET_DOWN, new Point2D(this.getPosition().getX() + this.getBoundingBox().getWidth() / 2, this.getPosition().getY() + this.getBoundingBox().getHeight() / 2), this.getDirection(), this.getRoom());

        }
        else if (this.getDirection().getX() == left.getX() && this.getDirection().getY() == left.getY()  ) {

            bullet = this.bulletFactory.createCharacterBullet(GameObjectType.CHARACTER_BULLET_LEFT, new Point2D(this.getPosition().getX() + this.getBoundingBox().getWidth() / 2, this.getPosition().getY() + this.getBoundingBox().getHeight() / 2), this.getDirection(), this.getRoom());
        }
        else if (this.getDirection().getX() == right.getX() && this.getDirection().getY() == right.getY()  ) {

            bullet = this.bulletFactory.createCharacterBullet(GameObjectType.CHARACTER_BULLET_RIGHT, new Point2D(this.getPosition().getX() + this.getBoundingBox().getWidth() / 2, this.getPosition().getY() + this.getBoundingBox().getHeight() / 2), this.getDirection(), this.getRoom());
        }
        //final Bullet bullet = this.bulletFactory.createCharacterBullet(GameObjectType.CHARACTER_BULLET, new Point2D(this.getPosition().getX() + this.getBoundingBox().getWidth() / 2, this.getPosition().getY() + this.getBoundingBox().getHeight() / 2), this.getDirection(), this.getRoom());
        //System.out.println("Creo oggetto");
        this.getRoom().addDinamicObject(bullet);

    }

    @Override
    public void takeDamage(final int damage) {
        this.life = this.life - damage;
    }

    @Override
    public void updateState(final double elapsed) { 
        //this.move(elapsed);

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
        this.setPosition(this.getPosition().sum(new Vector2D(0, -10)));
        this.setDirection(new Vector2D(0 , 1));
    }


    @Override
    public void moveDown() {
        this.setPosition(this.getPosition().sum(new Vector2D(0, 10)));
        this.setDirection(new Vector2D(0 , -1));
    }


    @Override
    public void moveRight() {
        this.setPosition(this.getPosition().sum(new Vector2D(10, 0)));
        this.setDirection(new Vector2D(1 , 0));
        
    }


    @Override
    public void moveLeft() {
        this.setPosition(this.getPosition().sum(new Vector2D(-10, 0)));
        this.setDirection(new Vector2D(-1 , 0));
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
        this.setDirection(new Vector2D(0, 0));
        //this.setPosition(this.getLastPosition());
    }

}
