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

enum FileActionType { SAVE, OPEN };

public class FileAction implements ActionListener {

    private JFileChooser fc;
    private Stack stackToUse;
    private VCard vCard;
    private FileActionType fileActionType;

    public FileAction(Stack stack, VCard vcard, FileActionType fat) {
        fc = new JFileChooser();
        stackToUse = stack;
        vCard = vcard;
        fileActionType = fat;
    }

    public void actionPerformed(ActionEvent e) {
        if(fileActionType.equals(FileActionType.OPEN)) {
            int returnVal = fc.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    stackToUse.Load(fc.getSelectedFile());
                }
                catch (FileNotFoundException fnfe) {
                    return;
                }
                catch (IOException fnfe) {
                    return;
                }
            }
        }
        else {
            int returnVal = fc.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
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

        vCard.Update();
    }
}
