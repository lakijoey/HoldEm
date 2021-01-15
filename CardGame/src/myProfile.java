import java.util.ArrayList;

public class myProfile {
    ArrayList<Card> myHand = new ArrayList<Card>();
    private int balance;


    public myProfile(ArrayList<Card> myHand) {
        this.myHand = myHand;
    }

    public myProfile() {
    }

    public ArrayList<Card> getMyHand() {
        return myHand;
    }

    public void setMyHand(ArrayList<Card> myHand) {
        this.myHand = myHand;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
