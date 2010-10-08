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
        //Scanner fileScanner = new Scanner(f);
        this.clear();
        FileReader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> fileText = new ArrayList<String>();
        String text;
        while((text = bufferedReader.readLine()) != null) {
            fileText.add(text);
        }
        bufferedReader.close();
        fileReader.close();
        ArrayList<String[]> cardsText = new ArrayList<String[]>();
        for(int i = 0; i < fileText.size(); ++i) {
            cardsText.add(ReturnCardCharacters(fileText.get(i),true));
        }
        SetSize(cardsText.size());
        ListIterator itr = listIterator();
        int placeholder = 0;
        while(itr.hasNext()) {
             for(int i = 0; i < cardsText.get(placeholder).length; ++i) {
                 String[] cardText = ReturnCardCharacters(cardsText.get(placeholder)[i],false);
                 String front =  cardText[0];
                 String back = cardText[1];
                 ((Card)(itr.next())).SetCharacters(front,back);
             }
             ++placeholder;
        }
    }

    private String[] ReturnCardCharacters(String charsToParse, boolean wholeLine) {
        if(wholeLine)
            return charsToParse.split("[;]");
        else
            return charsToParse.split("[,]");
    }

    private void SetSize(int size) {
        int thisSize = size();
        if(size > thisSize) {
            int cardsToAdd = size - thisSize;
            for(int i = 0; i < cardsToAdd; ++i) {
                add(new Card());
            }
            return;
        }
        return;
    }
}
