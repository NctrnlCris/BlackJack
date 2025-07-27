package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int deckQuantity = 52;
    private static final int cardQuantity = 4;
    private List<Card> deck;

    public Deck() {
        generateDeck();
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);

    }

    public void generateDeck() {
        deck = new ArrayList<Card>();
        for (int i = 0; i < cardQuantity; i++) {
            deck.add(new Card(Card_Enum.ACE, 1));
            deck.add(new Card(Card_Enum.TWO, 2));
            deck.add(new Card(Card_Enum.THREE, 3));
            deck.add(new Card(Card_Enum.FOUR, 4));
            deck.add(new Card(Card_Enum.FIVE, 5));
            deck.add(new Card(Card_Enum.SIX, 6));
            deck.add(new Card(Card_Enum.SEVEN, 7));
            deck.add(new Card(Card_Enum.EIGHT, 8));
            deck.add(new Card(Card_Enum.NINE, 9));
            deck.add(new Card(Card_Enum.TEN, 10));
            deck.add(new Card(Card_Enum.JACK, 10));
            deck.add(new Card(Card_Enum.QUEEN, 10));
            deck.add(new Card(Card_Enum.KING, 10));

        }
    }

    public void removeCard() {
        deck.remove(0);
    }


}
