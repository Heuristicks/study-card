import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

/**
 *
 * @author Matt
 */
public class VCard extends JPanel {

    private Card thisCard;
    public Stack theseCards;

    public VCard() {
        thisCard = new Card();
        theseCards.AddToStack(thisCard);
    }

    public Card GetCard() {
        return thisCard;
    }
    
    public void Update() {
        repaint();
    }

    public void NewCard() {
        //Need a save to stack function here
        thisCard = new Card();
    }

    private void paintBackground(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    private void paintCard(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(getX()*(3/2), getY()*(3/2), getWidth()/2, getHeight()/2);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.setColor(Color.black);
        String text = null;
        if(thisCard.Front()) {
            text = thisCard.GetFrontCharacters();  
        }
        else {
            text =  thisCard.GetBackCharacters();
        }
        if(text != null)
            g.drawString(text, getX() + 170, getY()+ 110);
        g.setColor(null);
    }
    
    @Override

    public void paint(Graphics g) {
        paintBackground(g);
        paintCard(g);
    }
}
