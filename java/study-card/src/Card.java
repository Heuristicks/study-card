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

    public void SetFrontCharacters(String frontCharacters) {
        frontChars = frontCharacters;
    }

    public void SetBackCharacters(String backCharacters) {
        backChars = backCharacters;
    }

    public void SetCharacters(String frontCharacters, String backCharacters) {
        frontChars = frontCharacters;
        backChars = backCharacters;
    }

    public String GetFrontCharacters() {
        return frontChars;
    }

    public String GetBackCharacters() {
        return backChars;
    }

    public boolean IsFrontSide() {
        return side;
    }

    public void Flip(boolean frontSide) {
        side = frontSide;
    }
}
