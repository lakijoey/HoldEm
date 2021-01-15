import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Deal {


    private static myProfile my = new myProfile();
    private static gepProfile pc = new gepProfile();
    private static Scanner sc = new Scanner(System.in);

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
        Scanner sc = new Scanner(System.in);
        int balance = sc.nextInt();
        my.setBalance(balance);
        pc.setBalance(balance);
    }

    public static void aGame(ArrayList<Integer> cards) {
        ArrayList<Integer> myCards = new ArrayList<>();             //Az en lapjaim
        ArrayList<Integer> gepCards = new ArrayList<>();            //A gep lapjai

        for (int i = 0; i < 2; i++) {
            int card = (int) (Math.random() * cards.size());
            cards.remove(card);
            myCards.add(card);
        }

        for (int i = 0; i < 2; i++) {
            int card = (int) (Math.random() * cards.size());
            cards.remove(card);
            gepCards.add(card);
        }
        my.setMyHand(myCards);
        pc.setGepHand(gepCards);
        Pot pot = new Pot(0);

        System.out.println("Your Cards: " + my.myHand);
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
                aGame(Deck.myDeck());
            }
        }
        //dealing flop, adding to cardsOnTable, and removing from deck
        ArrayList<Integer> cardsOnTable = new ArrayList<>();
        int burn = (int) (Math.random() * cards.size());
        cards.remove(burn);
        for (int i = 0; i < 3; i++) {                           //dealing flop, adding to cardsOnTable, and removing from deck
            int card = (int) (Math.random() * cards.size());
            cards.remove(card);
            cardsOnTable.add(card);
        }
        System.out.println("A flop: " + cardsOnTable);
        System.out.println("Your Cards: " + my.myHand);
        System.out.println("C = Check , R = Raise , F = Fold");
        Scanner scb = new Scanner(System.in);
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
                aGame(Deck.myDeck());
            }
        }

        //Dealing the Turn
        burn = (int) (Math.random() * cards.size());
        cards.remove(burn);

        int card = (int) (Math.random() * cards.size());
        cards.remove(card);
        cardsOnTable.add(card);
        System.out.println("Turn: " + cardsOnTable);

        System.out.println("Your Cards: " + my.myHand);
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
                aGame(Deck.myDeck());
            }
        }
        //Dealing the River
        burn = (int) (Math.random() * cards.size());
        cards.remove(burn);

        card = (int) (Math.random() * cards.size());
        cards.remove(card);
        cardsOnTable.add(card);
        System.out.println("River: " + cardsOnTable);

        System.out.println("Your Cards: " + my.myHand);
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
                aGame(Deck.myDeck());
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




