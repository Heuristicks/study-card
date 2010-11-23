import java.awt.AlphaComposite;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Matt
 */


public class VCard extends JPanel {

    private Card thisCard;
    private Stack thisStack;

    private boolean currentCardSide;

    private String userFont;
    private boolean randomOrder;
    private Random r;

    private JLabel currentCardLabel;

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
        randomOrder = false;
        r = new Random();
        currentCardSide = true;
        currentCardLabel = new JLabel();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHEAST;
        this.add(currentCardLabel,c);
    }

    public Stack GetStack() {
        return thisStack;
    }

    public void SetCurrentStack(Stack stack) {
        thisStack = stack;
        Update();
    }

    public void NextCard(boolean forward) {
        if(thisStack.isEmpty()) {
            return;
        }
        else {
            if(!randomOrder) {
                int index = thisStack.indexOf(thisCard);
                try {
                    thisCard = thisStack.get(index+(forward ? 1:-1));
                }
                catch (IndexOutOfBoundsException e) {
                    thisCard = forward ? thisStack.get(0):thisStack.get(thisStack.size()-1);
                }
            }
            else {
                int index = r.nextInt(thisStack.size());
                thisCard = thisStack.get(index);
            }
        }
        Update();
    }

    public Card GetCard() {
        return thisCard;
    }

    public boolean GetCurrentCardSide() {
        return currentCardSide;
    }

    public void SetCurrentCardSide(boolean front) {
        for(int i = 0; i < thisStack.size(); ++i) {
            thisStack.get(i).Flip(front);
        }
        currentCardSide = front;
        Update();
    }

    public void SetCurrentCard(Stack stack, int index) {
        if(!stack.isEmpty()) {
            thisCard = stack.get(index);
        }
    }

    public void SetOrder(boolean random) { //True = random order, else sequential according to file
        randomOrder = random;
    }
    
    public void Update() {
        int size = thisStack.size();
        int index = thisStack.indexOf(thisCard) + 1;
        repaint();
        currentCardLabel.setText(Integer.toString(size));
    }

    public void NewCard() {
        int index = (thisCard != null) ? thisStack.indexOf(thisCard)+1:0;
        Card newCard = new Card();
        if(index > thisStack.size())
            thisStack.add(newCard);
        else
            thisStack.add(index,newCard);
        thisCard = thisStack.get(index);
        Update();
    }

    public void DeleteCurrentCard() {
        if(thisCard != null) {
            int index = thisStack.indexOf(thisCard);
            thisStack.remove(index);
            thisCard = null;
            for(int i = 0; i < thisStack.size(); ++i) {
                if((thisCard = thisStack.get(index-i)) != null)
                    break;
            }
        }
        Update();
    }

    public void DeleteCurrentStack() {
        thisStack.clear();
        thisCard = null;
        Update();
    }

    public void SetUserFont(String fontName) {
        userFont = fontName;
        Update();
    }

    private void paintBackground(Graphics g) {
        g.setColor(new Color(50,50,50));
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    private void paintCard(Graphics g) {
        if(thisCard == null)
            return;

        boolean sideOfCard = thisCard.IsFrontSide();

        drawCard(g,sideOfCard);
        drawText(g,sideOfCard);
        g.setColor(null);
    }

    private void drawCard(Graphics g, boolean sideOfCard) {
        int shadow = sideOfCard ? (getWidth()/128):-(getWidth()/128);
        g.setColor(Color.black);
        g.fillRect(getX()+getCardX()+shadow, getY()+getCardY()+shadow, getCardWidth(), getCardHeight());
        Rectangle card = new Rectangle(getX()+getCardX(), getY()+getCardY(), getCardWidth(), getCardHeight());

        /*Graphics2D g2 = (Graphics2D) g;
        //Composite c = g2.getComposite();
        //g2.setComposite(AlphaComposite.SrcOver.derive(1.0f));
        g.drawImage(createDropShadow(card,shadow), getX()+getCardX()+shadow, getY()+getCardY()+shadow, null);*/
        g.setColor(Color.white);
        fillRect(g,card);
    }

    private void fillRect(Graphics g, Rectangle r) {
        g.fillRect(r.x,r.y, r.width, r.height);
    }

    private void drawText(Graphics g, boolean sideOfCard) {
        Graphics2D g2d = ((Graphics2D)g);
        RenderingHints antiAlias = g2d.getRenderingHints();
        antiAlias.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Font font = (new Font((userFont != null) ? (userFont):("Arial"), Font.PLAIN, getFontSize()));
        FontMetrics fm = g2d.getFontMetrics(font);
        g2d.setFont(font);
        g2d.setColor(Color.black);
        String text = null;
        if(sideOfCard) {
            text = thisCard.GetFrontCharacters();
        }
        else {
            text =  thisCard.GetBackCharacters();
        }
        if(text != null) {
            int textWidth = fm.stringWidth(text);
            if(textWidth <= (getCardX() - 20)) {
                g2d.drawString(text, (getWidth()/2)-(textWidth/2), getHeight()/2);
            }
            else {
                int numberOfLines = getNumberOfLines(textWidth,getCardWidth(),20);
                String[] splitText = splitTextForWidth(text,getCardWidth(),20,numberOfLines, fm);
                for(int i = 0; i < splitText.length; ++i) {
                    int x = getX()+(getCardX())+20;
                    int y = getHeight()/2 + fm.getHeight()*i;
                    g2d.drawString(splitText[i],x,y);
                }
            }
        }

    }

    private int getCardX() {
        return getWidth()/4;
    }

    private int getCardY() {
        return getHeight()/4;
    }

    private int getCardWidth() {
        return getWidth()/2;
    }

    private int getCardHeight() {
        return getHeight()/2;
    }

    private int getFontSize() {
        return (int)(getWidth()/25.6);
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
        super.paint(g);
        paintBackground(g);
        paintCard(g);
    }
}
