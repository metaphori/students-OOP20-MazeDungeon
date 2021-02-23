package model.gameobject.dinamicobject.character;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.swing.SwingUtilities;

import model.common.BoundingBox;
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
    private long lastShootTime = System.currentTimeMillis();
    private final Runnable bulletThread;


    public CharacterImpl(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final Room room) {
        super(id, speed, position, direction, gameObjectType, room);
        this.life = MAXLIFE;
        this.items = new HashSet<>();
        this.bulletFactory = new BulletFactoryImpl(this.getRoom().getRoomManager().getIdIterator());
        this.bulletThread = new BulletThread();
    }

    private class BulletThread implements Runnable{

        @Override
        public  void run() {
                Bullet bullet = bulletFactory.createCharacterBullet(
                        new Point2D(getPosition().getX() + 50 , getPosition().getY() +  50),
                        getDirection().sum(new Vector2D(getDirection().getX(), getDirection().getY())),
                        getRoom()); 
                getRoom().addDinamicObject(bullet);
               /* try {
                    SwingUtilities.invokeAndWait(this);
                } catch (InvocationTargetException | InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/

        }
    }

    /**
     * RICORDARSI DI STOPPARE IL THREAD UNA VOLTA SPARATO
     */
    @Override
    public void shoot() {
        if (this.canShoot()) {
            this.bulletThread.run();
        }
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
    public void setLife(final int life) {
        this.life = life;
    }
    /**
     * 
     */
    @Override
    public Set<Item> getItems() {
        return this.items;
    }
    /**
     * 
     */
    @Override
    public void addItem(Item item) {
        this.items.add(item);
    }

    /** METHODS FOR MOVEMENT**/

    /**
     * 
     */
    @Override
    public void moveUp() {
        this.setDirection(new Vector2D(this.getDirection().getX() , -1));
    }

    /**
     * 
     */
    @Override
    public void moveDown() {
        this.setDirection(new Vector2D(this.getDirection().getX() , 1));
    }

    /**
     * 
     */
    @Override
    public void moveRight() {
        this.setDirection(new Vector2D(1, this.getDirection().getY()));

    }

    /**
     * 
     */
    @Override
    public void moveLeft() {
        this.setDirection(new Vector2D(-1, this.getDirection().getY()));
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
     * 
     */
    @Override
    public void collideWith(final GameObject obj2) {
        final int footHeight = 15;
        final Point2D footColliderUL = new Point2D(this.getBoundingBox().getULCorner().getX(), this.getBoundingBox().getBRCorner().getY() - footHeight);
        final BoundingBox footCollider = new BoundingBox(footColliderUL, this.getBoundingBox().getWidth(), footHeight);
        if (footCollider.intersectWith(obj2.getBoundingBox())) {
            if (obj2.getGameObjectType() == GameObjectType.COIN) { //COLLECT A COIN
                this.getRoom().deleteGameObject(obj2);
                System.out.println("COLLECT A COIN");
            }
            this.setDirection(new Vector2D(0, 0));
            this.setPosition(this.getLastPosition());
        }
        this.setDirection(new Vector2D(0, 0));
        this.setPosition(this.getLastPosition());
    }
}

