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
enum PopupType {CARD_CREATE, CARD_EDIT, STACK};

//Needs work - action event not being registered (or at least, no popup) - will debug later
public class PopupAction implements ActionListener {

    private JFrame thisWindow;

    private JTextField frontText;
    private JTextField backText;
    private JButton button;

    public PopupAction(PopupType type, VCard visualCard) {
        if(type == PopupType.CARD_CREATE) {
            thisWindow = new JFrame("New");
            thisWindow.setLayout(new BoxLayout(thisWindow.getContentPane(),BoxLayout.Y_AXIS));
            addBoxComponent(frontText = new JTextField("Front"),thisWindow);
            addBoxComponent(backText = new JTextField("Back"),thisWindow);
            addBoxComponent(button = new JButton("Create"),thisWindow);
            button.addActionListener(new CardAction(frontText,backText,visualCard,thisWindow,PopupType.CARD_CREATE));
        }
        else if(type == PopupType.CARD_EDIT) {
            thisWindow = new JFrame("Edit");
            thisWindow.setLayout(new BoxLayout(thisWindow.getContentPane(),BoxLayout.Y_AXIS));
            addBoxComponent(frontText = new JTextField(visualCard.GetCard().GetFrontCharacters()),thisWindow);
            addBoxComponent(backText = new JTextField(visualCard.GetCard().GetBackCharacters()),thisWindow);
            addBoxComponent(button = new JButton("Edit"),thisWindow);
            button.addActionListener(new CardAction(frontText,backText,visualCard,thisWindow,PopupType.CARD_EDIT));
        }
        
        thisWindow.setSize(new Dimension(200,100));
    }

    private void addBoxComponent(JComponent comp, Container cont) {
            cont.add(comp);
            comp.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    }

    public void actionPerformed(ActionEvent e) {
        thisWindow.setVisible(true);
    }
}
