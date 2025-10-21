package myOwnVersion.GameState;

import java.util.List;

import myOwnVersion.Cards.Card;

public class DiscardPile {

    private List<Card> discardedCards;

    // constructor
    public DiscardPile() {
        this.discardedCards = new java.util.ArrayList<>();
    }

    public int size() {
        return discardedCards.size();
    }

    public void addCard(Card card) {
        discardedCards.add(card);
    }

    public Card getTopCard() {
        if (discardedCards.isEmpty()) {
            return null;
        }
        return discardedCards.get(discardedCards.size() - 1);
    }

}
