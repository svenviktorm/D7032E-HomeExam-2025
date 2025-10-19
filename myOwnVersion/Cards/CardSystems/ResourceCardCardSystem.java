package myOwnVersion.Cards.CardSystems;

import myOwnVersion.Principality;
import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.ResourceCardCardComponent;

/**
 * A card system for resource cards, tied to ResourceCardCardComponent.
 */
public class ResourceCardCardSystem extends ResourceHoldingCardSystem {

    /**
     * Checks if the given card is a resource card.
     *
     * @param card The card to check.
     * @return true if the card is a resource card, false otherwise.
     */
    public boolean isResourceCard(Card card) {
        return card.hasComponent(ResourceCardCardComponent.class);
    }

    /**
     * Produces resources for all resource cards in the given principalities based on the dice roll.
     *
     * @param diceRoll      The result of the dice roll.
     * @param principalites The array of principalities to check for resource cards.
     */
    public void produce(int diceRoll, Principality[] principalites) {
        for (Principality principality : principalites) {
            // Iterate through each card in the principality
            for (Card card : principality.getPlayedCards()) {
                if (!isResourceCard(card)) {
                    continue;
                } 
                ResourceCardCardComponent component = card.getComponent(ResourceCardCardComponent.class);
                if (component.productionDiceRoll == diceRoll) {
                    if (getAvailableSpace(card) >= 1) {
                        addResources(card, 1);
                    }
                }
            }
        }
    }
}
