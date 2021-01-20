package service;

import models.Card;
import models.Profile;
import models.Symbols;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class RuleChecker {
    private static final ArrayList<Card> cardsOnTable = Deal.cardsOnTable;
    private static final ArrayList<Profile> profiles = Deal.profiles;

    private static ArrayList<Profile> colorCheck() {
        ArrayList<Profile> winner = new ArrayList<>();
        int spades = Collections.frequency(cardsOnTable, Symbols.SPADES);
        int club = Collections.frequency(cardsOnTable, Symbols.CLUBS);
        int diamonds = Collections.frequency(cardsOnTable, Symbols.DIAMONDS);
        int hearts = Collections.frequency(cardsOnTable, Symbols.HEARTS);
        int s = 0, c = 0, d = 0, h = 0;

        for (Profile profile : profiles) {
            for (Card card : profile.getHand()) {
                if (card.getSymbol() == Symbols.CLUBS) {
                    c++;
                } else if (card.getSymbol() == Symbols.DIAMONDS) {
                    d++;
                } else if (card.getSymbol() == Symbols.SPADES) {
                    s++;
                } else if (card.getSymbol() == Symbols.HEARTS) {
                    h++;
                }
            }
            if (spades + s >= 5 || hearts + h >= 5 || diamonds + d >= 5 || club + c >= 5) {
                winner.add(profile);
            }
            s = 0;
            c = 0;
            d = 0;
            h = 0;
        }
        return winner;
    }

    private static Symbols colorCheckGetSymbol() {
        Symbols symbol = Symbols.valueOf("");
        int spades = Collections.frequency(cardsOnTable, Symbols.SPADES);
        int club = Collections.frequency(cardsOnTable, Symbols.CLUBS);
        int diamonds = Collections.frequency(cardsOnTable, Symbols.DIAMONDS);
        int hearts = Collections.frequency(cardsOnTable, Symbols.HEARTS);
        int s = 0, c = 0, d = 0, h = 0;

        for (Profile profile : profiles) {
            for (Card card : profile.getHand()) {
                if (card.getSymbol() == Symbols.CLUBS) {
                    c++;
                } else if (card.getSymbol() == Symbols.DIAMONDS) {
                    d++;
                } else if (card.getSymbol() == Symbols.SPADES) {
                    s++;
                } else if (card.getSymbol() == Symbols.HEARTS) {
                    h++;
                }
            }
            if (spades + s >= 5) {
                symbol = Symbols.SPADES;
            } else if (hearts + h >= 5) {
                symbol = Symbols.HEARTS;
            } else if (diamonds + d >= 5) {
                symbol = Symbols.DIAMONDS;
            } else if (club + c >= 5) {
                symbol = Symbols.CLUBS;
            }
            s = 0;
            c = 0;
            d = 0;
            h = 0;
        }
        return symbol;
    }

    public static ArrayList<Profile> checkFlush() {
        ArrayList<Profile> winner = new ArrayList<>();
        HashMap<Profile, Integer> compare = new HashMap<>();
        if (colorCheck().size() > 1) {
            for (Profile profiles : colorCheck()) {
                for (Card cards : profiles.getHand()) {
                    if (cards.getSymbol() == colorCheckGetSymbol()){
                        compare.put(profiles, cards.getValue());
                    }
                }
            }
            winner.add(Collections.max(compare.entrySet(), Map.Entry.comparingByValue()).getKey());

            }

         else {
            winner = colorCheck();
        }
        return winner;
    }


//TODO
    /*

    Royal Flush- A, K, Q, J, 10, all in the same suit. 1 in 650,000
    Straight Flush - Five cards in sequence, all of the same suit. 1in 65,000
    Four of a Kind- Four cards of one rank. Kicker breaks ties. 1 in 4,000
    Full House- Three matching cards of one rank, plus Two matching cards of another rank. Higher ranking set of three wins. If two players have the same set of three, the player with the higher pair wins. 1 in 700
    Flush- Five cards of the same suit. High card wins. 1 in 500
    Straight- Five cards of sequential rank, but different suit. High card wins. 1 in 250
    Three of a kind- Three cards of the same rank, plus two unmatched cards. High set wins. 1 in 50
    Two Pair- Two cards of the same rank, plus Two cards of another rank. High pair wins. 1 in 20
    One Pair- Two cards of the same rank, plus Three unmatched cards. High pair wins. 1 in 2 1/3
    High Card- One card high, plus four unmatched lower ranking cards. Ace is the Highest card. Kicker breaks ties. 1 in 1
     */
}
