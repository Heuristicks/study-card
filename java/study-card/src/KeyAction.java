import java.awt.event.*;
/**
 *
 * @author Matt
 */
public class KeyAction implements KeyListener {

    private VCard vCard;

    public KeyAction (VCard visualCard) {
        vCard = visualCard;
    }
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == e.VK_SPACE) {
            vCard.GetCard().Flip(!vCard.GetCard().Front());
            vCard.Update();
        }
        if(e.getKeyCode() == e.VK_ENTER) {
            vCard.NextCard();
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}
