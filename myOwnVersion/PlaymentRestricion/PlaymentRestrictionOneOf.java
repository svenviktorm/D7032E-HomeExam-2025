package myOwnVersion.PlaymentRestricion;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;
import myOwnVersion.GameState.Principality.Principality;

public class PlaymentRestrictionOneOf implements PlaymentRestriction {

    @Override
    public boolean isPlaymentValid(Card card, Principality principality) {
        Card[] playedCards = principality.getPlayedCards();
        String name = card.getComponent(StandardCardInfoComponent.class).name;
        for (Card playedCard : playedCards) {
            if (playedCard != null && playedCard.getComponent(StandardCardInfoComponent.class).name.equals(name)) {
                return false; // Card with the same name has already been played
            }
        }
        return true;
    }

}
