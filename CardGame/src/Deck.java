import java.util.ArrayList;
import java.util.HashMap;

public class Deck {


    public static ArrayList<Card> myDeck() {
        ArrayList<Card> myHashDeck = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            if (i < 13) {
                myHashDeck.add(new Card(Symbols.SPADES, i));
            } else if (i < 26) {
                myHashDeck.add(new Card(Symbols.DIAMONDS, i));
            } else if (i < 39) {
                myHashDeck.add(new Card(Symbols.CLUBS, i));
            } else {
                myHashDeck.add(new Card(Symbols.HEARTS, i));
            }
        }
        return myHashDeck;
    }
}
