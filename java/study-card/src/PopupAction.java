import java.awt.event.*;
import javax.swing.*;
import java.awt.Container;
import java.awt.Dimension;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Matt
 */
enum PopupType {CARD_CREATE, CARD_EDIT, CARD_DELETE, STACK_CREATE};

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
        else if(put.equals(PopupType.CARD_DELETE)) {
            if(vCard.GetCard() != null) {
                int n = JOptionPane.showConfirmDialog(vCard.getParent(),
                                        "Are you sure you would like to delete the currently display card from the stack?",
                                        "Please confirm",
                                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION)
                    vCard.DeleteCurrentCard();
            }
        }
        else if(put.equals(PopupType.STACK_CREATE)) {
            int n = JOptionPane.showConfirmDialog(vCard.getParent(),
                                    "Would you like to save the current stack?",
                                    "Please confirm",
                                    JOptionPane.YES_NO_OPTION);
            
            if (n == JOptionPane.YES_OPTION) {
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(new TextFileFilter());
                
                int returnVal = fc.showSaveDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        Stack stackToUse = vCard.GetStack();
                        stackToUse.Save(fc.getSelectedFile());
                    }
                    catch (FileNotFoundException fnfe) {
                        return;
                    }
                    catch (IOException fnfe) {
                        return;
                    }
                }
            }
            vCard.DeleteCurrentStack();
        }
        if(thisWindow != null) {
            thisWindow.setVisible(true);
            thisWindow.setSize(new Dimension(200,100));
            thisWindow.setLocationRelativeTo(vCard);
        }
    }
}
