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
        this.setLife(20);
        //System.out.println(this.getBoundingBox().getWidth() + "   " + this.getBoundingBox().getHeight());
    }

    @Override
    protected void changeRoutine() {

        System.out.println(this.getLife()+" Vita rimasta");
        if(this.getLife() > 15 ) {
            this.setLife(this.getLife()-1);//modifico vita boss MODIFICARE
            if ((int) this.getDirection().getX() == -1 && (int) this.getDirection().getY() == 0) {
                this.setDirection(new Vector2D(1, 0));
                this.setPosition(new Point2D(this.getPosition().getX() , this.getPosition().getY()));
            }
            else if((int) this.getDirection().getX() == 1 && (int) this.getDirection().getY() == 0) {
                this.setDirection(new Vector2D(-1, 0));
                this.setPosition(new Point2D(this.getPosition().getX() , this.getPosition().getY()));
            }
        }
        
        
    }

    @Override
    public void shoot() {
        if (this.getRoom().getCharacterPosition().isEmpty()) {
            return;
        }
        final Bullet bullet = this.getBulletFactory().createBossBullet(this.getPosition().sum(new Vector2D(110,100)),
                new Vector2D(0, 1), this.getRoom());
        this.getRoom().addDinamicObject(bullet);
    }

    @Override
    public void updateState(double elapsed) {

        this.move(elapsed);
        if(this.canShoot()) {
            this.shoot();
        }
        
    }

}
