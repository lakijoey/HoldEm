package models;

import java.util.ArrayList;

public class Profile {
    private String name;
    ArrayList<Card> hand = new ArrayList<Card>();
    private int balance;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Profile() {
    }

    public Profile(ArrayList<Card> hand, int balance, String name) {
        this.hand = hand;
        this.balance = balance;
        this.name = name;
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
