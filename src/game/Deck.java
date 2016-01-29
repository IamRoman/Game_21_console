package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> deck; //колода

    public Deck() {
        this.deck = new ArrayList<Card>();
        InitDeck(36);
    }

    public  void InitDeck(int sizeDeck){
        int [] arrayPoint_36 = {2,3,4,6,7,8,9,10,11,2,3,4,6,7,8,9,10,11,2,3,4,6,7,8,9,10,11,2,3,4,6,7,8,9,10,11};
        for(int i=0; i<sizeDeck; i++)
        {
            Card cd1 = new Card((i%9), (i%4), arrayPoint_36[i]);
            deck.add(cd1);
        }
    }

    public void ShuffleDeck(){
        Collections.shuffle(deck);
    }

    public Card Extract()
    {
        Card tmp = deck.get(deck.size()-1);
        deck.remove(deck.size()-1);
        return tmp;
    }

    public boolean isEmptyDeck()
    {
        return deck.isEmpty();
    }

    public int SizeDeck()
    {
        return deck.size();
    }
    /// <summary>
    /// Очистка колоды
    /// </summary>
    public void ClearDeck()
    {
        deck.clear();
    }

    public void ShowDeck()
    {
        for (int i = 0; i < deck.size(); i++)
            System.out.println("Значение карты - " + deck.get(i).getValue() + "\tМасть карты - " + deck.get(i).getSuit() + "\t\tОчки карты - " + deck.get(i).getPoint());
    }
}
