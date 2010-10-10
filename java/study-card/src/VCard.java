import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
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
        int index = (thisCard != null) ? thisStack.indexOf(thisCard):0;
        thisStack.add(index,new Card());
        thisCard = thisStack.get(index);
        Update();
    }

    public void DeleteCurrentCard() {
        int index = thisStack.indexOf(thisCard);
        thisStack.remove(index);
        thisCard = null;
        for(int i = 0; i < thisStack.size(); ++i) {
            if((thisCard = thisStack.get(index-i)) != null)
                break;
        }
        Update();
    }

    public void DeleteCurrentStack() {
        thisStack.clear();
        thisCard = null;
        Update();
    }

    private void paintBackground(Graphics g) {
        g.setColor(new Color(50,50,50));
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    private void paintCard(Graphics g) {
        Font font = new Font("Arial", Font.PLAIN, getFontSize());
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
                int numberOfLines = getNumberOfLines(textWidth,getWidth()/2,20);
                String[] splitText = splitTextForWidth(text,getWidth()/2,20,numberOfLines, fm);
                for(int i = 0; i < splitText.length; ++i) {
                    int x = getX()+(getWidth()/4)+20;
                    int y = getHeight()/2 + fm.getHeight()*i;
                    g.drawString(splitText[i],x,y);
                }
            }
        }
        g.setColor(null);
    }

    private int getFontSize() {
        return (int)(getWidth()/25.6);
    }

    private int getYCoordForString(int value, int numberOfLines) {
        int height = getHeight();
        int numerator = value + 1;
        int denominator = numberOfLines + 1;
        int val1 = height*numerator;
        int val2 = val1/denominator;
        int returnValue = val2;
        return returnValue;
    }

    private int getNumberOfLines(int textWidth, int containerWidth, int margin) {
        int lineCount = 0;
        while(textWidth > lineCount*(containerWidth - (margin*2))) {
            ++lineCount;
        }
        return lineCount;
    }

    private String[] splitTextForWidth(String text, int containerWidth, int margin, int numberOfLines, FontMetrics fm) {
        ArrayList<String> words = new ArrayList<String>();
        char[] chars = text.toCharArray();
        char[] extraChars = chars;
        if(numberOfLines > 1) {
            int width = 0;
            int beginIndex = 0;
            for(int i = 0; i < chars.length; ++i) {
                width += fm.charWidth(chars[i]);
                if(width > containerWidth - (margin*2)) {
                    words.add(text.substring(beginIndex,i-1));
                    width = 0;
                    beginIndex = i - 1;
                    extraChars = null;
                }
                else {
                    extraChars = (text.substring(beginIndex,i+1).toCharArray());
                }
            }
        }
        if(extraChars != null)
            words.add(new String(extraChars));
        String[] arrayToReturn = new String[words.size()];
        words.toArray(arrayToReturn);
        return arrayToReturn;
    }

    @Override
    public void paint(Graphics g) {
        paintBackground(g);
        paintCard(g);
    }
}
