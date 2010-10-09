import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import java.awt.Container;
import java.awt.Dimension;

/**
 *
 * @author Matt
 */
enum PopupType {CARD_CREATE, CARD_EDIT};

//Needs work - action event not being registered (or at least, no popup) - will debug later
public class PopupAction implements ActionListener {

    private JFrame thisWindow;

    private JTextField frontText;
    private JTextField backText;
    private JButton button;
    private PopupType put;
    private VCard vCard;

    public PopupAction(PopupType type, VCard visualCard) {
        vCard = visualCard;
        put = type;
    }

    private void addBoxComponent(JComponent comp, Container cont) {
            cont.add(comp);
            comp.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    }

    public void actionPerformed(ActionEvent e) {
        if(put.equals(PopupType.CARD_CREATE)) {
            thisWindow = new JFrame("New");
            thisWindow.setLayout(new BoxLayout(thisWindow.getContentPane(),BoxLayout.Y_AXIS));
            addBoxComponent(frontText = new JTextField("Front"),thisWindow);
            addBoxComponent(backText = new JTextField("Back"),thisWindow);
            addBoxComponent(button = new JButton("Create"),thisWindow);
            button.addActionListener(new CardAction(frontText,backText,vCard,thisWindow,PopupType.CARD_CREATE));
        }
        else if(put.equals(PopupType.CARD_EDIT)) {
            if(vCard.GetCard() == null)
                return;
            thisWindow = new JFrame("Edit");
            thisWindow.setLayout(new BoxLayout(thisWindow.getContentPane(),BoxLayout.Y_AXIS));
            addBoxComponent(frontText = new JTextField(vCard.GetCard().GetFrontCharacters()),thisWindow);
            addBoxComponent(backText = new JTextField(vCard.GetCard().GetBackCharacters()),thisWindow);
            addBoxComponent(button = new JButton("Edit"),thisWindow);
            button.addActionListener(new CardAction(frontText,backText,vCard,thisWindow,PopupType.CARD_EDIT));
        }
        thisWindow.setVisible(true);
        thisWindow.setSize(new Dimension(200,100));
        thisWindow.setLocationRelativeTo(vCard);
    }
}
