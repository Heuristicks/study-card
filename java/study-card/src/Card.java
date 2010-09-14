import java.lang.Object;
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

    public void Flip(boolean front) {
        side = front;
    }

}
