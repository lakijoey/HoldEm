import java.util.ArrayList;

public class Deck {
    public static ArrayList<Integer> myDeck(){
        ArrayList<Integer> cards = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            cards.add(i);
        }
        return cards;
    }
}
