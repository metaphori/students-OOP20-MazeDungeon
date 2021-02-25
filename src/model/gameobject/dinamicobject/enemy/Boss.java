package model.gameobject.dinamicobject.enemy;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.bullet.Bullet;


public class Boss extends AbstractEnemy{
   // private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private Vector2D vectorBullet = new Vector2D(0, 1);
    public Boss(final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType) {
        super(speed, position, direction, gameObjectType);
        //this.setLife(20);
        //System.out.println(this.getBoundingBox().getWidth() + "   " + this.getBoundingBox().getHeight());
    }
    private void moveAroundWall() {
        if ((int) this.getDirection().getX() == 0 && (int) this.getDirection().getY() == -1) {
            this.setDirection(new Vector2D(1, 0));
            this.setPosition(new Point2D(this.getPosition().getX() + 1, this.getPosition().getY() + 1));
            vectorBullet = new Vector2D(0, 1);
        }
        else if ((int) this.getDirection().getX() == 1 && (int) this.getDirection().getY() == 0) {
            this.setDirection(new Vector2D(0, 1));
            this.setPosition(new Point2D(this.getPosition().getX() - 1, this.getPosition().getY() + 1));
            vectorBullet = new Vector2D(-1, 0);
        }
        else if ((int) this.getDirection().getX() == 0 && (int) this.getDirection().getY() == 1) {
            this.setDirection(new Vector2D(-1, 0));
            this.setPosition(new Point2D(this.getPosition().getX() - 1, this.getPosition().getY() - 1));
            vectorBullet = new Vector2D(0, -1);
        }
        else if ((int) this.getDirection().getX() == -1 && (int) this.getDirection().getY() == 0) {
            this.setDirection(new Vector2D(0, -1));
            this.setPosition(new Point2D(this.getPosition().getX() + 1, this.getPosition().getY() - 1));
            vectorBullet = new Vector2D(1, 0);
        }
    }
    public void moveUpWall() {
        if ((int) this.getDirection().getX() == 0 && (int) this.getDirection().getY() == -1) {
            this.setDirection(new Vector2D(1, 0));
            this.setPosition(new Point2D(this.getPosition().getX() + 1, this.getPosition().getY() + 1));
            vectorBullet = new Vector2D(0, 1);
        }
        else {
            this.setDirection(new Vector2D(-1, 0));
            this.setPosition(new Point2D(this.getPosition().getX() -1, this.getPosition().getY()-1));
            vectorBullet = new Vector2D(0, 1);
        }
    }
    @Override
    public void collideWith(final GameObject obj2) {
        switch (obj2.getGameObjectType().getCollisionType()) {
        case OBSTACLE:
            //this.setPosition(this.getLastPosition());
            this.changeRoutine();
            break;
        case ENTITY:
            break;

        default:
            break;
        }
    }

    @Override
    protected void changeRoutine() {
        if (this.getLife() >= this.getLife() / 2) {
            moveUpWall();
        } else {
            moveAroundWall();
            this.setSpeed(this.getSpeed() + 10);
            
        }
        //moveAroundWall();
    }

    @Override
    public void shoot() {
        if (this.getRoom().getCharacterPosition().isEmpty()) {
            return;
        }
        final Bullet bullet = this.getBulletFactory().createBossBullet(this.getPosition().sum(new Vector2D(110,100)),
                vectorBullet);
        this.getRoom().addDinamicObject(bullet);
    }

    @Override
    public void updateState(double elapsed) {
        //System.out.println(this.getPosition().getX() +" "+this.getPosition().getY());
       /* 
        * if((int) this.getPosition().getX() == 850 && (int) this.getPosition().getY() == 50) {
            this.setDirection(new Vector2D(0, 1));
        }
        else if((int) this.getPosition().getX() == 850 && (int) this.getPosition().getY() == 450) {
            this.setDirection(new Vector2D(-1, 0));
        }
        else if((int) this.getPosition().getX() == 150 && (int) this.getPosition().getY() == 450) {
            this.setDirection(new Vector2D(0, -1));
        }
        else if((int) this.getPosition().getX() == 150 && (int) this.getPosition().getY() == 50) {
            this.setDirection(new Vector2D(1, 0));
        }*/
        this.move(elapsed);
        if (this.canShoot(1000)) {
            this.shoot();
        }
        
    }

}
