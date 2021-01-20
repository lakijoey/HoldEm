package service;

import models.Card;
import models.Profile;

import java.util.ArrayList;


public class RuleChecker {
    private static final ArrayList<Card> cardsOnTable = Deal.cardsOnTable;
    private static final ArrayList<Profile> profiles = Deal.profiles;

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
