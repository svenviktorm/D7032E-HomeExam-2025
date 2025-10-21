package myOwnVersion.GameState.GameStateSetup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.EventCardComponent;
import myOwnVersion.Cards.CardComponents.RegionCardComponent;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;
import myOwnVersion.GameState.BasicGameState;
import myOwnVersion.GameState.DiscardPile;
import myOwnVersion.GameState.DrawDeck;
import myOwnVersion.GameState.EventDrawDeck;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;

public class BasicGameStateSetup extends GameStateSetup {
    @Override
    public GameState setup(java.util.List<myOwnVersion.Cards.Card> cards) {
        //test that there are 94 cards
        if (cards.size() != 94) {
            throw new IllegalArgumentException("Expected 94 cards, but got " + cards.size());
        }
        DrawDeck regionDeck = new DrawDeck(getCardsByComponent(cards, RegionCardComponent.class));
        DrawDeck roadDeck = new DrawDeck(getCardsByName(cards, "Road"));
        DrawDeck settlementDeck = new DrawDeck(getCardsByName(cards, "Settlement"));
        DrawDeck cityDeck = new DrawDeck(getCardsByName(cards, "City"));
        EventDrawDeck eventDeck = new EventDrawDeck(getCardsByComponent(cards, EventCardComponent.class));

        Principality redPrincipality = new Principality(generatePrincipalitySetup(true,settlementDeck,roadDeck,regionDeck));
        Principality bluePrincipality = new Principality(generatePrincipalitySetup(false, settlementDeck,roadDeck,regionDeck));

        ArrayList<DrawDeck> normalDrawDecks = generate4NormalDrawDecks(cards);
        DrawDeck drawStack1 = normalDrawDecks.get(0);
        DrawDeck drawStack2 = normalDrawDecks.get(1);
        DrawDeck drawStack3 = normalDrawDecks.get(2);
        DrawDeck drawStack4 = normalDrawDecks.get(3);

        // randomise starting principality 
        Principality currentPlayerPrincipality = redPrincipality; // For now, red always starts
        if (Math.random() < 0.5) {
            currentPlayerPrincipality = bluePrincipality;
        }
        return new BasicGameState(
            redPrincipality, 
            bluePrincipality, 
            currentPlayerPrincipality, 
            roadDeck, 
            settlementDeck, 
            cityDeck, 
            regionDeck, 
            eventDeck, 
            drawStack1, drawStack2, drawStack3, drawStack4, 
            new DiscardPile());
    }



    private Map<Position, Card> generatePrincipalitySetup(boolean isRedPrincipality, DrawDeck settlementDeck, DrawDeck roadDeck, DrawDeck regionDeck) {
        // Generates the starting principality setup with predefined placements
        Map<Position, Card> principalitySetup = new java.util.HashMap<>();

        // Place Settlements & road
        principalitySetup.put(new Position(0, 0), roadDeck.drawCardTop());
        principalitySetup.put(new Position(0, 1), settlementDeck.drawCardTop());
        principalitySetup.put(new Position(0, -1), settlementDeck.drawCardTop());

        // list out the region positions
        Position[] regionPositions = {
            new Position(1, -2), new Position (1, 0), new Position (1, 2),
            new Position(-1, -2), new Position (-1, 0), new Position (-1, 2)
        };

        ArrayList<Card> regionCards = new ArrayList<>();
        //get the region card types in order
        String[] regionCardTypes = {"Forest", "Gold Field", "Field", "Hill", "Pasture", "Mountain"};
        
        //get the regeion card die values in order based on principality color
        int[] regionCardDieValues;
        if (isRedPrincipality) {
            regionCardDieValues = new int[] {2, 1, 6, 3, 4, 5};
        } else {
            regionCardDieValues = new int[] {3, 4, 5, 2, 1, 6};
        }
        List<Card> availableRegionCards = regionDeck.getCards();
        // print out available region cards for debugging
        for (Card card : availableRegionCards) {
            StandardCardInfoComponent info = card.getComponent(StandardCardInfoComponent.class);
            RegionCardComponent resource = card.getComponent(RegionCardComponent.class);
            //System.out.println("Available Region Card: " + info.name + ", Die Value: " + resource.productionDiceRoll);
        }
        // print out size of available region cards
        //System.out.println("Available Region Cards count: " + availableRegionCards.size());


        // Find and assign the correct region cards
        for (int i = 0; i < regionCardTypes.length; i++) {
            boolean found = false;
            //System.out.println("Looking for Region Card: " + regionCardTypes[i] + ", Die Value: " + regionCardDieValues[i]);
            for (Card card : availableRegionCards) {
                StandardCardInfoComponent info = card.getComponent(StandardCardInfoComponent.class);
                RegionCardComponent resource = card.getComponent(RegionCardComponent.class);
                if (info != null && resource != null &&
                    info.name.equals(regionCardTypes[i]) &&
                    resource.productionDiceRoll == regionCardDieValues[i]) {
                        regionCards.add(card);
                        regionDeck.removeCard(card);
                        // print out size of available region cards after removal
                        //System.out.println("Available Region Cards count: " + availableRegionCards.size()+ " removed " +info.name+ " with die value " + resource.productionDiceRoll);
                        found = true;
                        break;
                }
            }
            if (!found) {
                //System.out.println("Could not find Region Card: " + regionCardTypes[i] + ", Die Value: " + regionCardDieValues[i]);
                throw new IllegalArgumentException("Could not find region card of type " + regionCardTypes[i] + " with die value " + regionCardDieValues[i]);
            }
            
        }

        // Place Regions
        for (int i = 0; i < regionPositions.length; i++) {
            principalitySetup.put(regionPositions[i], regionCards.get(i));
        }

        return principalitySetup;
    }



    private ArrayList<DrawDeck> generate4NormalDrawDecks(List<Card> cards) {
        // Spleits the remaining cards into 4 equal decks
        ArrayList<DrawDeck> drawDecks = new ArrayList<>();
        int cardsPerDeck = cards.size() / 4;
        for (int i = 0; i < 4; i++) {
            List<Card> deckCards = new ArrayList<>(cards.subList(i * cardsPerDeck, (i + 1) * cardsPerDeck));
            drawDecks.add(new DrawDeck(deckCards));
        }

        return drawDecks;
    }



    private List<Card> getCardsByName(List<Card> cards, String name) {
        List<Card> filteredCards = new java.util.ArrayList<>();
        for (Card card : cards) {
            StandardCardInfoComponent comm = card.getComponent(StandardCardInfoComponent.class);
            if (comm.name.equals(name)) {
                    filteredCards.add(card);
                
            }
        }
        //remove cards from original list
        cards.removeAll(filteredCards);
        return filteredCards;
    }



    private List<Card> getCardsByComponent(List<Card> cards, Class componentClass) {
        List<Card> filteredCards = new java.util.ArrayList<>();
        for (Card card : cards) {
            if (card.hasComponent(componentClass)) {
                filteredCards.add(card);
            }
        }
        //remove cards from original list
        cards.removeAll(filteredCards);
        return filteredCards;
    }

}
