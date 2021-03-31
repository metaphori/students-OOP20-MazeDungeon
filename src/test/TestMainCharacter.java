package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.VectorDirection;
import model.gameobject.dynamicobject.maincharacter.MainCharacter;
import model.gameobject.dynamicobject.maincharacter.MainCharacterImpl;
import model.gameobject.simpleobject.Coin;
import model.room.Room;
import model.room.RoomImpl;
import model.room.RoomManagerImpl;

public class TestMainCharacter {


    private MainCharacter mainCharacter;
    private Room room;
    private static final int XPOSITION = 300;
    private static final int YPOSITION = 300;
    private final Point2D initialPoint = new Point2D(XPOSITION, YPOSITION);
    private static final double MAX_LIFE = 100;
    private static final int INITIAL_SPEED = 300; 
    private static final int INITIAL_MONEY = 0;
    private static final int INITIAL_BULLET_DELAY = 400;

    @org.junit.Before
    public void initCharacter() {
        this.mainCharacter = new MainCharacterImpl(this.initialPoint, GameObjectType.MAINCHARACTER);
        this.room = new RoomImpl(new RoomManagerImpl());
        this.room.addDynamicObject(this.mainCharacter);
    }

    @org.junit.Test
    public void testCharacterInitialSkills() {
        assertEquals(INITIAL_MONEY, this.mainCharacter.getMoney());
        assertEquals(INITIAL_SPEED, this.mainCharacter.getSpeed());
        assertEquals((int) MAX_LIFE, (int) this.mainCharacter.getLife());
    }

    @org.junit.Test
    public void testCharacterSpawnAndChangePosition() {
        this.room.addDynamicObject(this.mainCharacter);
        assertTrue(room.getCurrentGameObjects().contains(this.mainCharacter));
        assertEquals(this.initialPoint, this.mainCharacter.getPosition());
        final Point2D newPosition = new Point2D(301, 301);
        this.mainCharacter.setPosition(newPosition);
        assertEquals(newPosition, this.mainCharacter.getPosition());
    }

    @org.junit.Test
    public void testCharacterShoot() {
        final int objects = this.room.getCurrentGameObjects().size();
        try {
            Thread.sleep(INITIAL_BULLET_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final BoundingBox bb = new BoundingBox(new Point2D(301, 301), 301, 301);
        this.mainCharacter.setBoundingBox(bb);
        this.mainCharacter.setShoot(true, VectorDirection.UP);
        this.mainCharacter.update(1000);
        assertEquals(objects + 1, this.room.getCurrentGameObjects().size());

    }

    @org.junit.Test
    public void testCharacterTakesDamage() {
        final BoundingBox bb = new BoundingBox(new Point2D(301, 301), 301, 301);
        this.mainCharacter.setBoundingBox(bb);
        assertEquals(bb, this.mainCharacter.getBoundingBox());
        this.mainCharacter.takesDamage(10);
        assertEquals((int) MAX_LIFE - 10, (int) this.mainCharacter.getLife());
    }

    @org.junit.Test
    public void testCharacterCollectCoin() {
        final Coin coin = new Coin(new Point2D(301, 301));
        this.room.addSimpleObject(coin);
        assertTrue(room.getCurrentGameObjects().contains(coin));
        assertTrue(room.getCurrentGameObjects().contains(this.mainCharacter));
        this.mainCharacter.collideWith(coin);
        assertEquals(1, this.mainCharacter.getMoney());
    }

    @org.junit.Test
    public void testCharacterWin() {
        assertFalse(this.mainCharacter.isWin());
        this.mainCharacter.pickedUpFinalArtifact();
        this.mainCharacter.isWin();
    }
}
