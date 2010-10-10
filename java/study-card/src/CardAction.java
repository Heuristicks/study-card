import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.JFrame;
/**
 *
 * @author Matt
 */
public class CardAction implements ActionListener {

    private JTextField fText;
    private JTextField bText;
    private VCard vCard;
    private Card card;
    private JFrame jFrame;
    private PopupType pyt;

    public CardAction(JTextField frontText, JTextField backText, VCard visualCard, JFrame frame, PopupType type) {
        fText = frontText;
        bText = backText;
        vCard = visualCard;
        jFrame = frame;
        pyt = type;
    }

    public void actionPerformed(ActionEvent e) {
        if(pyt.equals(PopupType.CARD_CREATE)) {
            vCard.NewCard();
            card = vCard.GetCard();
        }
        else if(pyt.equals(PopupType.CARD_EDIT))
            card = vCard.GetCard();
        card.SetCharacters(fText.getText(),bText.getText());
        vCard.Update();
        jFrame.dispose();
    }

}
