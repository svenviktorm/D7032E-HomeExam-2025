package myOwnVersion.Cards.CardComponents;

import java.util.ArrayList;
import java.util.List;

import myOwnVersion.Cards.CardSymbol;

/**
 * A card component that holds symbol rewards such as victory points or ore.
 */
public class HasSymbolRewardCardComponent implements CardComponent{
    public List<CardSymbol> rewards;

    /**
     * Creates a HasSymbolRewardCardComponent with the specified rewards.
     * @param rewards
     */
    public HasSymbolRewardCardComponent(List<CardSymbol> rewards) {
        this.rewards = rewards;
    }

    /**
     * Creates a HasSymbolRewardCardComponent with a single reward.
     * @param reward
     */
    public HasSymbolRewardCardComponent(CardSymbol reward) {
        this.rewards = new ArrayList<>();
        rewards.add(reward);
    }
}
