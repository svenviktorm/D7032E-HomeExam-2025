package myOwnVersion.Cards.CardSystems;

import java.security.Principal;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.CardComponents.SymbolHoldingCardComponent;
import myOwnVersion.GameState.Principality.Principality;

/**
 * A card system for resource holding cards, tied to ResourceHoldingCardComponent.
 */
public class SymbolHoldingCardSystem extends RotateCardSystem {
    
    /**
     * Gets the resource type of the card.
     * @param card the card to check
     * @return the resource type, null if card does not have resource holding component
     */
    public CardSymbolType getResourceType(Card card) {
        if (!card.hasComponent(SymbolHoldingCardComponent.class)) {
            return null;
        }
        SymbolHoldingCardComponent res = card.getComponent(SymbolHoldingCardComponent.class);
        return res.resourceType; 
    }

    /**
     * Checks if the card is a resource holding card.
     * @param card the card to check
     * @return true if the card is a resource holding card
     */
    public boolean isResourceHoldingCard(Card card) {
        return card.hasComponent(SymbolHoldingCardComponent.class);
    }

    /**
     * Gets the available resources off the card.
     * @param card the card to check
     * @return the amount of resources available, -1 if card does not have resource holding component
     */
    public int getAvailabeResources(Card card) {
        if (!card.hasComponent(SymbolHoldingCardComponent.class)) {
            return -1;
        }
        SymbolHoldingCardComponent res = card.getComponent(SymbolHoldingCardComponent.class);
        return res.faces.get(res.curentFaceIndex).get(0).getAmount();
    }
    
    /**
     * Gets the available space on the card.
     * @param card the card to check
     * @return the available space, -1 if card does not have resource holding component
     */
    public int getAvailableSpace(Card card) {
        if (!card.hasComponent(SymbolHoldingCardComponent.class)) {
            return -1;
        }
        SymbolHoldingCardComponent res = card.getComponent(SymbolHoldingCardComponent.class);
        return 3 - res.faces.get(res.curentFaceIndex).get(0).getAmount();
    }

    /**
     * Removes resources from the card.
     * @param card the card to remove resources from
     * @param amount the amount of resources to remove
     * @return true if the resources were successfully removed, false otherwise
     */
    public boolean removereResources(Card card, int amount) {
        if (!card.hasComponent(SymbolHoldingCardComponent.class)) {
            return false;
        }
        if (getAvailabeResources(card)>= amount) {
            for (int i = 0; i < amount; i++) {
                roateRight(card);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds resources to the card.
     * @param card the card to add resources to
     * @param amount the amount of resources to add
     * @return true if the resources were successfully added, false otherwise
     */
    public boolean addResources(Card card, int amount) {
        if (!card.hasComponent(SymbolHoldingCardComponent.class)) {
            return false;
        }
        if (getAvailableSpace(card) >= amount) {
            for (int i = 0; i < amount; i++) {
                roateLeft(card);
            }
            return true;
        } else {
            return false;
        }
    }

    public int getAvailableResourcesOfType(CardSymbolType type, Principality principality) {
        int total = 0;
        for (Card card : principality.getPlayedCards()) {
            if (!isResourceHoldingCard(card)) {
                continue;
            }
            if (getResourceType(card) != type) {
                continue;
            }
            total += getAvailabeResources(card);
        }
        return total;
    }
}
