package myOwnVersion.Cards.CardSystems;

import myOwnVersion.Principality;
import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardSymbol;
import myOwnVersion.Cards.CardSymbolReward;
import myOwnVersion.Cards.CardComponents.HasSymbolRewardCardComponent;

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
    public CardSymbolReward getResourceFromType(Card card, CardSymbol type) {
        if (!card.hasComponent(HasSymbolRewardCardComponent.class)) {
            return null;
        }
        HasSymbolRewardCardComponent component = card.getComponent(HasSymbolRewardCardComponent.class);
        for (CardSymbolReward reward : component.rewards) {
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
    public boolean hasResourceType(Card card, CardSymbol type) {
        return getResourceFromType(card, type) != null;
    }

    /**
     * Gets the total amount of a specific CardSymbolReward type from all cards in a principality.
     * @param principality the principality to check
     * @param type the type of the CardSymbolReward to sum
     * @return a CardSymbolReward representing the total amount of the specified type
     */
    public CardSymbolReward getAllRewardsFromPrincipality (Principality principality, CardSymbol type) {
        CardSymbolReward reward = new CardSymbolReward(type, 0);
        Card[] cards = principality.getPlayedCards();
        for (Card card : cards) {
            CardSymbolReward cardReward = getResourceFromType(card, type);
            reward = new CardSymbolReward(type, reward.getAmount() + (cardReward != null ? cardReward.getAmount() : 0));
        }
        return reward;
    }


}
