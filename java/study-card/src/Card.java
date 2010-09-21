/**
 *
 * @author Matt
 */
public class Card {
    
    private String frontChars;
    private String backChars;
    private boolean side;

    public Card() {
        side = true;    //When side is true, visible face is the front
    }

    public void SetFrontCharacters(String characters) {
        frontChars = characters;
    }

    public void SetBackCharacters(String characters) {
        backChars = characters;
    }

    public String GetFrontCharacters() {
        return frontChars;
    }

    public String GetBackCharacters() {
        return backChars;
    }

    public boolean Front() {
        return side;
    }

    public void Flip(boolean front) {
        side = front;
    }
}
