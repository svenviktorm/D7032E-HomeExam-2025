package myOwnVersion.Cards.DirectEffects;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;
import myOwnVersion.Cards.CardSystems.ResourceCardSystem;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Principality;
import myOwnVersion.GameTurn.GameTurn;

public class YearOfPlentyEffect implements DirectEffect {
    @Override
    public void applyEffect(GameState gameState, GameTurn gameTurn) {
        ResourceCardSystem resourceSystem = myOwnVersion.Cards.CardHandeler.getInstance().getCardSystem(ResourceCardSystem.class);
        for (Principality principality : gameState.getAllPrincipalities()) {
            for (Card cardd : principality.getPlayedCards()) {
                String cardName = cardd.getComponent(StandardCardInfoComponent.class).name;
                if (cardName.equals("Storehouse") || cardName.equals("Abbey")) {
                    resourceSystem.giveResourceToNeighbors(cardd, principality);
                }
            }
        }
    }
}
