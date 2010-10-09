import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;

import java.lang.IndexOutOfBoundsException;


/**
 *
 * @author Matt
 */
public class VCard extends JPanel {

    private Card thisCard;
    private Stack thisStack;

    public VCard() {
        thisStack = new Stack();
        if(thisStack.isEmpty()) {}
        else  {
            int index = thisStack.indexOf(thisCard);
            try {
                thisCard = thisStack.get(index+1);
            }
            catch (IndexOutOfBoundsException e) {
                thisCard = thisStack.get(0);
            }
        }
    }

    public Stack GetStack() {
        return thisStack;
    }

    public void NextCard() {
        if(thisStack.isEmpty()) {
            return;
        }
        else {
            int index = thisStack.indexOf(thisCard);
            try {
                thisCard = thisStack.get(index+1);
            }
            catch (IndexOutOfBoundsException e) {
                thisCard = thisStack.get(0);
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
        thisStack.add(new Card());
        thisCard = thisStack.get(thisStack.size()-1);
        Update();
    }

    private void paintBackground(Graphics g) {
        g.setColor(new Color(50,50,50));
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    private void paintCard(Graphics g) {
        Font font = new Font("Verdana", Font.PLAIN, getFontSize());
        FontMetrics fm = g.getFontMetrics(font);
        g.setColor(Color.white);
        g.fillRect(getX()+(getWidth()/4), getY()+(getHeight()/4), getWidth()/2, getHeight()/2);
        g.setFont(font);
        g.setColor(Color.black);
        String text = null;
        if(thisCard != null) {
            if(thisCard.IsFrontSide()) {
                text = thisCard.GetFrontCharacters();
            }
            else {
                text =  thisCard.GetBackCharacters();
            }
        }
        if(text != null) {
            int textWidth = fm.stringWidth(text);
            if(textWidth <= (getWidth()/4 - 20)) {
                g.drawString(text, (getWidth()/2)-(textWidth/2), getHeight()/2);
            }
            else {
                if(textWidth <= getWidth() - 40)
                    g.drawString(text, getX()+(getWidth()/4)+20,getHeight()/2);
            }
        }
        g.setColor(null);
    }

    private int getFontSize() {
        return (int)(getWidth()/25.6);
    }

    @Override
    public void paint(Graphics g) {
        paintBackground(g);
        paintCard(g);
    }
}
