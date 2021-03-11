package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.dynamicobject.character.Character;
import model.gameobject.dynamicobject.character.CharacterImpl;

public class TestCharacter {


    private Character character;

    @org.junit.Before
    public void initFactory() {
        this.character = new CharacterImpl(new Point2D(300, 300), GameObjectType.CHARACTER);
    }


}
