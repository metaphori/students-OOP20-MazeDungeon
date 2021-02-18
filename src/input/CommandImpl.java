package input;

import java.awt.event.KeyEvent;

public class CommandImpl implements Command {

    @Override
    public void execute(final int keyCommand) {
        switch (keyCommand) {
            case KeyEvent.VK_W: //vai su
                System.out.println("VAI SU");
                break;
            case KeyEvent.VK_A: //vai a sinistra
                System.out.println("VAI A SINISTRA");
                break;
            case KeyEvent.VK_S: //vai giu
                System.out.println("VAI GIU");
                break;
            case KeyEvent.VK_D: //vai a destra
                System.out.println("VAI A DESTRA");
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
