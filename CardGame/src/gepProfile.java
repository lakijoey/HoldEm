import java.util.ArrayList;

public class gepProfile {
    ArrayList<Integer> gepHand;
    int balance;

    public gepProfile(ArrayList<Integer> gepHand) {
        this.gepHand = gepHand;
    }

    public gepProfile() {
    }

    public ArrayList<Integer> getGepHand() {
        return gepHand;
    }

    public void setGepHand(ArrayList<Integer> gepHand) {
        this.gepHand = gepHand;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
