package myOwnVersion.GameState;
import java.util.List;
import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardHandeler;
import myOwnVersion.Cards.CardComponents.CardComponent;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;

public class EventDrawDeck extends DrawDeck {

    public EventDrawDeck(List<Card> cards) {
        super(cards);
    }

    @Override
    public Card drawCardTop() {
        Card topCard = cards.get(0);
        if (topCard.hasComponent(StandardCardInfoComponent.class)) {
            StandardCardInfoComponent component = topCard.getComponent(StandardCardInfoComponent.class);
            if (component.name == "Yule") {
                shuffle();
                return drawCardTop();
            }
        }
        return super.drawCardTop();
    }

    @Override
    public void shuffle() {
        // get the Yule card
        Card yuleCard = null;
        for (Card card : cards) {
            if (card.hasComponent(StandardCardInfoComponent.class)) {
                StandardCardInfoComponent component = card.getComponent(StandardCardInfoComponent.class);
                if (component.name == "Yule") {
                    yuleCard = card;
                    cards.remove(card);
                    break;
                }
            }
        }
        // shuffle the rest of the deck
        java.util.Collections.shuffle(cards);
        // put Yule card on 4th from bottom
        if (yuleCard != null) {
            cards.add(cards.size() - 4, yuleCard);
        }
    }
}
