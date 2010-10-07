import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Matt
 */
public class FileAction implements ActionListener {

    private JFileChooser fc;
    private Stack stackToLoadTo;
    private VCard vCard;

    public FileAction(Stack stack, VCard vcard) {
        fc = new JFileChooser();
        stackToLoadTo = stack;
        vCard = vcard;
    }

    public void actionPerformed(ActionEvent e) {
        int returnVal = fc.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                stackToLoadTo.Load(fc.getSelectedFile());
            }
            catch (FileNotFoundException fnfe) {
                return;
            }
            catch (IOException fnfe) {
                return;
            }
        }
        vCard.Update();
    }
}
