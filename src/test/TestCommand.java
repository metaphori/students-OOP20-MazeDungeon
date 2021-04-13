package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        final List<Trio<Integer, Boolean, Optional<VectorDirection>>> list = this.command.getKeysList();
        list.stream().forEach(c -> {
            this.command.setKey(c.getX(), true);
            assertTrue(c.getY());
            this.command.execute();
            this.command.setKey(c.getX(), false);
            assertFalse(c.getY());
        });
    }
}

