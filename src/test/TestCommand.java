package test;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Optional;

import gamestructure.game.GameController;
import gamestructure.game.GameControllerImpl;
import input.Command;
import input.CommandImpl;
import input.Trio;
import model.Model;
import model.ModelImpl;
import model.common.VectorDirection;


public class TestCommand {

    private Command command;

    @org.junit.Before
    public void initKeysList() {
      final Model model;
      final GameController gameController;
      model = new ModelImpl();
      gameController = new GameControllerImpl(model);
      this.command = new CommandImpl(model, gameController);
    }

    @org.junit.Test
    public void testCommands() {
        //Premo il pulsante A 
        final List<Trio<Integer, Boolean, Optional<VectorDirection>>> list = this.command.getKeysList();
        final Trio<Integer, Boolean, Optional<VectorDirection>> trio;
        this.command.setKey(KeyEvent.VK_A, true);
        trio = list.stream().filter(t -> t.getX() == KeyEvent.VK_A).findFirst().get();
        assertTrue(trio.getY());
    }

}

