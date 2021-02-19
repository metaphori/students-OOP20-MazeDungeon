package input;

import java.awt.event.KeyEvent;

import gamestructure.game.GameController;
import mvc.Model;

public class CommandImpl implements Command {

    private final Model model;

    public CommandImpl(final Model model) {
        this.model = model;
    }

 

    @Override
    public void execute(final int keyCommand) {
        switch (keyCommand) {
            case KeyEvent.VK_W: //vai su
                this.model.getRoomManager().getCharacter().moveUp();
                System.out.println("VAI SU");
                break;
            case KeyEvent.VK_A: //vai a sinistra
                System.out.println("VAI A SINISTRA");
                this.model.getRoomManager().getCharacter().moveLeft();
                break;
            case KeyEvent.VK_S: //vai giu
                System.out.println("VAI GIU");
                this.model.getRoomManager().getCharacter().moveDown();
                break;
            case KeyEvent.VK_D: //vai a destra
                System.out.println("VAI A DESTRA");
                this.model.getRoomManager().getCharacter().moveRight();
                break;
            case KeyEvent.VK_SPACE:

                System.out.println("SPARA");
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
