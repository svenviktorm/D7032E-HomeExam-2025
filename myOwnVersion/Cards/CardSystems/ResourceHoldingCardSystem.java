package myOwnVersion.Cards.CardSystems;

import java.security.Principal;

import myOwnVersion.Principality;
import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.CardComponents.ResourceHoldingCardComponent;

/**
 * A card system for resource holding cards, tied to ResourceHoldingCardComponent.
 */
public class ResourceHoldingCardSystem extends RotateCardSystem {
    
    /**
     * Gets the resource type of the card.
     * @param card the card to check
     * @return the resource type, null if card does not have resource holding component
     */
    public CardSymbolType getResourceType(Card card) {
        if (!card.hasComponent(ResourceHoldingCardComponent.class)) {
            return null;
        }
        ResourceHoldingCardComponent res = card.getComponent(ResourceHoldingCardComponent.class);
        return res.resourceType; 
    }

    /**
     * Checks if the card is a resource holding card.
     * @param card the card to check
     * @return true if the card is a resource holding card
     */
    public boolean isResourceHoldingCard(Card card) {
        return card.hasComponent(ResourceHoldingCardComponent.class);
    }

    /**
     * Gets the available resources off the card.
     * @param card the card to check
     * @return the amount of resources available, -1 if card does not have resource holding component
     */
    public int getAvailabeResources(Card card) {
        if (!card.hasComponent(ResourceHoldingCardComponent.class)) {
            return -1;
        }
        ResourceHoldingCardComponent res = card.getComponent(ResourceHoldingCardComponent.class);
        return res.faces.get(res.curentFaceIndex).get(0).getAmount();
    }
    
    /**
     * Gets the available space on the card.
     * @param card the card to check
     * @return the available space, -1 if card does not have resource holding component
     */
    public int getAvailableSpace(Card card) {
        if (!card.hasComponent(ResourceHoldingCardComponent.class)) {
            return -1;
        }
        ResourceHoldingCardComponent res = card.getComponent(ResourceHoldingCardComponent.class);
        return 3 - res.faces.get(res.curentFaceIndex).get(0).getAmount();
    }

    /**
     * Removes resources from the card.
     * @param card the card to remove resources from
     * @param amount the amount of resources to remove
     * @return true if the resources were successfully removed, false otherwise
     */
    public boolean removereResources(Card card, int amount) {
        if (!card.hasComponent(ResourceHoldingCardComponent.class)) {
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
        if (!card.hasComponent(ResourceHoldingCardComponent.class)) {
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

    public int getAvailabeResourcesOfType(CardSymbolType type, Principality principality) {
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
