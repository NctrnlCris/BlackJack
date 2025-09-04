package Classes;

import java.util.ArrayList;
import java.util.List;

import Exceptions.BustException;
import Exceptions.CutCardException;
import Exceptions.NoPairSplitException;

public class BlackJack {
    private int decks;
    private Deck totalDeck;
    private List<Card> discard;
    private List<Card> dealerHand;
    private List<Card> playerHand;
    private List<Card> playerHand2;
    private List<Card> playerHand3;
    private List<Card> playerHand4;
    private int playerTotal;
    private int dealerTotal;
    private int bankRoll;
    private int bet;
    private int winnings;
    private Boolean lastHand = false;

    public BlackJack(int decks) {
        this.decks = decks;
        totalDeck = new Deck();
        for (int i = 0; i < decks; i++) {
            totalDeck.generateDeck();
        }
        totalDeck.shuffleDeck();
        bankRoll = 1000;
        playerHand = new ArrayList<Card>();
        dealerHand = new ArrayList<Card>();
    }

    public void placeBet(int bet) {
        this.bet = bet;
        bankRoll -= bet;
    }

    public void dealHands() throws CutCardException {
        playerHand.add(totalDeck.getNextCard());
        totalDeck.removeCard();
        playerHand.add(totalDeck.getNextCard());
        totalDeck.removeCard();
        dealerHand.add(totalDeck.getNextCard());
        totalDeck.removeCard();
        dealerHand.add(totalDeck.getNextCard());
        totalDeck.removeCard();
        playerTotal = playerHand.get(0).getValue() + playerHand.get(1).getValue();
        dealerTotal = dealerHand.get(0).getValue();
    }

    public void dealerTurn() throws CutCardException, BustException {
        for (Card c : dealerHand) {
            dealerTotal += c.getValue();
        }
        while (dealerTotal < 17) {
            hitDealer();
        }

        if (dealerTotal == 17) {
            if (soft17()) {
                hitDealer();
            }
            compareHands();
        }

        if (dealerTotal > 17) {
            compareHands();
        }
    }

    public boolean soft17() {
        for (Card c : dealerHand) {
            if (c.isAce()) {
                return true;
            }
        }
        return false;
    }

    public void hitDealer() throws CutCardException, BustException{
        Card nextCard = totalDeck.getNextCard(); 
        dealerHand.add(nextCard);
        dealerTotal += nextCard.getValue();
        totalDeck.removeCard();
        if (dealerTotal > 21) {
            handleAce();
        }
    }

    public void handleAce() throws BustException {
        Boolean ace = false;
        for (Card c : dealerHand) {
            if (c.isAce()) {
                c.setAceValue();
                dealerTotal -= 10;
                ace = true;
                break;
            }
        }
        if (!ace) {
            calculateWinnings();
            collectWinnings();
            clearTable();
        }
    }

    public void compareHands() {
        if (playerTotal > dealerTotal) {
            calculateWinnings();
        }
        if (playerTotal == dealerTotal) {
            if (playerHand.size() == 2 && playerTotal == 21 && dealerHand.size() != 2) {
                winnings = (int) (2.5 * bet);
            }
            if ((dealerHand.size() != 2 && dealerTotal == 21) || dealerTotal != 21) {
                winnings = bet;
            }
        }
        collectWinnings();
        bet = 0;
    }

    public void calculateWinnings() {
        if (playerHand.size() == 2 && playerTotal == 21) {
            winnings = (int) (2.5 * bet);
        }
        else {
            winnings = 2 * bet;
        }
    }

    public void collectWinnings() {
        bankRoll += winnings;
        //winnings = 0;
    }

    public void clearTable() {
        for (Card c : playerHand) {
            discard.add(c);
        }
        for (Card c : dealerHand) {
            discard.add(c);
        }
        playerHand.clear();
        dealerHand.clear();
        winnings = 0;
    }


    public void hitPlayer() throws CutCardException, BustException {
        playerHand.add(totalDeck.getNextCard());
        totalDeck.removeCard();
        if (playerTotal > 21) {
            throw new BustException();
        }
        dealerTurn();

    }

    public void standPlayer() throws CutCardException, BustException {
        dealerTurn();
    }

    public void doublePlayer() throws CutCardException, BustException {
        bet = bet * 2;
        bankRoll -= bet;
        hitPlayer();
    }

    // public void splitPlayer() throws NoPairSplitException {
    //     if playerHand
    // }

    public void surrenderPlayer() {
        if (dealerHand.size() != 2 || dealerTotal != 21) {
            winnings = (int) 0.5 * bet;
        }
        collectWinnings();
        bet = 0;
    }

    public void setLastHandTrue() {
        lastHand = true;
    }

    public int getPlayerTotal() {
        return playerTotal;
    }

    public int getDealerTotal() {
        return dealerTotal;
    }

    public int getWinnings() {
        return winnings;
    }
}
