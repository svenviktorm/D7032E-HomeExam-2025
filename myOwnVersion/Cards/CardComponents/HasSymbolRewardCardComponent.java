package myOwnVersion.Cards.CardComponents;

import java.util.ArrayList;
import java.util.List;

import myOwnVersion.Cards.CardSymbolReward;

/**
 * A card component that holds symbol rewards such as victory points or ore.
 */
public class HasSymbolRewardCardComponent implements CardComponent{
    public List<CardSymbolReward> rewards;

    /**
     * Creates a HasSymbolRewardCardComponent with the specified rewards.
     * @param rewards
     */
    public HasSymbolRewardCardComponent(List<CardSymbolReward> rewards) {
        this.rewards = rewards;
    }

    /**
     * Creates a HasSymbolRewardCardComponent with a single reward.
     * @param reward
     */
    public HasSymbolRewardCardComponent(CardSymbolReward reward) {
        this.rewards = new ArrayList<>();
        rewards.add(reward);
    }
}
