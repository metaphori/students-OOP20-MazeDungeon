package input;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.SwingUtilities;

import gamestructure.game.GameController;
import mvc.Model;

public class CommandImpl implements Command {

    private final Model model;
    private final Set<Integer> keysPressed = new HashSet<>();
    private int previousCommand;
    private boolean shoot = false;
    @Override
    public Set<Integer> getKeysPressed() {
        return keysPressed;
    }

    public CommandImpl(final Model model) {
        this.model = model;
    }

    @Override
    public  void execute(final int keyCommand) {
        //keysPressed.add(keyCommand);

        if (!keysPressed.isEmpty()) {
            for (final Iterator<Integer> it = keysPressed.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case KeyEvent.VK_W: //vai su
                        this.model.getRoomManager().getCharacter().moveUp();
                       // System.out.println("VAI SU");
                        this.previousCommand = keyCommand;
                        break;
                    case KeyEvent.VK_A: //vai a sinistra
                       // System.out.println("VAI A SINISTRA");
                        this.model.getRoomManager().getCharacter().moveLeft();
                        break;
                    case KeyEvent.VK_S: //vai giu
                      //  System.out.println("VAI GIU");
                        this.model.getRoomManager().getCharacter().moveDown();
                        break;
                    case KeyEvent.VK_D: //vai a destra
                       // System.out.println("VAI A DESTRA");
                        this.model.getRoomManager().getCharacter().moveRight();
                        break;
                    case KeyEvent.VK_SPACE:
                        System.out.println("SPARA");
                        this.model.getRoomManager().getCharacter().shoot();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.out.println("APRI MENUINGAME");
                        break;
                    default:
                        break;
                       /* System.out.println("TASTO DISABILITATO");
                        break;*/
                }
            }
          
        }
    }
}
