package model.gameobject.dinamicobject.character;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.swing.SwingUtilities;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.bullet.*;
import model.common.BoundingBox;
import model.common.CollisionType;
import model.room.Room;
import model.shop.ItemBuilder;


public class CharacterImpl extends AbstractDinamicObject implements Character {

    private final double MAXLIFE = 4.0;
    private double life;
    private Set<ItemBuilder> items; //contains set di items
    private final BulletFactory bulletFactory;
    private long lastShootTime = System.currentTimeMillis();
    private boolean shoot = false;
    private Vector2D lastDirection;
 
    public CharacterImpl(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final Room room) {
        super(id, speed, position, direction, gameObjectType, room);
        this.life = MAXLIFE;
        this.items = new HashSet<>();
        this.bulletFactory = new BulletFactoryImpl(this.getRoom().getRoomManager().getIdIterator());
        this.lastDirection = new Vector2D(1, 0);
    }

    @Override
    public void shoot() {

            Bullet bullet = bulletFactory.createCharacterBullet(
                    new Point2D(getPosition().getX() + this.getBoundingBox().getWidth() / 2, getPosition().getY() + this.getBoundingBox().getHeight() / 2),
                    getDirection().sum(this.lastDirection),
                    getRoom()); 
            getRoom().addDinamicObject(bullet);
            /*final MP3Player mp3Player = new MP3Player(new File("resources/sounds/characterhoot.mp3"));
            mp3Player.play();*/
            this.shoot = false;
    }

    /**
     * 
     * @return
     */
    private boolean canShoot() {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastShootTime > 500 ) {
            this.lastShootTime = currentTime;
            return true;
        }
        return false;
    }

    /**
     * 
     */
    @Override
    public void takeDamage(final int damage) {
        this.life = this.life - damage;
    }
    /**
     * 
     */
    @Override
    public void updateState(final double elapsed) { 
        this.move(elapsed);
        if (this.shoot) {
            this.shoot();
        }
    }
    /**
     * 
     */
    @Override
    public double getLife() {
        return this.life;
    }
    /**
     * 
     */
    @Override
    public void setLife(final int life) { /*PER DIGI*/
        this.life = life;
    }
    /**
     * 
     */
    @Override
    public Set<ItemBuilder> getItems() {
        return this.items;
    }
    /**
     * 
     */
    @Override
    public void addItem(ItemBuilder item) {
        this.items.add(item);
    }

    /** METHODS FOR MOVEMENT**/

    /**
     * 
     */
    @Override
    public void moveUp() {
        this.setDirection(new Vector2D(this.getDirection().getX() , -1));
        this.setLastDirection(new Vector2D(this.getDirection().getX() , -1));
    }

    /**
     * 
     */
    @Override
    public void moveDown() {
        this.setDirection(new Vector2D(this.getDirection().getX() , 1));
        this.setLastDirection(new Vector2D(this.getDirection().getX() , 1));
    }

    /**
     * 
     */
    @Override
    public void moveRight() {
        this.setDirection(new Vector2D(1, this.getDirection().getY()));
        this.setLastDirection(new Vector2D(1, this.getDirection().getY()));

    }

    /**
     * 
     */
    @Override
    public void moveLeft() {
        this.setDirection(new Vector2D(-1, this.getDirection().getY()));
        this.setLastDirection(new Vector2D(-1, this.getDirection().getY()));
    }

    /**
     * 
     */
    @Override
    public void stopVertical() {
        this.setDirection(new Vector2D(this.getDirection().getX() , 0));

    }
    /**
     * 
     */
    @Override
    public void stopHorizontal() {
        this.setDirection(new Vector2D(0 , this.getDirection().getY()));

    }
    /**
     * TODO
     */
    @Override
    public void collideWith(final GameObject obj2) {
       /*final int footHeight = 15;
        final Point2D footColliderUL = new Point2D(this.getBoundingBox().getULCorner().getX(), this.getBoundingBox().getBRCorner().getY() - footHeight);
        final BoundingBox footCollider = new BoundingBox(footColliderUL, this.getBoundingBox().getWidth(), footHeight);
        if (footCollider.intersectWith(obj2.getBoundingBox())) {
            if (obj2.getGameObjectType().getCollisionType() == CollisionType.INTERACTIVE_ELEMENT && obj2.getGameObjectType() != GameObjectType.CHARACTER_BULLET ) { //COLLISIONE CON MONETE E BULLET
                switch (obj2.getGameObjectType()) {
                    case COIN:
                        System.out.println("COLLECT A COIN");
                        break;
                    default:
                        break;

                }
                this.getRoom().deleteGameObject(obj2);
            }
            this.setDirection(new Vector2D(0, 0));
            this.setPosition(this.getLastPosition());
        }*/

       switch (obj2.getGameObjectType().getCollisionType()) {
            case OBSTACLE:
                this.setPosition(this.getLastPosition());
                break;
            case ENTITY:
                /*this.setPosition(new Point2D(this.getPosition().getX() - (this.getDirection().getX() * 1),
                        this.getPosition().getY()  - (this.getDirection().getY() * 1)));*/
                //this.setPosition(this.getLastPosition());
                final AbstractDinamicObject dinamicObject = (AbstractDinamicObject) obj2;
                dinamicObject.setPosition(dinamicObject.getLastPosition());
                break;
            case INTERACTIVE_ELEMENT:
                if (obj2.getGameObjectType().equals(GameObjectType.COIN)) {
                    System.out.println("COLLECT A COIN");
                    this.getRoom().deleteGameObject(obj2);
                }
                break;
            default:
                break;
        }
        /*this.setPosition(this.getLastPosition());*/
    }

    /*
     * 
     */
    @Override
    public void setShoot(final boolean shoot) {
        if (this.canShoot()) {
            this.shoot = shoot;
        }
    }

    private void setLastDirection(final Vector2D lastDirection) {
        this.lastDirection = lastDirection;
    }
}

