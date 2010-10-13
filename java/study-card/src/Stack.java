import java.util.ArrayList;
import java.util.ListIterator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
/**
 *
 * @author Matt
 */
public class Stack extends ArrayList<Card> {

    public Stack() {}

    public void Save(File f) throws FileNotFoundException, IOException {
        FileWriter fileWriter = new FileWriter(f);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ListIterator itr = this.listIterator();
        ArrayList<String[]> cardsText = new ArrayList<String[]>();
        while(itr.hasNext()) {
            Card currentItr = ((Card)itr.next());
            String[] cardText = { currentItr.GetFrontCharacters(), currentItr.GetBackCharacters() };
            cardsText.add(cardText);
        }
        for(int i = 0; i < cardsText.size(); ++i) {
            bufferedWriter.write(cardsText.get(i)[0] + "$" + cardsText.get(i)[1] + "@");
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    public void Load(File f) throws FileNotFoundException, IOException {
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
        ArrayList<String[]> cardText = new ArrayList<String[]>();
        for(int i = 0; i < fileText.size(); ++i) {
            cardsText.add(ReturnCardCharacters(fileText.get(i),true));
            for(int a = 0; a < cardsText.get(i).length; ++a) {
                 cardText.add(ReturnCardCharacters(cardsText.get(i)[a],false));
            }
        }
        SetSize(cardText.size());
        ListIterator itr = listIterator();
        while(itr.hasNext()) {
            for(int i = 0; i < cardText.size(); ++i) {
                String front =  cardText.get(i)[0];
                String back = cardText.get(i)[1];
                ((Card)(itr.next())).SetCharacters(front,back);
            }
        }
    }

    private String[] ReturnCardCharacters(String charsToParse, boolean wholeLine) {
        if(wholeLine)
            return charsToParse.split("[@]");
        else
            return charsToParse.split("[$]");
    }

    public void SetSize(int size) {
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
