package model.gameobject.dinamicobject.character;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import jaco.mp3.player.MP3Player;
import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.bullet.*;
import model.room.Room;
import model.shop.Item;
import model.shop.Items;
import model.gameobject.dinamicobject.character.CharacterMovement;


public class CharacterImpl extends AbstractDinamicObject implements Character {

    /**
     * CONSTANTS.
     */

    private static final double MAX_LIFE = 100;
    private static final int INITIAL_SPEED = 400; 
    private static final int INITIAL_MONEY = 0;

    private static final int INITIAL_BULLET_SPEED = 3;
    private static final int INITIAL_BULLET_DELAY = 200; 


    /*
     * CHARACTER CHARACTERISTIC.
     */
    private double life;
    private int bonusDamage;
    private int bulletSpeed;
    private int money;

    /**
     * VARIABLES.
     */
    private final BulletFactory bulletFactory;
    private long lastShootTime; 
    private boolean shoot;
    private Vector2D shootDirection;
    private boolean won;
    private CharacterMovement characterMovement;



    public CharacterImpl(final Point2D position, final GameObjectType gameObjectType) {
        super(INITIAL_SPEED, position, gameObjectType);
        this.life = MAX_LIFE;
        this.bonusDamage = 0;
        this.bulletSpeed = INITIAL_BULLET_SPEED;
        this.money = INITIAL_MONEY;

        this.characterMovement = new CharacterMovementImpl(this);
        this.bulletFactory = new BulletFactoryImpl();
        this.shoot = false;
        this.lastShootTime = System.currentTimeMillis();
        this.won = false;
    }


    /**
     * Update state.
     */
    @Override
    public void updateState(final double elapsed) { 
        this.move(elapsed);
        if (this.shoot) {
            this.shoot();
        }
    }

    /**
     * take damage to the character.
     */
    @Override
    public void takesDamage(final int damage) {
        this.life = this.life - damage;
        System.out.println(this.getID() + ") " + this.getGameObjectType() + " Life: " + this.getLife());
    }

    /**
     * @return true if the character is death.
     */
    @Override
    public boolean isDead() {
        return this.life <= 0;
    }

    /**
     * @return the current life.
     */
    @Override
    public double getLife() {
        return this.life;
    }

    /**
     * @param life
     * set the current life.
     */
    @Override
    public void setLife(final double life) {
        this.life = life;
    }

    /**
     * @return the max life
     */
    @Override
    public double getMaxLife() {
        return MAX_LIFE;
    }

    /**
     * @return the current bullet damage.
     */
    @Override
    public int getBonusDamage() {
        return this.bonusDamage;
    }

    /**
     * @param damage
     * set the current damage.
     */
    @Override
    public void setBonusDamage(final int damage) {
        this.bonusDamage = damage;
    }

    /**
     * @return the amount of money.
     */
    @Override
    public int getMoney() {
        return this.money;
    }
    /**
     * @param money
     * set the current money amount
     */
    @Override
    public void setMoney(final int money) {
        this.money = money;
    }
    /**
     * @return the bullet speed.
     */
    @Override
    public int getBulletSpeed() {
        return this.bulletSpeed;
    }

    /**
     * @param bulletSpeed
     * set the current bullet speed.
     */
    @Override
    public void setBulletSpeed(final int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }
    
    /**
     * @param obj2 
     * the object the character is collide with.
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
                final int footHeight = 15;
                final Point2D footColliderUL = new Point2D(this.getBoundingBox().getULCorner().getX(), this.getBoundingBox().getBRCorner().getY() - footHeight);
                final BoundingBox footCollider = new BoundingBox(footColliderUL, this.getBoundingBox().getWidth(), footHeight);
                if (footCollider.intersectWith(obj2.getBoundingBox())) {
                    this.setPosition(this.getLastPosition());
                }
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
                    this.money++;
                    System.out.println("CURRENT MONEY BADGE: " + this.money);
                    this.getRoom().deleteGameObject(obj2);
                }
                break;
            default:
                break;
        }
    }


    /*METHODS FOR SHOOTING*/
    /**
     * shoot a bullet.
     */
    private void shoot() {
        Bullet bullet = bulletFactory.createCharacterBullet(
                new Point2D(getPosition().getX() + this.getBoundingBox().getWidth() / 2, getPosition().getY() + this.getBoundingBox().getHeight() / 2),
                this.shootDirection.mul(this.bulletSpeed), this.bonusDamage);
        getRoom().addDinamicObject(bullet);
        /*final MP3Player mp3Player = new MP3Player(new File("resources/sounds/characterhoot.mp3"));
        mp3Player.play();*/
        this.shoot = false;
    }

    /**
     * 
     * @return if the character can shoot
     */
    private boolean canShoot() {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastShootTime > this.INITIAL_BULLET_DELAY) {
            this.lastShootTime = currentTime;
            return true;
        }
        return false;
    }
    /*
     * set a shoot and his shoot direction.
     */
    @Override
    public void setShoot(final boolean shoot, final Vector2D shootDirection) {
        if (this.canShoot()) {
            this.shoot = shoot;
            this.shootDirection = shootDirection;
        }
    }

    /**
     * 
     */
    @Override
    public void pickedUpFinalArtefact() {
        this.won = true;
    }


    @Override
    public boolean isWon() {
        // TODO Auto-generated method stub
        return this.won;
    }
}

