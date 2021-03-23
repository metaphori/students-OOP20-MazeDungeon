package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.common.VectorDirection;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.bullet.Bullet;
import model.gameobject.dynamicobject.bullet.BulletImpl;
import model.gameobject.dynamicobject.character.Character;
import model.gameobject.dynamicobject.character.CharacterImpl;
import model.gameobject.simpleobject.Coin;
import model.room.Room;
import model.room.RoomImpl;
import model.room.RoomManagerImpl;

public class TestCharacter {


    private Character character;
    private Room room;
    private static final int XPOSITION = 300;
    private static final int YPOSITION = 300;
    private final Point2D initialPoint = new Point2D(XPOSITION, YPOSITION);
    private static final double MAX_LIFE = 100;
    private static final int INITIAL_SPEED = 300; 
    private static final int INITIAL_MONEY = 0;

    @org.junit.Before
    public void initCharacter() {
        this.character = new CharacterImpl(this.initialPoint, GameObjectType.CHARACTER);
        this.room = new RoomImpl(new RoomManagerImpl());
        this.room.addDynamicObject(this.character);
    }

    @org.junit.Test
    public void testCharacterInitialSkills() {
        assertEquals(INITIAL_MONEY, this.character.getMoney());
        assertEquals(INITIAL_SPEED, this.character.getSpeed());
        assertEquals((int) MAX_LIFE, (int) this.character.getLife());
    }

    @org.junit.Test
    public void testCharacterSpawnAndChangePosition() {
        this.room.addDynamicObject(this.character);
        assertTrue(room.getCurrentGameObjects().contains(this.character));
        assertEquals(this.initialPoint, this.character.getPosition());
        final Point2D newPosition = new Point2D(301, 301);
        this.character.setPosition(newPosition);
        assertEquals(newPosition, this.character.getPosition());
    }

    @org.junit.Test
    public void testCharacterShoot() {
        final int object = this.room.getCurrentGameObjects().size();
        this.character.setShoot(true, VectorDirection.UP);
        this.character.updateState(10);
        assertEquals(object + 1, this.room.getCurrentGameObjects().size());

    }

    @org.junit.Test
    public void testCharacterTakesDamage() {
        final BoundingBox bb = new BoundingBox(new Point2D(301, 301), 301, 301);
        this.character.setBoundingBox(bb);
        assertEquals(bb, this.character.getBoundingBox());
        this.character.takesDamage(10);
        assertEquals((int) MAX_LIFE - 10, (int) this.character.getLife());
    }

    @org.junit.Test
    public void testCharacterCollectCoin() {
        final Coin coin = new Coin(new Point2D(301, 301));
        this.room.addSimpleObject(coin);
        assertTrue(room.getCurrentGameObjects().contains(coin));
        assertTrue(room.getCurrentGameObjects().contains(this.character));
        this.character.collideWith(coin);
        assertEquals(1, this.character.getMoney());
    }

    @org.junit.Test
    public void testCharacterWin() {
        assertFalse(this.character.isWin());
        this.character.pickedUpFinalArtifact();
        this.character.isWin();
    }
}
