import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import java.lang.IndexOutOfBoundsException;


/**
 *
 * @author Matt
 */
public class VCard extends JPanel {

    private Card thisCard;
    private Stack theseCards;

    public VCard() {
        theseCards = new Stack();
        if(theseCards.isEmpty()) {
            thisCard = new Card();
            theseCards.add(thisCard);
        }
        else {
            int index = theseCards.indexOf(thisCard);
            try {
                thisCard = theseCards.get(index+1);
            }
            catch (IndexOutOfBoundsException e) {
                thisCard = theseCards.get(0);
            }
        }
    }

    public Stack GetStack() {
        return theseCards;
    }

    public void NextCard() {
        if(theseCards.isEmpty()) {
            thisCard = new Card();
            theseCards.add(thisCard);
        }
        else {
            int index = theseCards.indexOf(thisCard);
            try {
                thisCard = theseCards.get(index+1);
            }
            catch (IndexOutOfBoundsException e) {
                thisCard = theseCards.get(0);
            }
        }
        Update();
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
        theseCards.add(thisCard);
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
