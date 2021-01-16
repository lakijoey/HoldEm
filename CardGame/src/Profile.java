import java.util.ArrayList;

public class Profile {
    ArrayList<Card> hand = new ArrayList<Card>();
    private int balance;

    public Profile() {
    }

    public Profile(ArrayList<Card> hand, int balance) {
        this.hand = hand;
        this.balance = balance;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
