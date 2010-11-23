/**
 *
 * @author Matt
 */

/*
 * More of a structure class
 */
public class Card {

    /* Variable that dynamically holds the text on the front of the card */
    private String frontChars;
    /* Variable that dynamically holds the text on the back of the card */
    private String backChars;
    /* Variable that dynamically holds the position of the card (true = front side, false = back side) */
    private boolean side;

    /* Contructor
     * Side is front by default
     */
    public Card() {
        side = true;
    }

    /* public method to set the text on the front of the card to the given parameter */
    public void SetFrontCharacters(String frontCharacters) {
        frontChars = frontCharacters;
    }

    /* public method to set the text on the back of the card to the given parameter */
    public void SetBackCharacters(String backCharacters) {
        backChars = backCharacters;
    }

    /* public method to set the text on the front and back of the card to the given parameters */
    public void SetCharacters(String frontCharacters, String backCharacters) {
        frontChars = frontCharacters;
        backChars = backCharacters;
    }

    /* public method to get the text on the front of the card */
    public String GetFrontCharacters() {
        return frontChars;
    }

    /* public method to get the text on the back of the card */
    public String GetBackCharacters() {
        return backChars;
    }

    /* public method to get the current position of the card ( return true = front, return false = back ) */
    public boolean IsFrontSide() {
        return side;
    }

    /* public method to set the current position of the card to the given parameter */
    public void Flip(boolean frontSide) {
        side = frontSide;
    }
}
