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

    public CardAction(JTextField frontText, JTextField backText, VCard visualCard, JFrame frame, PopupType type) {
        fText = frontText;
        bText = backText;
        vCard = visualCard;
        jFrame = frame;
        if(type == PopupType.CARD_CREATE)
        {
            vCard.NewCard();
            card = vCard.GetCard();
        }
        else
            card = vCard.GetCard();
    }

    public void actionPerformed(ActionEvent e) {
        card.SetCharacters(fText.getText(),bText.getText());
        vCard.Update();
        jFrame.dispose();
    }

}
