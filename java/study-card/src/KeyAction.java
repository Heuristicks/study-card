import java.awt.event.*;
/**
 *
 * @author Matt
 */

/*
 * KeyListener for the main window of this program
 * -Space Bar: Flips card to oppposite of current side
 * -Enter/Up Arrow/Right Arrow: Displays the next card (if random card order setting is off, will show next card in sequence)
 * -Backspace/Down Arrow/Left Arrow: Displays the previous card (if random card order setting is off, will show previous card in sequence)
 */
public class KeyAction implements KeyListener {

    private VCard vCard;

    public KeyAction (VCard visualCard) {
        vCard = visualCard;
    }
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE: {
                Card card;
                if((card = vCard.GetCard()) != null) {
                    card.Flip(!card.IsFrontSide());
                    vCard.Update();
                }
                break;
            }
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_UP: {
                if(vCard.GetCard() != null) {
                    vCard.GetCard().Flip(vCard.GetCurrentCardSide());
                }
                vCard.NextCard(true);
                break;
            }
            case KeyEvent.VK_BACK_SPACE:
            case KeyEvent.VK_LEFT: 
            case KeyEvent.VK_DOWN: {
                if(vCard.GetCard() != null) {
                    vCard.GetCard().Flip(vCard.GetCurrentCardSide());

                }
                vCard.NextCard(false);
                break;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}
