package myOwnVersion.GameState;

import java.util.List;

import myOwnVersion.Cards.Card;

public class DrawDeck {

    protected List<Card> cards;

    public DrawDeck(List<Card> cards) {
        this.cards = cards;
    }

    public Card drawCardTop() {
        if (cards.isEmpty()) {
            return null; 
        }
        return cards.remove(0);
    }

    public void addCardBottom(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public void shuffle() {
        java.util.Collections.shuffle(cards);
    }
    
    public List<Card> getCards() {
        return cards;
    }

    public Card removeCard(Card card) {
        if (cards.remove(card)) {
            return card;
        }
        return null;
    }


}
