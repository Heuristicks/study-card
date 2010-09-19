import java.awt.event.*;
import javax.swing.JWindow;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
/**
 *
 * @author Matt
 */

//Needs work - action event not being registered (or at least, no popup)
public class PopupAction implements ActionListener {

    private JOptionPane popup;
    private JFrame parent;
    private JFrame thisWindow;

    public PopupAction() {
        thisWindow = new JFrame();
        thisWindow.setLayout(new BoxLayout(thisWindow.getContentPane(),BoxLayout.Y_AXIS));
    }

    public void actionPerformed(ActionEvent e) {
        popup = new JOptionPane("Test");
        popup.setVisible(true);
    }
}
