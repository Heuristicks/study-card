import java.awt.event.*;
import javax.swing.*;


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

    public FileAction(VCard vcard, FileActionType fat) {
        fc = new JFileChooser();
        fc.setFileFilter(new TextFileFilter());
        stackToUse = vcard.GetStack();
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
            int returnVal = fc.showSaveDialog(null);
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
