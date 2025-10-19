package myOwnVersion.Cards.CardComponents;

import java.util.ArrayList;
import java.util.List;

import myOwnVersion.Cards.CardSymbolReward;

public class HasSymbolRewardCardComponent implements CardComponent{
    public final List<CardSymbolReward> rewards;

    public HasSymbolRewardCardComponent(List<CardSymbolReward> rewards) {
        this.rewards = rewards;
    }

    public HasSymbolRewardCardComponent(CardSymbolReward reward) {
        this.rewards = new ArrayList<>();
        rewards.add(reward);
    }
}
