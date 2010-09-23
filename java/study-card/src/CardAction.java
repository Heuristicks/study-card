import java.awt.event.*;
import javax.swing.JTextField;
/**
 *
 * @author Matt
 */
public class CardAction implements ActionListener {

    private JTextField fText;
    private JTextField bText;
    private VCard vCard;
    private Card card;

    public CardAction(JTextField frontText, JTextField backText, VCard visualCard, PopupType type) {
        fText = frontText;
        bText = backText;
        vCard = visualCard;
        if(type == PopupType.CARD_CREATE)
        {
            vCard.NewCard();
            card = vCard.GetCard();
        }
        else
            card = vCard.GetCard();
    }

    public void actionPerformed(ActionEvent e) {
        card.SetFrontCharacters(fText.getText());
        card.SetBackCharacters(bText.getText());
        vCard.Update();
    }

}
