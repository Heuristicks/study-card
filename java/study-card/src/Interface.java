import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.Dimension;
/**
 *
 * @author Matt
 */
public class Interface {

    private JFrame mainWindow;
    private JPanel visualFrame;

    private BorderLayout mainLayout;

    private JMenuBar menuBar;
        private JMenu stackMenu;
            private JMenuItem newStack;
            private JMenuItem loadStack;
            private JMenuItem currentStack;
        private JMenu cardMenu;
            private JMenuItem newCard;
            private JMenuItem deleteCard;
        private JMenu timerMenu;
            private JMenuItem startTimer;
            private JMenuItem stopTimer;
        private JMenu optionMenu;

    public Interface() {
        setUpComponents();
        setUpLayout();
    }

    private void setUpComponents() {
        mainWindow = new JFrame("Flashflip");
            menuBar = new JMenuBar();
                stackMenu = new JMenu("Stack");
                    newStack = new JMenuItem("New");
                    loadStack = new JMenuItem("Load");
                    currentStack = new JMenuItem("Current Cards");
                cardMenu = new JMenu("Card");
                    newCard = new JMenuItem("New");
                    deleteCard = new JMenuItem("Delete Current");
                timerMenu = new JMenu("Timer");
                    startTimer = new JMenuItem("Start");
                    stopTimer = new JMenuItem("Stop");
                optionMenu = new JMenu("Options");
        visualFrame = new JPanel();

        mainLayout = new BorderLayout();

        mainWindow.setLayout(mainLayout);

        mainWindow.add(menuBar,BorderLayout.PAGE_START);
        mainWindow.add(visualFrame,BorderLayout.CENTER);

            menuBar.add(stackMenu);
                stackMenu.add(newStack);
                stackMenu.add(loadStack);
                stackMenu.add(currentStack);
            menuBar.add(cardMenu);
                cardMenu.add(newCard);
                cardMenu.add(deleteCard);
            menuBar.add(timerMenu);
                timerMenu.add(startTimer);
                timerMenu.add(stopTimer);
            menuBar.add(optionMenu);
    }

    private void setUpLayout() {
        mainWindow.setPreferredSize(new Dimension(640,480));
    }

    public void run() {
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}
