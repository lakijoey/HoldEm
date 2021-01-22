package service;

import models.Card;
import models.Deck;
import models.Profile;
import models.Symbols;

import java.util.ArrayList;
import java.util.Scanner;

public class Deal {
    public static ArrayList<Card> cardsOnTable = new ArrayList<>();
    private static Integer pot = 0;
    private static Profile my = new Profile();
    private static Profile pc1 = new Profile();
    private static Profile pc2 = new Profile();
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Card> cards = Deck.myDeck();
    public static ArrayList<Profile> profiles = new ArrayList<>();

    private static void printTable() {
        try {
            System.out.println("______________________________________");
            System.out.println("  " + my.getName() + "                  " + pc1.getName());
            System.out.println("  " + my.getBalance() + "                    " + pc1.getBalance());
            System.out.print("  ");
            printCards(my.getHand());
            System.out.print("                 ");
            printCards(pc1.getHand());
            System.out.println("\n");
            System.out.print("         ");
            printCards(cardsOnTable);
            System.out.println("\n          Pot: " + pot + "              \n\n");      //    \n  --> új sor
            System.out.println("  " + pc2.getName() + "                  ");
            System.out.println("  " + pc2.getBalance() + "                  ");
            System.out.print("  ");
            printCards(pc2.getHand());
            System.out.println("\n______________________________________");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printCard(Card card) {
        int a = card.getValue();
        switch (a) {
            case 1 -> System.out.print("A" + card.getSymbolForm() + " ");
            case 11 -> System.out.print("J" + card.getSymbolForm() + " ");
            case 12 -> System.out.print("Q" + card.getSymbolForm() + " ");
            case 13 -> System.out.print("K" + card.getSymbolForm() + " ");
            default -> System.out.print(a + "" + card.getSymbolForm() + " ");
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
        return switch (i) {
            case 1 -> Symbols.CLUBS;
            case 2 -> Symbols.HEARTS;
            case 3 -> Symbols.DIAMONDS;
            case 4 -> Symbols.SPADES;
            default -> null;
        };
    }

    public static void bet() {
        System.out.println("How Much?    Balance: " + my.getBalance());
        int bet = intBeolvasas();
        for (Profile profile : profiles) {
            profile.setBalance(profile.getBalance() - bet);
        }
        pot += bet * profiles.size();
        System.out.println("New Balance: " + my.getBalance());
        System.out.println("Pot: " + pot);
    }

    public static void moneyUpload() {
        System.out.println("Add money to your balance: (The AI will start with the same amount)");
        int balance = intBeolvasas();
        System.out.println("Give me your name.");
        String name = stringBeolvasas();
        my.setName(name);
        my.setBalance(balance);
        pc1.setBalance(balance);
        pc1.setName("PC1");
        pc2.setBalance(balance);
        pc2.setName("PC2");
    }

    public static void printCards(ArrayList<Card> myCards) {
        for (Card myCard : myCards) {
            printCard(myCard);
        }
    }

    public static void runGame() {
        profiles.add(my);
        profiles.add(pc1);
        profiles.add(pc2);

        ArrayList<Card> myCards = new ArrayList<>();
        ArrayList<Card> gepCards1 = new ArrayList<>();
        ArrayList<Card> gepCards2 = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            myCards.add(chooseRandomCard());
            gepCards1.add(chooseRandomCard());
            gepCards2.add(chooseRandomCard());
        }
        my.setHand(myCards);
        pc1.setHand(gepCards1);
        pc2.setHand(gepCards2);
        printTable();
        askingMe();

//dealing flop, adding to cardsOnTable
        chooseRandomCard(); //burning
        for (int i = 0; i < 3; i++) {
            cardsOnTable.add(chooseRandomCard());
        }
        printTable();
        askingMe();
//Dealing the Turn
        chooseRandomCard();
        cardsOnTable.add(chooseRandomCard());
        System.out.println("Turn: ");
        printTable();
        askingMe();
//Dealing the River
        chooseRandomCard();
        cardsOnTable.add(chooseRandomCard());
        System.out.println("River: ");
        printTable();

        askingMe();
        winnerGetsPot();
        cardsOnTable.clear();
        System.out.println("______________________NEW_ROUND______________________________");
        runGame();

    }

    public static void winnerGetsPot() {
        ArrayList<Profile> winners = RuleChecker.checkWinner();
        if (winners.size() == 1) {
            System.out.println("Winner: " + winners.get(0).getName());
            System.out.println("Won: " + pot);
            winners.get(0).setBalance(winners.get(0).getBalance() + pot);
            pot = 0;
        } else {
            System.out.print("Winners: ");
            int splittedPot = pot / winners.size();
            for (Profile winner : winners) {
                System.out.print(winner.getName() + " Cards: ");
                printCards(winner.getHand());
                winner.setBalance(winner.getBalance() + splittedPot);
            }
            pot = 0;
        }
    }

    private static String stringBeolvasas() {
        return sc.next();
    }

    private static void askingMe() {
        System.out.println("C = Check , R = Raise , F = Fold");
        String c = stringBeolvasas();
        if (c.equals("R") || c.equals("r")) {
            bet();
        }
        if (c.equals("F") || c.equals("f")) {
            System.out.println("New service.Deal? Y/N");
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




