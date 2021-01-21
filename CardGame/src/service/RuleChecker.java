package service;

import models.Card;
import models.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class RuleChecker {
    private static final ArrayList<Card> cardsOnTable = Deal.cardsOnTable;
    private static final ArrayList<Profile> profiles = Deal.profiles;


    public static ArrayList<Integer> getSevenCards(Profile profile) {
        ArrayList<Integer> sevenCard = new ArrayList<>();

        for (Card cardsontable : cardsOnTable) {
            sevenCard.add(cardsontable.getValue());
        }
        for (Card handcards : profile.getHand()) {
            sevenCard.add(handcards.getValue());
        }
        Collections.sort(sevenCard);
        return sevenCard;
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

    public static boolean checkStraight(ArrayList<Integer> sevenCards) {
        int db = 1;
        //check if cards follow each other
        for (int j = 0; j < sevenCards.size()-1; j++) {
            if (sevenCards.get(j) == sevenCards.get(j+1) - 1) {
                db++;
            } else {
                db = 1;
            }
        }

        return (sevenCards.containsAll(Arrays.asList(1, 10, 11, 12, 13)) || db >=5);
    }


    public static int getOccurances(ArrayList<Integer> sevenCards) {
        HashMap<Integer, Integer> incidences = new HashMap<>();
        for (int i = 0; i < sevenCards.size(); i++) {
            if (incidences.containsKey(sevenCards.get(i))) {
                incidences.put(sevenCards.get(i), incidences.get(sevenCards.get(i)) + 1);
            } else {
                incidences.put(sevenCards.get(i), 1);
            }
        }
        int inc = Collections.max(incidences.values());
        if ((Collections.frequency(incidences.values(), 2) == 2 || Collections.frequency(incidences.values(), 2) == 3) && (inc == 2)){
            inc = 2;
        }
        if ((Collections.frequency(incidences.values(), 2) == 1 && inc == 2)){
            inc = 10;
        }
        if ((Collections.frequency(incidences.values(), 2) == 1 || Collections.frequency(incidences.values(), 2) == 2) && (inc == 3)){
            inc = 5;
        }
        return inc;

    }

    public static boolean checkPoker(Profile profile) {
        return getOccurances(getSevenCards(profile)) == 4;
    }
    
    public static boolean checkThreeOfAKind(Profile profile) {
        return getOccurances(getSevenCards(profile)) == 3;
    }

    public static boolean checkTwoPair(Profile profile){
        return getOccurances(getSevenCards(profile)) == 2;
    }

    public static boolean checkOnePair(Profile profile){
        return getOccurances(getSevenCards(profile)) == 10;
    }
    public static boolean checkStraightFlush(Profile profile){
        return (checkFlush(profile) && checkStraight(getSevenCards(profile)));
    }
    
    public static boolean checkRoyalFlush(Profile profile){
        return (checkFlush(profile) && checkStraight(getSevenCards(profile)) && getSevenCards(profile).containsAll(Arrays.asList(1, 10, 11, 12, 13)));
    }
    
    public static boolean checkFullHouse(Profile profile){
        return getOccurances(getSevenCards(profile)) == 5;
    }
    public static int getHighCardValue(Profile profile){
        return Collections.max(getSevenCards(profile));
    }
}



/*
    public static Profile checkWinner() {
    Profile winner = null;
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
        return winner
    }
*/

//TODO
    /*
   .. Royal Flush- A, K, Q, J, 10, all in the same suit. 1 in 650,000
   .. Straight Flush - Five cards in sequence, all of the same suit. 1in 65,000
   .. Four of a Kind- Four cards of one rank. Kicker breaks ties. 1 in 4,000
   .. Full House- Three matching cards of one rank, plus Two matching cards of another rank. Higher ranking set of three wins. If two players have the same set of three, the player with the higher pair wins. 1 in 700
   .. Flush- Five cards of the same suit. High card wins. 1 in 500
   .. Straight- Five cards of sequential rank, but different suit. High card wins. 1 in 250
   .. Three of a kind- Three cards of the same rank, plus two unmatched cards. High set wins. 1 in 50
   .. Two Pair- Two cards of the same rank, plus Two cards of another rank. High pair wins. 1 in 20
   .. One Pair- Two cards of the same rank, plus Three unmatched cards. High pair wins. 1 in 2 1/3
    High Card- One card high, plus four unmatched lower ranking cards. Ace is the Highest card. Kicker breaks ties. 1 in 1
     */
