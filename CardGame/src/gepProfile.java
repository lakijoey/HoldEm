import java.util.ArrayList;

public class gepProfile {
    ArrayList<Card> gepHand = new ArrayList<>();
    private int balance;

    public gepProfile( ArrayList<Card> gepHand) {
        this.gepHand = gepHand;
    }

    public gepProfile() {
    }

    public  ArrayList<Card> getGepHand() {
        return gepHand;
    }

    public void setGepHand( ArrayList<Card> myHand) {
        this.gepHand = myHand;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
