package service;

import models.Card;
import models.Profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class RuleChecker {
    private static final ArrayList<Card> cardsOnTable = Deal.cardsOnTable;
    private static final ArrayList<Profile> profiles = Deal.profiles;

    public static int[] getSevenCards(Profile profile) {
        int[] sevenCards = new int[7];
        int csere = 0;
        int i = 0;
        //cardsOnTable Values to tomb
        for (Card cardsontable : cardsOnTable) {
            sevenCards[i] = cardsontable.getValue();
            i++;
        }
        //PlayerCardValues to tomb ...now we have all cards values 
        for (Card handcards : profile.getHand()) {
            sevenCards[i] = handcards.getValue();
            i++;
        }
        //getting ascending order
        for (int j = 0; j < sevenCards.length - 1; j++) {
            for (int k = j + 1; k < sevenCards.length; k++) {
                if (sevenCards[j] > sevenCards[k]) {
                    csere = sevenCards[j];
                    sevenCards[j] = sevenCards[k];
                    sevenCards[k] = csere;
                }
            }
        }
        return sevenCards;
    }

    public static boolean checkFlush(Profile profile) {
        int s = 0, c = 0, d = 0, h = 0;
        for (Card card : cardsOnTable) {
            switch (card.getSymbol()) {
                case CLUBS -> c++;
                case HEARTS -> h++;
                case DIAMONDS -> d++;
                case SPADES -> s++;
            }
        }
        for (Card card : profile.getHand()) {
            switch (card.getSymbol()) {
                case CLUBS -> c++;
                case HEARTS -> h++;
                case DIAMONDS -> d++;
                case SPADES -> s++;
            }
        }
        return (c >= 5 || h >= 5 || d >= 5 || s >= 5);
    }

    public static boolean checkStraight(int[] sevenCards) {
        int db = 1;
        //check if cards follow each other
        for (int j = 0; j < sevenCards.length - 1; j++) {
            if (sevenCards[j] == sevenCards[j + 1] - 1) {
                db++;
            } else {
                db = 1;
            }
        }
        return db >= 5;
    }

    public static boolean checkThreeOfAKind(int[] sevenCards) {
        HashMap<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < sevenCards.length - 1; i++) {
            if (values.containsKey(sevenCards[i])) {
                values.put(sevenCards[i], values.get(sevenCards[i]) + 1);
            } else {
                values.put(sevenCards[i], 1);
            }
        }
        int maxValue = Collections.max(values.values());
        return maxValue >= 3;
    }

    public static boolean checkPoker(int[] sevenCards){
        HashMap<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < sevenCards.length - 1; i++) {
            if (values.containsKey(sevenCards[i])) {
                values.put(sevenCards[i], values.get(sevenCards[i]) + 1);
            } else {
                values.put(sevenCards[i], 1);
            }
        }
        int maxValue = Collections.max(values.values());
        return maxValue >= 4;
    }

}



/*
    public void checkWinner() {
        for (Profile profile : profiles) {
            checkHighCard(profile);
            checkPairs(profile);
            checkDrill(profile);
            checkStraight(profile);
            checkFlush(profile);
            checkPoker(profile);
            if(checkPairs == true && checkDrill == true) {
            checkFullHouse(profile);
            }

            if(checkStraight == true && checkFlush== true) {
                checkStraightFlush(profile);
                if (checkStraightFlush == true) {
                    checkRoyal(profile);
                }
            }
        }
    }
*/

//TODO
    /*
    Remove card if Fold
    Royal Flush- A, K, Q, J, 10, all in the same suit. 1 in 650,000
   .... DoubleCheck(Flush, Straight) Straight Flush - Five cards in sequence, all of the same suit. 1in 65,000
   .. Four of a Kind- Four cards of one rank. Kicker breaks ties. 1 in 4,000
   .... DoubleCheck(One Pair, Three of..)Full House- Three matching cards of one rank, plus Two matching cards of another rank. Higher ranking set of three wins. If two players have the same set of three, the player with the higher pair wins. 1 in 700
   .. Flush- Five cards of the same suit. High card wins. 1 in 500
   .. Straight- Five cards of sequential rank, but different suit. High card wins. 1 in 250
   .. Three of a kind- Three cards of the same rank, plus two unmatched cards. High set wins. 1 in 50
    Two Pair- Two cards of the same rank, plus Two cards of another rank. High pair wins. 1 in 20
    One Pair- Two cards of the same rank, plus Three unmatched cards. High pair wins. 1 in 2 1/3
    High Card- One card high, plus four unmatched lower ranking cards. Ace is the Highest card. Kicker breaks ties. 1 in 1
     */
