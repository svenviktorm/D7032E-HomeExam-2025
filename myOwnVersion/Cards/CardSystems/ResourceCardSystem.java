package myOwnVersion.Cards.CardSystems;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.DoubleRegionCardComponent;
import myOwnVersion.Cards.CardComponents.PlacableCardComponent;
import myOwnVersion.Cards.CardComponents.RegionCardComponent;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;

/**
 * A card system for resource cards, tied to ResourceCardCardComponent.
 */
public class ResourceCardSystem extends SymbolHoldingCardSystem {

    /**
     * Checks if the given card is a resource card.
     *
     * @param card The card to check.
     * @return true if the card is a resource card, false otherwise.
     */
    public boolean isResourceCard(Card card) {
        return card.hasComponent(RegionCardComponent.class);
    }

    /**
     * Produces resources for all resource cards in the given principalities based on the dice roll.
     *
     * @param diceRoll      The result of the dice roll.
     * @param principalites The array of principalities to check for resource cards.
     */
    public void produceGlobal(int diceRoll, Principality[] principalites) {
        for (Principality principality : principalites) {
            // Iterate through each card in the principality
            for (Card card : principality.getPlayedCards()) {
                if (card.hasComponent(DoubleRegionCardComponent.class)) {
                    DoubleRegionCardComponent doubleComp = card.getComponent(DoubleRegionCardComponent.class);
                    if (doubleComp.productionDiceRoll == diceRoll) {
                        produceLocal(card,2);
                    }
                }
                if (!isResourceCard(card)) {
                    continue;
                } 
                RegionCardComponent component = card.getComponent(RegionCardComponent.class);
                if (component.productionDiceRoll == diceRoll) {
                    if (getAvailableSpace(card) >= 1) {
                        addResources(card, 1);
                    }
                }
            }
        }
    }

    public boolean produceLocal(Card card, int amount) {
        if (amount <= 0) {
            return false;
        }
        if (!isResourceCard(card)) {
            return false;
        }
        if (getAvailableSpace(card) >= amount) {
            addResources(card, amount);
            return true;
        } else {
            return produceLocal(card, amount-1);
        }
    }

    /**
     * Gives resources to neighboring Cards of the given principality.
     * Note that by neighboring we mean the cards that are directly adjacent in the same row.
     * @param card
     * @param principality
     */
    public void giveResourceToNeighbors(Card card, Principality principality) {
        Position position = card.getComponent(PlacableCardComponent.class).position;
        Position leftNeighborPos = new Position(position.getColumn(), position.getRow()+1);
        Position rightNeighborPos = new Position(position.getColumn()+1, position.getRow()-1);
        Card leftNeighborCard = principality.getCardAt(leftNeighborPos);
        Card rightNeighborCard = principality.getCardAt(rightNeighborPos);
        produceLocal(rightNeighborCard, 1);
        produceLocal(leftNeighborCard, 1);

    }
}
