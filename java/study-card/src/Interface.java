import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
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
            private JMenuItem saveStack;
            private JMenuItem loadStack;
            private JMenu currentStack;
        private JMenu cardMenu;
            private JMenuItem newCard;
            private JMenuItem editCard;
            private JMenuItem deleteCard;
        private JMenu timerMenu;
            private JMenuItem startTimer;
            private JMenuItem stopTimer;
        private JMenu optionMenu;
    private VCard cardDisplay;

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
                    saveStack = new JMenuItem("Save");
                    currentStack = new JMenu("Current Cards");
                cardMenu = new JMenu("Card");
                    newCard = new JMenuItem("New");
                    editCard = new JMenuItem("Edit");
                    deleteCard = new JMenuItem("Delete Current");
                timerMenu = new JMenu("Timer");
                    startTimer = new JMenuItem("Start");
                    stopTimer = new JMenuItem("Stop");
                optionMenu = new JMenu("Options");
        visualFrame = new JPanel();
            cardDisplay = new VCard();

        mainLayout = new BorderLayout();

        mainWindow.setLayout(mainLayout);

        mainWindow.add(menuBar,BorderLayout.PAGE_START);
        mainWindow.setJMenuBar(menuBar);
        mainWindow.add(visualFrame,BorderLayout.CENTER);

            menuBar.add(stackMenu);
                stackMenu.add(newStack);
                stackMenu.add(saveStack);
                    saveStack.addActionListener(new FileAction(cardDisplay.GetStack(),cardDisplay, FileActionType.SAVE));
                stackMenu.add(loadStack);
                    loadStack.addActionListener(new FileAction(cardDisplay.GetStack(),cardDisplay, FileActionType.OPEN));
                stackMenu.add(currentStack);
            menuBar.add(cardMenu);
                cardMenu.add(newCard);
                    newCard.addActionListener(new PopupAction(PopupType.CARD_CREATE,cardDisplay));
                cardMenu.add(editCard);
                    editCard.addActionListener(new PopupAction(PopupType.CARD_EDIT,cardDisplay));
                cardMenu.add(deleteCard);
            menuBar.add(timerMenu);
                timerMenu.add(startTimer);
                timerMenu.add(stopTimer);
            menuBar.add(optionMenu);

        mainLayout = new BorderLayout();
        visualFrame.setLayout(mainLayout);
        
        visualFrame.add(cardDisplay,BorderLayout.CENTER);
    }


    private void setUpLayout() {
        Dimension d = new Dimension(640,480);
        mainWindow.setPreferredSize(d);
        mainWindow.setMinimumSize(d);
        mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWindow.addComponentListener(new ResizeListener(mainWindow));
        mainWindow.addKeyListener(new KeyAction(cardDisplay));
    }

    public boolean run() {
        mainWindow.pack();
        mainWindow.setVisible(true);
        return true;
    }
}
