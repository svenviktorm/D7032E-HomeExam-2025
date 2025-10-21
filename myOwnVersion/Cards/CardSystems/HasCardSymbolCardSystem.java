package myOwnVersion.Cards.CardSystems;

import java.util.List;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.CardSymbol;
import myOwnVersion.Cards.CardComponents.HasSymbolRewardCardComponent;
import myOwnVersion.GameState.Principality.Principality;

/**
 * A card system for cards that have CardSymbolRewards, tied to HasSymbolRewardCardComponent.
 */
public class HasCardSymbolCardSystem extends CardSystem {
   
    /**
     * Gets the CardSymbolReward of a specific type from a card if it has one.
     * @param card the card to check
     * @param type the type of the CardSymbolReward to retrieve
     * @return the CardSymbolReward of the specified type, or null if not found
     */
    public CardSymbol getResourceFromType(Card card, CardSymbolType type) {
        if (!card.hasComponent(HasSymbolRewardCardComponent.class)) {
            return null;
        }
        HasSymbolRewardCardComponent component = card.getComponent(HasSymbolRewardCardComponent.class);
        for (CardSymbol reward : component.rewards) {
            if (reward.getCardSymbol() == type) {
                return reward;
            }
        }
        return null;
    }

    /**
     * Checks if the card has a CardSymbolReward of a specific type.
     * @param card the card to check
     * @param type the type of the CardSymbolReward to check for
     * @return true if the card has a CardSymbolReward of the specified type, false otherwise
     */
    public boolean hasResourceType(Card card, CardSymbolType type) {
        return getResourceFromType(card, type) != null;
    }

    /**
     * Gets the total amount of a specific CardSymbolReward type from all cards in a principality.
     * @param principality the principality to check
     * @param type the type of the CardSymbolReward to sum
     * @return a CardSymbolReward representing the total amount of the specified type
     */
    public CardSymbol getAllRewardsFromPrincipality (Principality principality, CardSymbolType type) {
        CardSymbol reward = new CardSymbol(type, 0);
        List<Card> cards = principality.getPlayedCards();
        for (Card card : cards) {
            CardSymbol cardReward = getResourceFromType(card, type);
            reward = new CardSymbol(type, reward.getAmount() + (cardReward != null ? cardReward.getAmount() : 0));
        }
        return reward;
    }


}
