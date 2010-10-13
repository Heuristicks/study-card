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
    private VCard vCard;
    private FileActionType fileActionType;

    public FileAction(VCard vcard, FileActionType fat) {
        fc = new JFileChooser("./");
        fc.setFileFilter(new TextFileFilter());
        fc.removeChoosableFileFilter(fc.getAcceptAllFileFilter());
        vCard = vcard;
        fileActionType = fat;
    }

    public void actionPerformed(ActionEvent e) {
        if(fileActionType.equals(FileActionType.OPEN)) {
            if(!vCard.GetStack().isEmpty()) {
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
            }
            int returnVal = fc.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    vCard.GetStack().Load(fc.getSelectedFile());
                    vCard.SetCurrentCard(vCard.GetStack(), 0);
                    vCard.Update();
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
                    vCard.GetStack().Save(fc.getSelectedFile());
                }
                catch (FileNotFoundException fnfe) {
                    return;
                }
                catch (IOException fnfe) {
                    return;
                }
            }
        }
    }
}
