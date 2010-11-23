import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

    CardAction(JTextField frontText, JTextField backText, VCard visualCard, JFrame frame, PopupType type) {
        fText = frontText;
        bText = backText;
        vCard = visualCard;
        jFrame = frame;
        pyt = type;
    }

    public void actionPerformed(ActionEvent e) {
        final String[] text = { fText.getText(), bText.getText() };
        for(int i = 0; i < text.length; ++i) {
            if(!isValidText(text[i])) {
                JOptionPane.showMessageDialog(vCard.getParent(),
                "The text that you have entered for the card is invalid.",
                "Invalid text",
                JOptionPane.OK_OPTION);
                return;
            }
        }
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

    private boolean isValidText(String text) {
        if(text.length() > 250)
            return false;
        if(text.contains("$") || text.contains("@"))
            return false;
        return true;
    }
}
