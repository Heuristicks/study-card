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
            Card card;
            if((card = vCard.GetCard()) != null) {
                card.Flip(!card.IsFrontSide());
                vCard.Update();
            }
        }
        if(e.getKeyCode() == e.VK_ENTER) {
            if(vCard.GetCard() != null) {
                vCard.GetCard().Flip(true);

            }
            vCard.NextCard();
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}
