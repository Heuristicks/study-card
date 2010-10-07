import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.regex.Pattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author Matt
 */
public class Stack extends ArrayList<Card> {

    public Stack() {}

    public void Save(File f) throws FileNotFoundException {
        ListIterator itr = this.listIterator();
        while(itr.hasNext()) {

        }
    }

    public void Load(File f) throws FileNotFoundException, IOException {
        ListIterator itr = this.listIterator();

        //Scanner fileScanner = new Scanner(f);
        FileReader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while(itr.hasNext()) {
            String text = bufferedReader.readLine();
            if(!text.isEmpty()) {
                String[] cardText = ReturnCardCharacters(text);
                String front =  cardText[0];
                String back = cardText[1];
                ((Card)itr.next()).SetCharacters(front,back);
            }
            else {
                break;
            }
        }
    }

    private String[] ReturnCardCharacters(String charsToParse) {
        return charsToParse.split("[,;]");
    }
}
