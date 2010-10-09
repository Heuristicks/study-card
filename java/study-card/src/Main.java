/**
 *
 * @author Matt
 */

public class Main {

    private static Interface i = new Interface();

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if(!i.run())
                    return;
            }
        });        
    }

}
