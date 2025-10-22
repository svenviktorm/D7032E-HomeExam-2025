package myOwnVersion.Cards.CardLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.CardComponents.RegionCardComponent;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;

public class BasicGamePostCardLoader implements PostCardLoader {
    
    public List<Card> process(List<Card> cards) {
        cards = setupResourceCardDiesValues(cards);
        return cards;
    }

    private List<Card> setupResourceCardDiesValues(List<Card> cards) {
        ArrayList<Card> resourceCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.hasComponent(RegionCardComponent.class)) {
                resourceCards.add(card);
            }
        }
        //print resourceCards for debugging
        //System.out.println("Resource Cards found: " + resourceCards.size());
        for (Card resourceCard : resourceCards) {
            RegionCardComponent resComp = resourceCard.getComponent(RegionCardComponent.class);
            StandardCardInfoComponent infoComp = resourceCard.getComponent(StandardCardInfoComponent.class);
            //System.out.println("Resource Card: " + infoComp.name + ", Type: " + resComp.resourceType);
        }
        // Assign correct die values to resource cards
        HashMap<CardSymbolType, Integer[]> resourceDieMap = new HashMap<>();
        resourceDieMap.put(CardSymbolType.BRICK, new Integer[]{5, 1, 3, 2});
        resourceDieMap.put(CardSymbolType.LUMBER, new Integer[]{6, 2, 2, 3});
        resourceDieMap.put(CardSymbolType.ORE, new Integer[]{4, 2, 5, 6});
        resourceDieMap.put(CardSymbolType.GRAIN, new Integer[]{3, 1, 6, 5});
        resourceDieMap.put(CardSymbolType.WOOL, new Integer[]{6, 5, 4, 1});
        resourceDieMap.put(CardSymbolType.GOLD, new Integer[]{3, 2, 4, 1});

        //Check size of resourceCards
        if (resourceCards.size() != resourceDieMap.size() * 4) {
            throw new IllegalStateException("Expected " + (resourceDieMap.size() * 4) + " resource cards, but found: " + resourceCards.size());
        }
        for (Card resourceCard : resourceCards) {
            RegionCardComponent resComp = resourceCard.getComponent(RegionCardComponent.class);
            Integer[] dieValues = resourceDieMap.get(resComp.resourceType);
            if (dieValues != null && dieValues.length > 0) {
                resComp.productionDiceRoll = dieValues[0];
                // Remove the first value from dieValues and rotate the array
                Integer[] newDieValues = new Integer[dieValues.length - 1];
                System.arraycopy(dieValues, 1, newDieValues, 0, newDieValues.length);
                // print newDieValues for debugging
                //System.out.println("New die values for " + resComp.resourceType + ": " + Arrays.toString(newDieValues));
                resourceDieMap.put(resComp.resourceType, newDieValues);
            } else {
                throw new IllegalStateException("No die values found for resource type: " + resComp.resourceType);
            }
        }
        return cards;
    }
}
