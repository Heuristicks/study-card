import javax.swing.JPanel;
import javax.swing.JLabel;
/**
 *
 * @author Matt
 */
public class VCard extends JPanel {

    private Card thisCard;
    private JLabel placeholder;     //Eventually implementing an OpenGL graphic to display card contents

    public VCard() {
        thisCard = new Card();
        placeholder = new JLabel(thisCard.GetFrontCharacters());
        this.add(placeholder);
    }

    public Card GetCard() {
        return thisCard;
    }
}
