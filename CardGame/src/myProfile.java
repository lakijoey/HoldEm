import java.util.ArrayList;

public class myProfile {
    ArrayList<Integer> myHand = new ArrayList<>();
    private int balance;

    public myProfile(ArrayList<Integer> myHand) {
        this.myHand = myHand;
    }

    public myProfile() {
    }

    public ArrayList<Integer> getMyHand() {
        return myHand;
    }

    public void setMyHand(ArrayList<Integer> myHand) {
        this.myHand = myHand;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
