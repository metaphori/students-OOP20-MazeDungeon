package model.gameobject.dinamicobject.enemy;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.dinamicobject.bullet.Bullet;
import model.room.Room;

public class Boss extends AbstractEnemy{
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public Boss(final int id,final int speed,final Point2D position,final Vector2D direction,final GameObjectType gameObjectType,final Room room) {
        super(id, speed, position, direction, gameObjectType, room);
        //System.out.println(this.getBoundingBox().getWidth() + "   " + this.getBoundingBox().getHeight());
    }

    @Override
    protected void changeRoutine() {
        
        if(this.getDirection().equals(new Vector2D(-1,0))) {
            this.setDirection(new Vector2D(1,0));
            this.setPosition(new Point2D(this.getPosition().getX()+5, this.getPosition().getY()));
        }
        else if(this.getDirection().equals(new Vector2D(1,0))) {
            this.setDirection(new Vector2D(-1,0));
            this.setPosition(new Point2D(this.getPosition().getX()-5, this.getPosition().getY()));
        }
       /* System.out.println(this.getPosition());
        if(this.getPosition().getX() >= screen.width - 800) {
            this.setDirection(new Vector2D(-1, 0));
            this.setPosition(new Point2D(this.getPosition().getX()-50, this.getPosition().getY()));
        }
        else if(this.getPosition().getX() <= 0 +800) {
            this.setPosition(new Point2D(this.getPosition().getX()+50, this.getPosition().getY()));
            this.setDirection(new Vector2D(1, 0));
        }
        /*final Random rndFlipDirection = new Random();
        final double newX = this.getDirection().getX() * (rndFlipDirection.nextBoolean() ? -1 : 0);
        final double newY = this.getDirection().getY() * (rndFlipDirection.nextBoolean() ? 1 : 0);
        this.setDirection(new Vector2D(newX, newY));*/
    }

    @Override
    public void shoot() {
        if (this.getRoom().getCharacterPosition().isEmpty()) {
            return;
        }
        final Bullet bullet = this.getBulletFactory().createBossBullet(GameObjectType.BOSS_BULLET, this.getPosition().sum(new Vector2D(110,100)),
                new Vector2D(0, 1), this.getRoom());
        this.getRoom().addDinamicObject(bullet);
    }

    @Override
    public void updateState(double elapsed) {
       // System.out.println(this.getPosition().getX()+" " + this.getPosition().getY());
       /* if (this.getPosition().getX() >= screen.width - 800) {
            this.setDirection(new Vector2D(-1, 0));
            this.setPosition(new Point2D(this.getPosition().getX()-50, this.getPosition().getY()));
        }
        else if (this.getPosition().getX() <= 0 +800) {
            this.setPosition(new Point2D(this.getPosition().getX()+50, this.getPosition().getY()));
            this.setDirection(new Vector2D(1, 0));
        }      */
        this.move(elapsed);
        if(this.canShoot()) {
            this.shoot();
        }
        
    }

}
