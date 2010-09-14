import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 *
 * @author Matt
 */
public class Interface {

    private JFrame mainWindow;
    private JPanel toolFrame;
    private JPanel visualFrame;

    private BorderLayout mainLayout;
    private GridLayout toolLayout;

    private JButton stackButton;
    private JButton cardButton;
    private JButton timerButton;
    private JButton optionButton;

    public Interface() {
        setUpComponents();
        setUpLayout();
    }

    private void setUpComponents() {
        mainWindow = new JFrame();
        toolFrame = new JPanel();
        visualFrame = new JPanel();

        mainLayout = new BorderLayout();
        toolLayout = new GridLayout(0,1);

        stackButton = new JButton("Stack");
        cardButton = new JButton("Card");
        timerButton = new JButton("Timed");
        optionButton = new JButton("Options");

        mainWindow.setLayout(mainLayout);
        mainWindow.add(toolFrame,BorderLayout.WEST);
        mainWindow.add(visualFrame,BorderLayout.EAST);

        toolFrame.setLayout(toolLayout);


        toolFrame.add(stackButton);
        toolFrame.add(cardButton);
        toolFrame.add(timerButton);
        toolFrame.add(optionButton);
    }

    private void setUpLayout() {
        mainWindow.setLocationRelativeTo(null);

        toolFrame.setMaximumSize(new Dimension(100,toolFrame.getY()));
    }

    public void run() {
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}
