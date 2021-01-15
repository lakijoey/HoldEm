import java.util.*;

public class Deal {


    private static myProfile my = new myProfile();
    private static gepProfile pc = new gepProfile();
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Card> cards = Deck.myDeck();

    private static void printCard(Card card) {
        int a = card.getValue();
        switch (a) {
            case 1:
                System.out.println(card.getSymbol() + " - ACE ");
                break;
            case 11:
                System.out.println(card.getSymbol() + " - Jack ");
                break;
            case 12:
                System.out.println(card.getSymbol() + " - Queen ");
                break;
            case 13:
                System.out.println(card.getSymbol() + " - King ");
                break;
            default:
                System.out.println(card.getSymbol() + " - " + a);
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

    public static void bet(myProfile my, gepProfile gep, Pot pot) {
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

    public static void runGame() {
        ArrayList<Card> myCards = new ArrayList<Card>();             //Az en lapjaim
        ArrayList<Card> gepCards = new ArrayList<Card>();             //A gep lapjai

        for (int i = 0; i < 2; i++) { //TODO how many player
            Card card1 = chooseRandomCard();
            Card card2 = chooseRandomCard();
            myCards.add(card1);
            gepCards.add(card2);
        }

        my.setMyHand(myCards);
        pc.setGepHand(gepCards);
        Pot pot = new Pot(0);
        System.out.println("Your Cards: ");
        for (Card myCard : myCards) {
            printCard(myCard);
        }
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
        //dealing flop, adding to cardsOnTable, and removing from deck
        ArrayList<Card> cardsOnTable = new ArrayList<>();
        chooseRandomCard();
        for (int i = 0; i < 3; i++) {
            //dealing flop, adding to cardsOnTable, and removing from deck
            cardsOnTable.add(chooseRandomCard());
        }
        System.out.println("A flop: ");
        for (Card card : cardsOnTable) {
            printCard(card);
        }
        System.out.println("Your Cards: ");
        for (Card myCard : myCards) {
            printCard(myCard);
        }
        System.out.println("C = Check , R = Raise , F = Fold");
        c = stringBeolvasas();
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

        //Dealing the Turn
        chooseRandomCard();

        cardsOnTable.add(chooseRandomCard());
        System.out.println("Turn: ");
        for (Card card : cardsOnTable) {
            printCard(card);
        }

        System.out.println("Your Cards: ");
        for (Card myCard : myCards) {
            printCard(myCard);
        }
        System.out.println("C = Check , R = Raise , F = Fold");
        c = stringBeolvasas();
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
        //Dealing the River
        chooseRandomCard();

        cardsOnTable.add(chooseRandomCard());
        System.out.println("River: ");
        for (Card card : cardsOnTable) {
            printCard(card);
        }

        System.out.println("Your Cards: ");
        for (Card myCard : myCards) {
            printCard(myCard);
        }
        System.out.println("C = Check , R = Raise , F = Fold");
        Scanner scd = new Scanner(System.in);
        c = scd.next();
        if (c.equals("R") || c.equals("r")) {
            bet(my, pc, pot);
        }
        if (c.equals("F") || c.equals("f")) {
            System.out.println("New Deal? Y/N");
            c = scd.next();
            if (c.equals("N") || c.equals("n")) {
                System.exit(0);
            }
            if (c.equals("Y") || c.equals("y")) {
                runGame();
            }
        }
        scd.close();
    }

    private static String stringBeolvasas() {
        String answer = null;
        do {
            answer = sc.next();
            return answer;
        } while (!answer.equals("r"));

    }

    private static int intBeolvasas() {
        return Integer.parseInt(stringBeolvasas());
    }
}




