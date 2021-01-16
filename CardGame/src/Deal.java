import java.util.ArrayList;
import java.util.Scanner;

public class Deal {

    private static ArrayList<Card> cardsOnTable = new ArrayList<>();
    private static Pot pot = new Pot(0);
    private static Profile my = new Profile();
    private static Profile pc = new Profile();
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Card> cards = Deck.myDeck();

    private static void printCard(Card card) {
        int a = card.getValue();
        switch (a) {
            case 1:
                System.out.println("A" + card.getSymbolForm());
                break;
            case 11:
                System.out.println("J" + card.getSymbolForm());
                break;
            case 12:
                System.out.println("Q" + card.getSymbolForm());
                break;
            case 13:
                System.out.println("K" + card.getSymbolForm());
                break;
            default:
                System.out.println(a + "" + card.getSymbolForm());
        }
    }

    public static Card chooseRandomCard() {
        int symbol = (int) (Math.random() * 4) + 1;
        int num = (int) (Math.random() * 13) + 1;
        Card answer = new Card(chooseColor(symbol), num);
        cards.remove(answer);
        return answer;
    }

    public static Symbols chooseColor(int i) {
        switch (i) {
            case 1:
                return Symbols.CLUBS;
            case 2:
                return Symbols.HEARTS;
            case 3:
                return Symbols.DIAMONDS;
            case 4:
                return Symbols.SPADES;
        }
        return null;
    }

    public static void bet(Profile my, Profile gep, Pot pot) {
        System.out.println("How Much?    Balance: " + my.getBalance());
        int bet = intBeolvasas();
        my.setBalance(my.getBalance() - bet);
        gep.setBalance(gep.getBalance() - bet);
        pot.setPot(pot.getPot() + bet * 2);
        System.out.println("New Balance: " + my.getBalance());
        System.out.println("Pot: " + pot.getPot());
    }

    public static void moneyUpload() {
        System.out.println("Add money to your balance: (The AI will start with the same amount)");
        int balance = intBeolvasas();
        my.setBalance(balance);
        pc.setBalance(balance);
    }

    public static void printCards(ArrayList<Card> myCards) {
        for (Card myCard : myCards) {
            printCard(myCard);
        }
        System.out.println();
    }

    public static void runGame() {
        ArrayList<Card> myCards = new ArrayList<Card>();             //Az en lapjaim
        ArrayList<Card> gepCards = new ArrayList<Card>();             //A gep lapjai

        for (int i = 0; i < 2; i++) { //TODO how many player
            Card card1 = chooseRandomCard();
            Card card2 = chooseRandomCard();
            myCards.add(card1);
            gepCards.add(card2);
        }

        my.setHand(myCards);
        pc.setHand(gepCards);
        System.out.println("My cards: ");
        printCards(myCards);
        askingMe();

//dealing flop, adding to cardsOnTable
        chooseRandomCard(); //burning
        for (int i = 0; i < 3; i++) {
            cardsOnTable.add(chooseRandomCard());
        }
        System.out.println("A flop: ");
        printCards(cardsOnTable);
        System.out.println("My cards: ");

        printCards(myCards);
        askingMe();
//Dealing the Turn
        chooseRandomCard();

        cardsOnTable.add(chooseRandomCard());
        System.out.println("Turn: ");
        printCards(cardsOnTable);
        System.out.println("My cards: ");

        printCards(myCards);
        askingMe();
//Dealing the River
        chooseRandomCard();
        cardsOnTable.add(chooseRandomCard());
        System.out.println("River: ");
        printCards(cardsOnTable);

        System.out.println("My cards: ");
        printCards(myCards);
        askingMe();
    }

    private static String stringBeolvasas() {
        String answer = null;
        do {
            answer = sc.next();
            return answer;
        } while (!answer.equals("r"));

    }

    private static void askingMe() {
        System.out.println("C = Check , R = Raise , F = Fold");
        String c = stringBeolvasas();
        if (c.equals("R") || c.equals("r")) {
            bet(my, pc, pot);
        }
        if (c.equals("F") || c.equals("f")) {
            System.out.println("New Deal? Y/N");
            c = stringBeolvasas();
            if (c.equals("N") || c.equals("n")) {
                System.exit(0);
            }
            if (c.equals("Y") || c.equals("y")) {
                runGame();
            }
        }

    }

    private static int intBeolvasas() {
        return Integer.parseInt(stringBeolvasas());
    }
}




