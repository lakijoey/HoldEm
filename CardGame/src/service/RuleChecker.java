package service;

import models.Card;
import models.Profile;

import java.util.*;


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
        for (int j = 0; j < sevenCards.size() - 1; j++) {
            if (sevenCards.get(j) == sevenCards.get(j + 1) - 1) {
                db++;
            } else {
                db = 1;
            }
        }

        return (sevenCards.containsAll(Arrays.asList(1, 10, 11, 12, 13)) || db >= 5);
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
        if ((Collections.frequency(incidences.values(), 2) == 2 || Collections.frequency(incidences.values(), 2) == 3) && (inc == 2)) {
            inc = 2;
        }
        if ((Collections.frequency(incidences.values(), 2) == 1 && inc == 2)) {
            inc = 10;
        }
        if ((Collections.frequency(incidences.values(), 2) == 1 || Collections.frequency(incidences.values(), 2) == 2) && (inc == 3)) {
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

    public static boolean checkTwoPair(Profile profile) {
        return getOccurances(getSevenCards(profile)) == 2;
    }

    public static boolean checkOnePair(Profile profile) {
        return getOccurances(getSevenCards(profile)) == 10;
    }

    public static boolean checkStraightFlush(Profile profile) {
        return (checkFlush(profile) && checkStraight(getSevenCards(profile)));
    }

    public static boolean checkRoyalFlush(Profile profile) {
        return (checkFlush(profile) && checkStraight(getSevenCards(profile)) && getSevenCards(profile).containsAll(Arrays.asList(1, 10, 11, 12, 13)));
    }

    public static boolean checkFullHouse(Profile profile) {
        return getOccurances(getSevenCards(profile)) == 5;
    }

    public static int getHighCardValue(Profile profile) {
        return Collections.max(getSevenCards(profile));
    }

    public static ArrayList<Profile> checkWinner() {
        ArrayList<Profile> winner = new ArrayList<>();
        int score = 0;
        HashMap<Profile, Integer> ranking = new HashMap<>();
        for (Profile profile : profiles) {
            if (checkOnePair(profile)) {
                score = 1;
            }
            if (checkTwoPair(profile)) {
                score = 2;
            }
            if (checkThreeOfAKind(profile)) {
                score = 3;
            }
            if (checkStraight(getSevenCards(profile))) {
                score = 4;
            }
            if (checkFlush(profile)) {
                score = 5;
            }
            if (checkFullHouse(profile)) {
                score = 6;
            }
            if (checkPoker(profile)) {
                score = 7;
            }
            if (checkStraightFlush(profile)) {
                score = 8;
            }
            if (checkRoyalFlush(profile)) {
                score = 9;
            }
            ranking.put(profile, score);
        }
        if (Collections.frequency(ranking.values(), Collections.max(ranking.values())) > 1) { //check if draw
            for (Map.Entry<Profile, Integer> profileIntegerEntry : ranking.entrySet()) {
                if (profileIntegerEntry.getValue() < Collections.max(ranking.values())) {
                    ranking.remove(profileIntegerEntry.getKey());
                }
            }
            for (Map.Entry<Profile, Integer> profileIntegerEntry : ranking.entrySet()) {
                //if draw high card wins
                score = getHighCardValue(profileIntegerEntry.getKey());
                ranking.put(profileIntegerEntry.getKey(), score);
            }
        }
        for (Map.Entry<Profile, Integer> winners : ranking.entrySet()) {  //check final scores after drawcheck, can be still draw with high card, thats why im using arraylist
            if (winners.getValue() == Collections.max(ranking.values()).intValue()) {
                winner.add(winners.getKey());
            }
        }
        return winner;
    }
}